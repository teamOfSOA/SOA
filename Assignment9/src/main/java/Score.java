import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

@XmlRootElement
@XmlType(propOrder = {"studentId", "grade"})
public class Score {

    private String studentId;
    private int grade;

    public Score() {
    }

    public Score(String studentId, int grade) {
        this.studentId = studentId;
        this.grade = grade;
    }

    @XmlElement(name = "学号", namespace = "http://jw.nju.edu.cn/schema")
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    @XmlElement(name = "得分", namespace = "http://jw.nju.edu.cn/schema")
    public void setGrade(int grade) {
        this.grade = grade;
    }


    public String getStudentId() {
        return studentId;
    }

    public int getGrade() {
        return grade;
    }

}
