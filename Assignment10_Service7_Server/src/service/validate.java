package service;

import entity.ClassScore;

import java.util.ArrayList;

public class validate {

    String msg1="\"课程编号\"格式错误，必须是六位数字";
    String msg2="\"成绩性质\"格式错误，必须是{\"平时成绩\"，\"作业成绩\"，\"期末成绩\"，\"总评成绩\"}中的一个";
    String msg3="\"学号\"格式错误，必须是九位数字";
    String msg4="\"得分\"数值错误，必须在[0，100]之间";
    String msg5="未找到要求的成绩对象";

    boolean b1=true;
    boolean b2=true;
    boolean b3=true;
    boolean b4=true;

    private void setb1(String courseID){
        if(courseID.length()!=6){
            b1=false;
        }
        else {
            for (int i = 0; i < courseID.length(); i++) {
                if (courseID.charAt(i) < 48 || courseID.charAt(i) > 57) {
                    b1 = false;
                    break;
                }
            }
        }
    }

    private void setb2(String type){
        if(!type.equals("平时成绩")&&!type.equals("作业成绩")&&!type.equals("期末成绩")&&!type.equals("总评成绩")){
            b2=false;
        }
    }

    private void setb3(String studentID){
        if(studentID.length()!=9){
            b3=false;
        }
        else {
            for (int i = 0; i < studentID.length(); i++) {
                if (studentID.charAt(i) < 48 || studentID.charAt(i) > 57) {
                    b3 = false;
                    break;
                }
            }
        }
    }

    private void setb4(int score){
        if(score<0||score>100){b4=false;}
    }

    private void reset(){
        b1=true;
        b2=true;
        b3=true;
        b4=true;
    }

    public void validate1(String courseID,String type,String studentID,int score) throws dataException {
        reset();
        setb1(courseID);
        setb2(type);
        setb3(studentID);
        setb4(score);

        if(!b1){
            throw new dataException(msg1,new faultinfo(msg1));
        }
        if(!b2){
            throw new dataException(msg2,new faultinfo(msg2));
        }
        if(!b3){
            throw new dataException(msg3,new faultinfo(msg3));
        }
        if(!b4){
            throw new dataException(msg4,new faultinfo(msg4));
        }
    }

    public void validate2(String courseID,String type,String studentID) throws dataException {
        reset();
        setb1(courseID);
        setb2(type);
        setb3(studentID);

        if(!b1){
            throw new dataException(msg1,new faultinfo(msg1));
        }
        if(!b2){
            throw new dataException(msg2,new faultinfo(msg2));
        }
        if(!b3){
            throw new dataException(msg3,new faultinfo(msg3));
        }
    }

    public void validate3(String courseID, String type, String studentID, ArrayList<ClassScore> courseGrades)throws dataException{
        boolean res=false;
        for(int i=0;i<courseGrades.size();i++){
            ClassScore c=courseGrades.get(i);
            if(c.getClassId().equals(courseID)&&c.getScoreAttribute().equals(type)&&c.getScore().getStudentId().equals(studentID)){
                res=true;
                break;
            }
        }
        if(!res) {
            throw new dataException(msg4,new faultinfo(msg5));
        }
    }
}
