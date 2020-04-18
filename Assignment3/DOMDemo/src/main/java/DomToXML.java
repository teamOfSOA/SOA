import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import com.sun.xml.internal.bind.v2.model.core.EnumLeafInfo;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;

public class DomToXML {

    private String[] names = {"name1","name2","name3","name4","name5"};
    private String[] ids = {"171250573","171250573","171250573","171250573","171250573"};
    private String[] genders = {"male","male","male","male","male"};
    private String[] ages = {"21","21","21","21","21","21"};
    private String[] scoreType = {"平时成绩","作业成绩","期末成绩","总评成绩"};
    private int Length = names.length;

    private void createXML(){
        //1.创建document对象，代表整个xml文档
        Document StudentList = DocumentHelper.createDocument();
        //2.创建根节点root
        Element root = StudentList.addElement("学生列表","http://jw.nju.edu.cn/schema");
        //3.向rss节点中添加version属性
        root.addNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
        root.addNamespace("nju","http://www.nju.edu.cn/schema");
        root.addAttribute("xsi:schemaLocation","http://jw.nju.edu.cn/schema StudentList.xsd");

        for(int n = 0;n<Length;n++){
            Namespace namespace = root.getNamespace();
            Element Student = root.addElement("学生",namespace.getURI());
            Student.setParent(root);
            Element Id = Student.addElement("学号");
            Id.setText(ids[n]);
            Element StudentInfo = Student.addElement("学生信息");
            Element PersonInfo = StudentInfo.addElement("个人信息");
            Element name = PersonInfo.addElement("nju:姓名");
            name.setText(names[n]);
            Element gender = PersonInfo.addElement("nju:性别");
            gender.setText(genders[n]);
            Element age = PersonInfo.addElement("nju:年龄");
            age.setText(ages[n]);
            Element DepartList = PersonInfo.addElement("nju:所在部门列表");
            for(int i=0;i<2;i++){
                Element department = DepartList.addElement("nju:部门");
                department.setText("00000"+i);
            }
            Element courseList = Student.addElement("参与课程列表");
            for(int i=0;i<5;i++){
                Element course = courseList.addElement("课程编号");
                course.setText("00000"+i);
            }
            Element studentScoreList = Student.addElement("个人成绩列表");
            Element scoreList = studentScoreList.addElement("课程成绩列表");
            for(int i=0;i<5;i++){
                for(int j=0;j<4;j++){
                    Element courseScore = scoreList.addElement("课程成绩");
                    courseScore.addAttribute("课程编号","00000"+i);
                    courseScore.addAttribute("成绩性质",scoreType[j]);
                    Element score = courseScore.addElement("成绩");
                    Element sid = score.addElement("学号");
                    sid.setText(ids[n]);
                    Element marks = score.addElement("得分");
                    int mark = (int) (50+Math.random()*(100-50+1));
                    marks.setText(mark+"");
                }
            }
        }
        //5.设置生成xml的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        //6.生成xml文件
        File file = new File("StudentList.xml");
        XMLWriter writer;
        try {
            writer = new XMLWriter(new FileOutputStream(file), format);
            //设置是否转义，默认值是true，代表转义
            writer.setEscapeText(false);
            writer.write(StudentList);
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * @param args
     */
    public static void main(String[] args) {
        new DomToXML().createXML();
    }

}
