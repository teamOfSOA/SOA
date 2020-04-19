import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import org.dom4j.Document;
import org.dom4j.DocumentHelper;
import org.dom4j.Element;
import org.dom4j.Namespace;
import org.dom4j.io.OutputFormat;
import org.dom4j.io.XMLWriter;
import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;

public class DomToXML {

    private String[] names = {"余含章","勇中坚","夏汉仲","张建榕","陈凌晨","徐志威","殷承鉴","罗民胜",
            "苑宇航","杨日东","濮宗悦","章诚","雷媛"};
    private String[] ids = {"161250188","171250631","171250565","171250517","171250696","171250654",
            "171250661","171250670","171250573","171250558","171250025","171250682","171250001"};
    private String[] genders = {"male","male","male","male","male","male","male","male","male","male",
            "female","male","female",};
    private String[] ages = {"22","21","21","21","21","21","21","21","21","21","21","21","21"};
    private String[] scoreType = {"平时成绩","作业成绩","期末成绩","总评成绩"};
    private int Length = names.length;

    /**
     * 使用DOM建立xml文档
     * @param xmlPath xml文档路径
     */
    private void createXML(String xmlPath){
        //创建document对象，代表整个xml文档
        Document StudentList = DocumentHelper.createDocument();
        //创建根节点root并且添加命名空间
        Element root = StudentList.addElement("学生列表","http://jw.nju.edu.cn/schema");
        root.addNamespace("xsi","http://www.w3.org/2001/XMLSchema-instance");
        root.addNamespace("nju","http://www.nju.edu.cn/schema");
        root.addAttribute("xsi:schemaLocation","http://jw.nju.edu.cn/schema StudentList.xsd");
        //添加子元素
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
        //设置生成xml的格式
        OutputFormat format = OutputFormat.createPrettyPrint();
        format.setEncoding("UTF-8");
        //生成xml文件
        File file = new File(xmlPath);
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
     * 验证建立的xml文档
     * @param xsdPath schema脚本文件路径
     * @param xmlPath 生成的xml文件路径
     */
    private void compareXSD(String xsdPath,String xmlPath){
        //建立schema工厂
        SchemaFactory schemaFactory=SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        //建立验证文档文件对象，利用此文件对象所封装的文件进行schema验证
        File schemaFile=new File(xsdPath);
        //利用schema工厂，接收验证文档文件对象生成Schema对象
        try{
            Schema schema=schemaFactory.newSchema(schemaFile);
            //通过Schema产生针对于此Schema的验证器，利用schemaFile进行验证
            Validator validator=schema.newValidator();
            //得到验证的数据源
            Source source=new StreamSource(xmlPath);
            //开始验证，成功输出success!!!，失败输出fail
            try{
                validator.validate(source);
                System.out.println("数据校验成功!");
            }catch(Exception ex){

                String error=ex.getMessage();
                error = error.substring(error.indexOf("valid.1.2.1:")+12);
                System.out.println(error);
            }
        }
        catch (SAXException ex){
            ex.printStackTrace();
        }
    }

    public static void main(String[] args) {
        //生成的xml文件路径
        String xmlPath = "StudentList.xml";
        //匹配的xsd文件路径
        String xsdPath = "StudentList.xsd";
        //创建xml文档
        new DomToXML().createXML(xmlPath);
        //验证xml文档
        new DomToXML().compareXSD(xsdPath,xmlPath);
    }

}
