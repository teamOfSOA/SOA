import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class StudentInfo {

    private PersonInfo personInfo;

    public StudentInfo() {
    }

    public StudentInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    @XmlElement(name = "个人信息", namespace = "http://jw.nju.edu.cn/schema")
    public void setPersonInfo(PersonInfo personInfo) {
        this.personInfo = personInfo;
    }

    public PersonInfo getPersonInfo() {
        return personInfo;
    }

}
