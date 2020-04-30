package main.java;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.PrintWriter;
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
     * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        StringBuilder soapXML = new StringBuilder();
        String errorMessage = "";
        StringBuilder correctMessage = new StringBuilder();
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
            boolean hasFound=false;
            for(Iterator it = root.elementIterator(); it.hasNext();){
                Element Student = (Element) it.next();
                Element Id = Student.element("学号");
                if(Id!=null){
                    if(Id.getText().equals(id)){
                        hasFound = true;
                        Element ScoreList = Student.element("个人成绩列表").element("课程成绩列表");
                        String sid = Id.getText();
                        String name = Student.element("学生信息").element("个人信息").element("姓名").getText();
                        correctMessage.append("<m:Correct env:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\" xmlns:m=\"http://www.w3.org/2001/XMLSchema-instance\">\n" + "            <m:学生 学号=\"").append(sid).append("\" 姓名=\"").append(name).append("\">\n").append("                <个人成绩列表>\n");
                        for(Iterator iter = ScoreList.elementIterator();iter.hasNext();){
                            Element Score = (Element)iter.next();
                            String courseId = Score.attributeValue("课程编号");
                            String scoreType = Score.attributeValue("成绩性质");
                            String scoreValue = Score.element("成绩").element("得分").getText();
                            correctMessage.append("                    <课程成绩 课程编号=\"")
                                    .append(courseId)
                                    .append("\" 成绩性质=\"")
                                    .append(scoreType)
                                    .append("\">")
                                    .append(scoreValue)
                                    .append("</课程成绩>\n");
                        }
                        correctMessage.append("                </个人成绩列表>\n" +
                                "            </m:学生>\n" +
                                "        </m:Correct>\n");
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
                soapXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" +
                        "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" >\n" +
                        "    <env:Body>\n")
                .append(correctMessage)
                .append("    </env:Body>\n" +
                        "</env:Envelope>");

            }else{
                response.setStatus(500);
                soapXML.append("<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + "<env:Envelope xmlns:env=\"http://schemas.xmlsoap.org/soap/envelope/\" >\n" + "    <env:Body>\n" +
                        "        <m:Fault env:encodingStyle=\"http://www.w3.org/2003/05/soap-encoding\" xmlns:m=\"http://www.w3.org/2001/XMLSchema-instance\">\n" +
                        "            <m:errorMessage>")
                        .append(errorMessage)
                        .append("</m:errorMessage>\n")
                        .append("        </m:Fault>\n")
                        .append("    </env:Body>\n")
                        .append("</env:Envelope>");
            }
            out.println(soapXML.toString());
        } catch (DocumentException e) {
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
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // TODO Auto-generated method stub
    }

}
