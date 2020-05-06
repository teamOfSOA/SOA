package main.java;

public class StudentScore {
    private String studentId;
    private String courseId;
    private String scoreType;
    private String score;

    public StudentScore(String studentId, String courseId, String scoreType, String score){
        this.studentId=studentId;
        this.courseId=courseId;
        this.scoreType=scoreType;
        this.score=score;
    }

    public String getStudentId() {
        return studentId;
    }

    public String getCourseId() {
        return courseId;
    }

    public String getScoreType() {
        return scoreType;
    }

    public String getScore() {
        return score;
    }
}
