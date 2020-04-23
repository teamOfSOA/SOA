import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Result;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.sax.SAXTransformerFactory;
import javax.xml.transform.sax.TransformerHandler;
import javax.xml.transform.stream.StreamResult;

import org.xml.sax.SAXException;
import org.xml.sax.helpers.AttributesImpl;

public class SAX {
    public static void main(String[] args){
        try{
//            解析scoreList.xml，具体就是让MyHandler继承API的DefaultHandler，并重写其中的方法来筛选不合格的成绩
            SAXParserFactory saxParserFactory=SAXParserFactory.newInstance();
            SAXParser saxParser=saxParserFactory.newSAXParser();
            MyHandler mh=new MyHandler();
            saxParser.parse("src/main/resources/ScoreList.xml",mh);
//            经过筛选的不合格的成绩生成XML4.xml
            SAXTransformerFactory factory = (SAXTransformerFactory)SAXTransformerFactory.newInstance();
            TransformerHandler handler = factory.newTransformerHandler();
            Transformer transformer = handler.getTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty(OutputKeys.ENCODING, "utf-8");
            Result result = new StreamResult(new FileOutputStream(new File("src/main/resources/XML4.xml")));
            handler.setResult(result);
            AttributesImpl attr = new AttributesImpl();
            attr.clear();
            attr.addAttribute("", "", "xmlns", "", "http://jw.nju.edu.cn/schema");
            handler.startDocument();
            handler.characters("\n".toCharArray(),0,"\n".length());
            handler.startElement("", "", "课程成绩列表", attr);
            for(CourseGrade cg:mh.res){
                attr.clear();
                attr.addAttribute("","","课程编号","",cg.courseId);
                attr.addAttribute("","","成绩性质","",cg.type);
                handler.characters("\n    ".toCharArray(),0,"\n    ".length());
                handler.startElement("","","课程成绩",attr);
                attr.clear();
                handler.characters("\n        ".toCharArray(),0,"\n        ".length());
                handler.startElement("","","成绩",attr);
                handler.characters("\n            ".toCharArray(),0,"\n            ".length());
                handler.startElement("","","学号",attr);
                handler.characters(cg.grade.studentId.toCharArray(),0,cg.grade.studentId.length());
                handler.endElement("","","学号");
                handler.characters("\n            ".toCharArray(),0,"\n            ".length());
                handler.startElement("","","得分",attr);
                handler.characters(Integer.toString(cg.grade.score).toCharArray(),0,Integer.toString(cg.grade.score).length());
                handler.endElement("","","得分");
                handler.characters("\n        ".toCharArray(),0,"\n        ".length());
                handler.endElement("","","成绩");
                handler.characters("\n    ".toCharArray(),0,"\n    ".length());
                handler.endElement("","","课程成绩");
            }
            handler.endElement("","","课程成绩列表");
            handler.endDocument();

        }catch (ParserConfigurationException e){
            e.printStackTrace();
        }catch(SAXException e){
            e.printStackTrace();
        }catch (IOException e){
            e.printStackTrace();
        }catch (TransformerConfigurationException e){
            e.printStackTrace();
        }
    }
}

