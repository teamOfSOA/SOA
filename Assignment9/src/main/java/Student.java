import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"id", "studentInfo", "classList", "personalScoreList"})
public class Student {

    private String id;
    private StudentInfo studentInfo;
    private ClassList classList;
    private PersonalScoreList personalScoreList;

    public Student() {
    }

    public Student(String id, StudentInfo studentInfo, ClassList classList, PersonalScoreList personalScoreList) {
        this.id = id;
        this.studentInfo = studentInfo;
        this.classList = classList;
        this.personalScoreList = personalScoreList;
    }

    @XmlElement(name = "学号",namespace = "http://jw.nju.edu.cn/schema")
    public void setId(String id) {
        this.id = id;
    }

    @XmlElement(name = "学生信息",namespace = "http://jw.nju.edu.cn/schema")
    public void setStudentInfo(StudentInfo studentInfo) {
        this.studentInfo = studentInfo;
    }

    @XmlElement(name = "参与课程列表",namespace = "http://jw.nju.edu.cn/schema")
    public void setClassList(ClassList classList) {
        this.classList = classList;
    }

    @XmlElement(name = "个人成绩列表",namespace = "http://jw.nju.edu.cn/schema")
    public void setPersonalScoreList(PersonalScoreList personalScoreList) {
        this.personalScoreList = personalScoreList;
    }

    public String getId() {
        return id;
    }

    public StudentInfo getStudentInfo() {
        return studentInfo;
    }

    public ClassList getClassList() {
        return classList;
    }

    public PersonalScoreList getPersonalScoreList() {
        return personalScoreList;
    }
}
