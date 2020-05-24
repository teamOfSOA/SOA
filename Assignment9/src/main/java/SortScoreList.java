import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;

@XmlRootElement(name = "课程成绩列表")
public class SortScoreList {
    private ArrayList<ClassScore> classScores;

    public SortScoreList() {
    }

    public SortScoreList(ArrayList<ClassScore> classScores) {
        this.classScores = classScores;

    }

    @XmlElement(name = "课程成绩", namespace = "http://jw.nju.edu.cn/schema")
    public void setClassScores(ArrayList<ClassScore> classScores) {
        this.classScores = classScores;
    }


    public ArrayList<ClassScore> getClassScores() {
        return classScores;
    }


}
