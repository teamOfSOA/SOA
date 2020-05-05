package main.java;

import java.io.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.util.Iterator;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Servlet implementation class main.java.HelloServlet
 */
@WebServlet("/Score")
public class MyServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyServlet() throws ServletException{
        super();
    }

    /**
     * 处理客户端的GET请求
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        StringBuilder soapXML = new StringBuilder();
        String errorMessage = "";
        switch (isId(id)){
            case 1:errorMessage = "学生id中只能包含数字!";
                break;
            case 2:errorMessage = "学生id长度为9位!";
                break;
            case 0: break;
        }
        SAXReader reader = new SAXReader();
        Document document;
        try {
            document = reader.read(new File("D:\\服务端与SOA开发\\SOA\\Assignment5\\src\\main\\resources\\StudentList.xml"));
            Element root = document.getRootElement();

            MessageFactory factory = MessageFactory.newInstance();
            SOAPMessage respMsg = factory.createMessage();
            SOAPPart soapPart = respMsg.getSOAPPart();
            SOAPEnvelope envelope = soapPart.getEnvelope();

            SOAPBody body = envelope.getBody();
            boolean hasFound=false;
            for(Iterator it = root.elementIterator(); it.hasNext();){
                Element Student = (Element) it.next();
                Element Id = Student.element("学号");
                if(Id!=null){
                    if(Id.getText().equals(id)){
                        hasFound = true;

                        SOAPBodyElement bodyElement = body.addBodyElement(envelope.createName("Correct","m","http://www.w3.org/2001/XMLSchema-instance"));
                        bodyElement.addAttribute(QName.valueOf("SOAP-ENV:encodingStyle"),"http://www.w3.org/2003/05/soap-encoding");
                        Element ScoreList = Student.element("个人成绩列表").element("课程成绩列表");
                        String sid = Id.getText();
                        String name = Student.element("学生信息").element("个人信息").element("姓名").getText();
                        SOAPElement student = bodyElement.addChildElement(envelope.createName("学生","m","http://www.w3.org/2001/XMLSchema-instance"));
                        student.addAttribute(QName.valueOf("学号"),sid);
                        student.addAttribute(QName.valueOf("姓名"),name);
                        SOAPElement scoreList = student.addChildElement(envelope.createName("个人成绩列表"));

                        for(Iterator iter = ScoreList.elementIterator();iter.hasNext();){
                            Name score = envelope.createName("课程成绩");
                            Element Score = (Element)iter.next();
                            String courseId = Score.attributeValue("课程编号");
                            String scoreType = Score.attributeValue("成绩性质");
                            String scoreValue = Score.element("成绩").element("得分").getText();
                            scoreList.addChildElement(score).addTextNode(scoreValue).addAttribute(QName.valueOf("课程编号"),courseId).addAttribute(QName.valueOf("成绩性质"),scoreType);
                        }
                    }
                }
            }

            if(!hasFound && errorMessage.equals("")){
                errorMessage = "没有找到该学号对应的学生!";
            }
            response.setContentType("application/soap+xml");
            response.setCharacterEncoding("utf-8");
            PrintWriter out = response.getWriter();
            if(errorMessage.equals("")){
                response.setStatus(200);
            }else{
                response.setStatus(500);
                SOAPBodyElement bodyElement = body.addBodyElement(envelope.createName("Fault","m","http://www.w3.org/2001/XMLSchema-instance"));
                bodyElement.addAttribute(QName.valueOf("SOAP-ENV:encodingStyle"),"http://www.w3.org/2003/05/soap-encoding");
                bodyElement.addChildElement(envelope.createName("errorMessage","m","http://www.w3.org/2001/XMLSchema-instance")).addTextNode(errorMessage);
            }
            out.print(soapMessageToString(respMsg));
        } catch (DocumentException | SOAPException e) {
            e.printStackTrace();
        }
    }

    /**
     * 判断参数是否是符合要求的id
     * @param id 传入的参数
     * @return int，错误类型，0：正确；1：含有非数字；2：长度不匹配
     */
    private int isId(String id) {
        boolean isNumber=true;
        for(char c:id.toCharArray()){
            if(!Character.isDigit(c)){
                isNumber = false;break;
            }
        }
        if(!isNumber){
            return 1;
        }
        if(id.length()!=9){
            return 2;
        }
        return 0;
    }

    /**
     * 处理客户端的POST请求
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

    /**
     * 将soapMessage转化为string
     * @param message soap消息
     * @return string
     */
    public String soapMessageToString(SOAPMessage message) {
        String result = null;

        if (message != null) {
            try (ByteArrayOutputStream bao = new ByteArrayOutputStream()) {
                message.writeTo(bao);
                result = bao.toString("utf-8");
            } catch (Exception ignored) {
            }
        }
        return result;
    }
}
