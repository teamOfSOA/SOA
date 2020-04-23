import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    public List<CourseGrade> l=new ArrayList<CourseGrade>();
    public String currentTag;

    public void startElement(String uri, String localName, String qName,
                             Attributes attributes)throws SAXException {
        currentTag=qName;
        if(qName.equals("课程成绩")){
            CourseGrade cg=new CourseGrade();
            cg.courseId=attributes.getValue(0);
            cg.type=attributes.getValue(1);
            l.add(cg);
        }
        else if(qName.equals("成绩")){
            Grade g=new Grade();
            CourseGrade cg=l.get(l.size()-1);
            cg.grade=g;
            l.set(l.size()-1,cg);
        }
    }

    public void characters(char[] ch, int start, int length)throws SAXException{
        String temp=new String(ch,start,length);
        if(Character.isDigit(temp.charAt(0))){
            if(currentTag.equals("学号")){
                CourseGrade cg=l.get(l.size()-1);
                cg.grade.studentId=temp;
                l.set(l.size()-1,cg);
            }
            else if(currentTag.equals("得分")){
                int score=Integer.parseInt(temp);
                if(score>=60){
                    l.remove(l.size()-1);
                }
                else{
                    CourseGrade cg=l.get(l.size()-1);
                    cg.grade.score=score;
                    l.set(l.size()-1,cg);
                }
            }
        }
    }

}
