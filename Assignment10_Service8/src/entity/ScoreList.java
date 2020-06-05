package entity;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

public class ScoreList {

    private ArrayList<ClassScore> classScores;

    public ScoreList() {
    }

    public ScoreList(ArrayList<ClassScore> classScores) {
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
