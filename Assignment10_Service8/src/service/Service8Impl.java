package service;

import entity.*;
import transfer.*;

import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@WebService
public class Service8Impl implements Service8 {

    @Override
    public String create(String studentID, String name, String gender, int age) throws JAXBException, IOException {
        XML2Bean xml2Bean = new XML2Bean();
        ArrayList<Student> students = xml2Bean.read("Assignment10_Service8/src/resources/StudentList2.xml");
        Student student = new Student(studentID, new StudentInfo(new PersonInfo(name, gender, age, new DepartmentList())), new ClassList(), new PersonalScoreList());
        students.add(student);
        Bean2XML bean2XML = new Bean2XML();
        bean2XML.write(new StudentList(), students, "Assignment10_Service8/src/resources/StudentList2.xml");
        System.out.println(showResult("Assignment10_Service8/src/resources/StudentList2.xml"));
        return showResult("Assignment10_Service8/src/resources/StudentList2.xml");
    }

    @Override
    public String retrieve(String studentID) throws JAXBException, IOException {
        XML2Bean xml2Bean = new XML2Bean();
        ArrayList<Student> students = xml2Bean.read("Assignment10_Service8/src/resources/StudentList2.xml");
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getId().equals(studentID))
                result.add(s);
        }
        new Bean2XML().write(new StudentList(),result,"Assignment10_Service8/src/resources/retrievedStudentList.xml");
        System.out.println(showResult("Assignment10_Service8/src/resources/retrievedStudentList.xml"));
        return showResult("Assignment10_Service8/src/resources/retrievedStudentList.xml");
    }

    @Override
    public String update(String studentID, String name, String gender, int age) throws JAXBException, IOException {
        XML2Bean xml2Bean = new XML2Bean();
        ArrayList<Student> students = xml2Bean.read("Assignment10_Service8/src/resources/StudentList2.xml");
        for(Student s:students){
            if(s.getId().equals(studentID)){
                StudentInfo studentInfo = s.getStudentInfo();
                PersonInfo personInfo = studentInfo.getPersonInfo();
                personInfo.setName(name);
                personInfo.setGender(gender);
                personInfo.setAge(age);
                studentInfo.setPersonInfo(personInfo);
                s.setStudentInfo(studentInfo);
                break;
            }
        }
        new Bean2XML().write(new StudentList(),students,"Assignment10_Service8/src/resources/StudentList2.xml");
        System.out.println(showResult("Assignment10_Service8/src/resources/StudentList2.xml"));
        return showResult("Assignment10_Service8/src/resources/StudentList2.xml");
    }

    @Override
    public String delete(String studentID) throws JAXBException, IOException {
        XML2Bean xml2Bean = new XML2Bean();
        ArrayList<Student> students = xml2Bean.read("Assignment10_Service8/src/resources/StudentList2.xml");
        ArrayList<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (!s.getId().equals(studentID))
                result.add(s);
        }
        new Bean2XML().write(new StudentList(),result,"Assignment10_Service8/src/resources/StudentList2.xml");
        System.out.println(showResult("Assignment10_Service8/src/resources/StudentList2.xml"));
        return showResult("Assignment10_Service8/src/resources/StudentList2.xml");
    }

    public String showResult(String address) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new FileReader(address));
        String line;
        String result = "";
        line = bufferedReader.readLine();
        while (line != null) {
            result = result + line + System.lineSeparator();
            line = bufferedReader.readLine();
        }
        return result;
    }
}
