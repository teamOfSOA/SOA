package entity;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "学生列表", namespace = "http://jw.nju.edu.cn/schema")
public class StudentList {

    private ArrayList<Student> studentList;
    private String SchemaLocation;

    public StudentList(){}

    public StudentList(ArrayList<Student> students) {
        this.studentList = students;
        this.SchemaLocation = "http://jw.nju.edu.cn/schema StudentList.xsd";
    }

    @XmlElement(name = "学生", namespace = "http://jw.nju.edu.cn/schema")
    public void setStudents(ArrayList<Student> students) {
        this.studentList = students;
    }

    @XmlAttribute(namespace = "http://www.w3.org/2001/XMLSchema-instance")
    public void setSchemaLocation(String schemaLocation) {
        SchemaLocation = schemaLocation;
    }

    public ArrayList<Student> getStudents() {
        return studentList;
    }

    public String getSchemaLocation() {
        return SchemaLocation;
    }
}
