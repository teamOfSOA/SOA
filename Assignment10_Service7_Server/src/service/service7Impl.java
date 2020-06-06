package service;

import java.io.*;
import java.util.ArrayList;
import entity.*;
import transfer.*;

import javax.jws.WebService;
import javax.xml.bind.JAXBException;

@WebService
public class service7Impl implements service7 {

    validate v=new validate();
    XML_2_bean xml_2_bean=new XML_2_bean();
    bean_2_XML bean_2_xml=new bean_2_XML();
    ArrayList<ClassScore> courseGrades;

    @Override
    public String create(String courseID,String type,String studentID,int score) throws IOException, dataException, JAXBException {
        v.validate1(courseID, type, studentID, score);

        courseGrades=xml_2_bean.read("src/main/resources/scoreList.xml");

        ClassScore c=new ClassScore(new Score(studentID,score),courseID,type);
        courseGrades.add(c);

        bean_2_xml.write(new SortScoreList(),courseGrades,"src/main/resources/scoreList.xml");

        System.out.println(showResult("src/main/resources/scoreList.xml"));
        return showResult("src/main/resources/scoreList.xml");
    }

    @Override
    public String retrieve(String courseID,String type,String studentID) throws IOException, JAXBException, dataException {
        v.validate2(courseID,type,studentID);

        courseGrades=xml_2_bean.read("src/main/resources/scoreList.xml");

        v.validate3(courseID, type, studentID, courseGrades);

        ArrayList<ClassScore> res=new ArrayList<ClassScore>();
        for(ClassScore c:courseGrades){
            if(c.getClassId().equals(courseID)&&c.getScoreAttribute().equals(type)&&c.getScore().getStudentId().equals(studentID)){
                res.add(c);
            }
        }

        bean_2_xml.write(new SortScoreList(),res,"src/main/resources/retrieveResult.xml");

        System.out.println(showResult("src/main/resources/retrieveResult.xml"));
        return showResult("src/main/resources/retrieveResult.xml");
    }

    @Override
    public String update(String courseID,String type,String studentID,int newScore) throws IOException, JAXBException, dataException {
        v.validate1(courseID,type,studentID,newScore);

        courseGrades=xml_2_bean.read("src/main/resources/scoreList.xml");

        v.validate3(courseID, type, studentID, courseGrades);

        for(ClassScore c:courseGrades){
            if(c.getClassId().equals(courseID)&&c.getScoreAttribute().equals(type)&&c.getScore().getStudentId().equals(studentID)){
                c.setScore(new Score(studentID,newScore));
            }
        }

        bean_2_xml.write(new SortScoreList(),courseGrades,"src/main/resources/scoreList.xml");

        System.out.println(showResult("src/main/resources/scoreList.xml"));
        return showResult("src/main/resources/scoreList.xml");
    }

    @Override
    public String delete(String courseID,String type,String studentID) throws IOException, JAXBException, dataException {
        v.validate2(courseID,type,studentID);

        courseGrades=xml_2_bean.read("src/main/resources/scoreList.xml");

        v.validate3(courseID, type, studentID, courseGrades);

        ArrayList<ClassScore> res=new ArrayList<>();
        for(ClassScore c:courseGrades){
            if(c.getClassId().equals(courseID)&&c.getScoreAttribute().equals(type)&&c.getScore().getStudentId().equals(studentID)){
            }
            else{res.add(c);}
        }

        bean_2_xml.write(new SortScoreList(),res,"src/main/resources/scoreList.xml");

        System.out.println(showResult("src/main/resources/scoreList.xml"));
        return showResult("src/main/resources/scoreList.xml");
    }

    private String showResult(String address) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new FileReader(address));
        String line;
        String result="";
        line=bufferedReader.readLine();
        while(line!=null){
            result=result+line+System.lineSeparator();
            line=bufferedReader.readLine();
        }
        return result;
    }
}
