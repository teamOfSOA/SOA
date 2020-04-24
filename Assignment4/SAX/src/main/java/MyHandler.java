import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import java.util.ArrayList;
import java.util.List;

public class MyHandler extends DefaultHandler {

    public List<CourseGrade> l=new ArrayList<CourseGrade>();
    public List<CourseGrade> res=new ArrayList<CourseGrade>();
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
                CourseGrade cg=l.get(l.size()-1);
                cg.grade.score=score;
                l.set(l.size()-1,cg);
            }
        }
    }

    @Override
    public void endDocument() throws SAXException {
        for(int i=0;i<l.size();){
            if(l.get(i).grade.score<60){
                switch(i%4){
                    case 0:
                        res.add(l.get(i));res.add(l.get(i+1));res.add(l.get(i+2));res.add(l.get(i+3));
                        i+=4;
                        break;
                    case 1:
                        res.add(l.get(i-1));res.add(l.get(i));res.add(l.get(i+1));res.add(l.get(i+2));
                        i+=3;
                        break;
                    case 2:
                        res.add(l.get(i-2));res.add(l.get(i-1));res.add(l.get(i));res.add(l.get(i+1));
                        i+=2;
                        break;
                    case 3:
                        res.add(l.get(i-3));res.add(l.get(i-2));res.add(l.get(i-1));res.add(l.get(i));
                        i++;
                        break;
                }
            }
            else{i++;}
        }
    }
}
