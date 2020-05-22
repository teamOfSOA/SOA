import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class ClassScore {

    private Score score;
    private String classId;
    private String scoreAttribute;

    public ClassScore() {
    }

    public ClassScore(Score score, String classId, String scoreAttribute) {
        this.score = score;
        this.classId = classId;
        this.scoreAttribute = scoreAttribute;
    }

    @XmlElement(name = "成绩", namespace = "http://jw.nju.edu.cn/schema")
    public void setScore(Score score) {
        this.score = score;
    }

    @XmlAttribute(name = "课程编号")
    public void setClassId(String classId) {
        this.classId = classId;
    }

    @XmlAttribute(name = "成绩性质")
    public void setScoreAttribute(String scoreAttribute) {
        this.scoreAttribute = scoreAttribute;
    }

    public Score getScore() {
        return score;
    }

    public String getClassId() {
        return classId;
    }

    public String getScoreAttribute() {
        return scoreAttribute;
    }

}
