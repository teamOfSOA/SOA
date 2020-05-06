package main.java;

import java.io.*;
import javax.jws.WebMethod;
import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.namespace.QName;
import javax.xml.soap.*;
import java.util.*;
import java.util.regex.Pattern;
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
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
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
            document = reader.read(new File("/home/lms/Desktop/Assignment5/src/main/resources/StudentList.xml"));
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
     * 将soapMessage转化为string
     * @param message soap消息
     * @return string
     */
    public String soapMessageToString(SOAPMessage message) {
        String result = null;

        if (message != null) {
            try (ByteArrayOutputStream bao = new ByteArrayOutputStream()) {
                message.writeTo(bao);
                result = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>\n" + bao.toString("utf-8").replaceAll("><",">\n<");
            } catch (Exception ignored) {
            }
        }
        return result;
    }


    private String errorMsg = "";
    private int updatedCount = 0;

    /**
     * 处理客户端的POST请求
     * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response){
        // TODO Auto-generated method stub
        errorMsg = "";
        List<StudentScore> updateContent = getMethodParams(request);


        if("".equals(errorMsg)){
            //检验参数是否符合条件
            checkParams(updateContent);
        }



        if("".equals(errorMsg)){
            Set<String> studentIdSet = new HashSet<>();
            for(StudentScore studentScore:updateContent){
                studentIdSet.add(studentScore.getStudentId());
            }

            SAXReader reader = new SAXReader();
            Document document;
            try {
                document = reader.read(new File("/home/lms/Desktop/Assignment5/src/main/resources/StudentList.xml"));
                Element root = document.getRootElement();
                MessageFactory factory = MessageFactory.newInstance();
                SOAPMessage respMsg = factory.createMessage();
                SOAPPart soapPart = respMsg.getSOAPPart();
                SOAPEnvelope envelope = soapPart.getEnvelope();
                SOAPBody body = envelope.getBody();

                //TODO
                SOAPBodyElement bodyElement = body.addBodyElement(envelope.createName("Correct", "m", "http://www.w3.org/2001/XMLSchema-instance"));
                bodyElement.addAttribute(QName.valueOf("SOAP-ENV:encodingStyle"), "http://www.w3.org/2003/05/soap-encoding");

                for (Iterator it = root.elementIterator(); it.hasNext(); ) {
                    Element Student = (Element) it.next();
                    Element Id = Student.element("学号");
                    if (Id != null) {
                        if (studentIdSet.contains(Id.getText())) {

                            studentIdSet.remove(Id.getText());

                            Element ScoreList = Student.element("个人成绩列表").element("课程成绩列表");
                            String sid = Id.getText();
                            String name = Student.element("学生信息").element("个人信息").element("姓名").getText();
                            SOAPElement student = bodyElement.addChildElement(envelope.createName("学生", "m", "http://www.w3.org/2001/XMLSchema-instance"));
                            student.addAttribute(QName.valueOf("学号"), sid);
                            student.addAttribute(QName.valueOf("姓名"), name);
                            SOAPElement scoreList = student.addChildElement(envelope.createName("个人成绩列表"));

                            for (Iterator iter = ScoreList.elementIterator(); iter.hasNext(); ) {
                                Name score = envelope.createName("课程成绩");
                                Element Score = (Element) iter.next();
                                String courseId = Score.attributeValue("课程编号");
                                String scoreType = Score.attributeValue("成绩性质");
                                String scoreValue = Score.element("成绩").element("得分").getText();

                                //查看是否需要更新成绩
                                scoreValue = checkUpdateScore(sid, courseId, scoreType, scoreValue, updateContent);

                                scoreList.addChildElement(score).addTextNode(scoreValue).addAttribute(QName.valueOf("课程编号"), courseId).addAttribute(QName.valueOf("成绩性质"), scoreType);
                            }
                        }
                    }
                }

                if(studentIdSet.size() > 0){
                    errorMsg = "Don't have these studentId: ";
                    for (String sid:studentIdSet)errorMsg = errorMsg + " " + sid;
                }

                if("".equals(errorMsg) && updatedCount != updateContent.size()){
                    errorMsg = "Some params isn't in StudentList";
                }

                response.setContentType("application/soap+xml");
                response.setCharacterEncoding("utf-8");


                if("".equals(errorMsg)){
                    response.setStatus(200);
                }else{
                    response.setStatus(500);
                    respMsg = factory.createMessage();
                    soapPart = respMsg.getSOAPPart();
                    envelope = soapPart.getEnvelope();
                    body = envelope.getBody();
                    body = envelope.getBody();
                    response.setContentType("application/soap+xml");
                    response.setCharacterEncoding("utf-8");
                    bodyElement = body.addBodyElement(envelope.createName("Fault","m","http://www.w3.org/2001/XMLSchema-instance"));
                    bodyElement.addAttribute(QName.valueOf("SOAP-ENV:encodingStyle"),"http://www.w3.org/2003/05/soap-encoding");
                    bodyElement.addChildElement(envelope.createName("errorMessage","m","http://www.w3.org/2001/XMLSchema-instance")).addTextNode(errorMsg);
                }
                response.getWriter().print(soapMessageToString(respMsg));
            }catch (Exception e){
                e.printStackTrace();
            }
        }else{
            try {
                MessageFactory factory = MessageFactory.newInstance();
                SOAPMessage respMsg = factory.createMessage();
                SOAPPart soapPart = respMsg.getSOAPPart();
                SOAPEnvelope envelope = soapPart.getEnvelope();
                SOAPBody body = envelope.getBody();
                response.setContentType("application/soap+xml");
                response.setCharacterEncoding("utf-8");
                response.setStatus(500);
                SOAPBodyElement bodyElement = body.addBodyElement(envelope.createName("Fault","m","http://www.w3.org/2001/XMLSchema-instance"));
                bodyElement.addAttribute(QName.valueOf("SOAP-ENV:encodingStyle"),"http://www.w3.org/2003/05/soap-encoding");
                bodyElement.addChildElement(envelope.createName("errorMessage","m","http://www.w3.org/2001/XMLSchema-instance")).addTextNode(errorMsg);
                response.getWriter().print(soapMessageToString(respMsg));
            }catch (Exception e){}
        }
    }

    public List<StudentScore> getMethodParams(HttpServletRequest request){
        List<StudentScore> updateContent = new ArrayList<>();
        SOAPMessage reqMsg = null;
        try{
            MessageFactory messageFactory = MessageFactory.newInstance();
            reqMsg = messageFactory.createMessage(new MimeHeaders(), request.getInputStream());
            reqMsg.saveChanges();
            SOAPBody body = reqMsg.getSOAPBody();

            if(body == null){
                errorMsg = "soapXml syntax error";
                return updateContent;
            }

            Iterator<SOAPElement> iterator = body.getChildElements();
            SOAPElement methodElement = null;
            while(iterator.hasNext()){
                Object o = iterator.next();
                if(o != null){
                    SOAPElement element = null;
                    try{
                        element = (SOAPElement)o;
                        if("m:updateScore".equals(element.getNodeName())){
                            methodElement = element;
                            break;
                        }
                    }catch(Exception e){}
                }
            }

            if(methodElement == null){
                errorMsg = "soap has wrong method name";
            }

            if("".equals(errorMsg)){
                updateContent = getParams(methodElement.getChildElements());
            }
        }catch (SOAPException soapException) {
            errorMsg = soapException.getMessage();
        }catch (IOException ioException){
            errorMsg = "The data flow encountered a error";
        }
        return updateContent;
    }

    public List<StudentScore> getParams(Iterator<SOAPElement> iterator){
        List<StudentScore> updateContent = new ArrayList<>();
        while(iterator.hasNext()) {
            Object o1 = iterator.next();
            if (o1 != null) {
                SOAPElement element = null;
                try {
                    element = (SOAPElement) o1;
                    Iterator<SOAPElement> elementIterator = element.getChildElements();
                    String studentId = null, courseId = null, scoreType = null, score = null;
                    while (elementIterator.hasNext()) {
                        Object o2 = elementIterator.next();
                        if (o2 != null) {
                            SOAPElement subElement = null;
                            try {
                                subElement = (SOAPElement) o2;
                                if ("m:studentId".equals(subElement.getNodeName())) studentId = subElement.getValue();
                                if ("m:courseId".equals(subElement.getNodeName())) courseId = subElement.getValue();
                                if ("m:scoreType".equals(subElement.getNodeName())) scoreType = subElement.getValue();
                                if ("m:score".equals(subElement.getNodeName())) score = subElement.getValue();
                            }catch (Exception ex){}
                        }
                    }
                    // System.out.println(studentId + " " + courseId + " " + scoreType + " " + score);
                    StudentScore studentScore = new StudentScore(studentId, courseId, scoreType, score);
                    updateContent.add(studentScore);
                }catch (Exception e){}
            }
        }
        return updateContent;
    }

    public void checkParams(List<StudentScore> updateContent){
        if(updateContent.size() == 0)errorMsg = "Nothing needs to be updated";

        for (StudentScore studentScore: updateContent){
            String studentId = studentScore.getStudentId();
            if(studentId.length() != 9){
                errorMsg = studentId + ": the length of studengId must be equal to 9";
                break;
            }
            Pattern pattern = Pattern.compile("[0-9]*");
            if(pattern.matcher(studentId).matches() == false){
                errorMsg = studentId + ": it has to be all numbers";
                break;
            }
            String courseId = studentScore.getCourseId();
            if(pattern.matcher(courseId).matches() == false){
                errorMsg = courseId + ": it has to be all numbers";
                break;
            }

            String score = studentScore.getScore();
            if(pattern.matcher(score).matches() == false){
                errorMsg = score + ": score >= 0 and must be all numbers";
                break;
            }else if(Integer.parseInt(score) > 100){
                errorMsg = score + ": score <= 100";
            }
        }
    }




    /**
     * 查看是否需要更新成绩,需要更新则返回新的成绩，否则返回原来的
     * @param sid
     * @param courseId
     * @param scoreType
     * @param scoreValue
     * @param updateContent
     * @return
     */
    public String checkUpdateScore(String sid, String courseId, String scoreType, String scoreValue, List<StudentScore> updateContent){
        for (StudentScore o: updateContent){
            if(sid.equals(o.getStudentId()) && courseId.equals(o.getCourseId()) && scoreType.equals(o.getScoreType())){
                updatedCount += 1;
                return o.getScore();
            }
        }
        return scoreValue;
    }
}
