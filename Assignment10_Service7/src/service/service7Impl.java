package service;

import java.io.*;
import java.util.ArrayList;
import entity.*;
import transfer.*;

import javax.jws.WebService;
import javax.xml.bind.JAXBException;
@WebService
public class service7Impl implements service7 {
    @Override
    public String create(String courseID,String type,String studentID,int score) throws JAXBException, IOException {
        XML_2_bean xml_2_bean=new XML_2_bean();
        ArrayList<ClassScore> courseGrades=xml_2_bean.read("Assignment10_Service7/src/main/resources/scoreList.xml");
        ClassScore c=new ClassScore(new Score(studentID,score),courseID,type);
        courseGrades.add(c);

        bean_2_XML bean_2_xml=new bean_2_XML();
        bean_2_xml.write(new SortScoreList(),courseGrades,"Assignment10_Service7/src/main/resources/scoreList.xml");

        System.out.println(showResult("Assignment10_Service7/src/main/resources/scoreList.xml"));
        return showResult("Assignment10_Service7/src/main/resources/scoreList.xml");
    }
    @Override
    public String retrieve(String courseID,String type,String studentID) throws JAXBException, IOException {
        XML_2_bean xml_2_bean=new XML_2_bean();
        ArrayList<ClassScore> courseGrades=xml_2_bean.read("Assignment10_Service7/src/main/resources/scoreList.xml");
        ArrayList<ClassScore> result=new ArrayList<ClassScore>();
        for(ClassScore c:courseGrades){
            if(c.getClassId().equals(courseID)&&c.getScoreAttribute().equals(type)&&c.getScore().getStudentId().equals(studentID)){
                result.add(c);
            }
        }

        bean_2_XML bean_2_xml=new bean_2_XML();
        bean_2_xml.write(new SortScoreList(),result,"Assignment10_Service7/src/main/resources/retrieveResult.xml");

        System.out.println(showResult("Assignment10_Service7/src/main/resources/retrieveResult.xml"));
        return showResult("Assignment10_Service7/src/main/resources/retrieveResult.xml");
    }
    @Override
    public String update(String courseID,String type,String studentID,int newScore) throws JAXBException, IOException {
        XML_2_bean xml_2_bean=new XML_2_bean();
        ArrayList<ClassScore> courseGrades=xml_2_bean.read("Assignment10_Service7/src/main/resources/scoreList.xml");
        for(ClassScore c:courseGrades){
            if(c.getClassId().equals(courseID)&&c.getScoreAttribute().equals(type)&&c.getScore().getStudentId().equals(studentID)){
                c.setScore(new Score(studentID,newScore));
            }
        }

        bean_2_XML bean_2_xml=new bean_2_XML();
        bean_2_xml.write(new SortScoreList(),courseGrades,"Assignment10_Service7/src/main/resources/scoreList.xml");

        System.out.println(showResult("Assignment10_Service7/src/main/resources/scoreList.xml"));
        return showResult("Assignment10_Service7/src/main/resources/scoreList.xml");
    }
    @Override
    public String delete(String courseID,String type,String studentID) throws JAXBException, IOException {
        XML_2_bean xml_2_bean=new XML_2_bean();
        ArrayList<ClassScore> courseGrades=xml_2_bean.read("Assignment10_Service7/src/main/resources/scoreList.xml");
        ArrayList<ClassScore> result=new ArrayList<>();
        for(ClassScore c:courseGrades){
            if(c.getClassId().equals(courseID)&&c.getScoreAttribute().equals(type)&&c.getScore().getStudentId().equals(studentID)){
            }
            else{result.add(c);}
        }

        bean_2_XML bean_2_xml=new bean_2_XML();
        bean_2_xml.write(new SortScoreList(),result,"Assignment10_Service7/src/main/resources/scoreList.xml");

        System.out.println(showResult("Assignment10_Service7/src/main/resources/scoreList.xml"));
        return showResult("Assignment10_Service7/src/main/resources/scoreList.xml");
    }
    public String showResult(String address) throws IOException {
        BufferedReader bufferedReader=new BufferedReader(new FileReader(address));
        String line;
        String result="";
        line=bufferedReader.readLine();
        while(line!=null){
            result=result+line+System.lineSeparator();
            line=bufferedReader.readLine();
        }
        return result;
//        return null;
    }
}
