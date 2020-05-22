import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement
public class ClassList {

    private ArrayList<String> classes;

    public ClassList() {
    }

    public ClassList(ArrayList<String> classes) {
        this.classes = classes;
    }

    @XmlElement(name = "课程编号", namespace = "http://jw.nju.edu.cn/schema")
    public void setClasses(ArrayList<String> classes) {
        this.classes = classes;
    }

    public ArrayList<String> getClasses() {
        return classes;
    }

}
