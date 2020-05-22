import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class PersonalScoreList {
    private ScoreList scoreList;

    public PersonalScoreList() {
    }

    public PersonalScoreList(ScoreList scoreList) {
        this.scoreList = scoreList;
    }

    @XmlElement(name = "课程成绩列表", namespace = "http://jw.nju.edu.cn/schema")
    public void setScoreList(ScoreList scoreList) {
        this.scoreList = scoreList;
    }

    public ScoreList getScoreList() {
        return scoreList;
    }

}
