import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;


/**
 * 获取XML声明信息,根节点名称，指定节点的信息
 * JAVA DOM解析器入门   
 * 输出书籍信息
 * @author licheng
 */
public class JAXPOne {


    public static void main(String[] args) {
        // DocumentBuilderFactory对象调用newInstance方法实例化一个DocumentBuilderFactory对象
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            //factory对象调用newDocumentBuilder创建 domParser DOM解析器
            DocumentBuilder domParser=factory.newDocumentBuilder();
            try {

                Document document=domParser.parse(new File("student.xml"));
                String version=document.getXmlVersion();  //获取XML版本号
                System.out.println("version:"+version);

                String encoding=document.getXmlEncoding(); //获取声明编码
                System.out.println("encoding:"+encoding);

                Element root=document.getDocumentElement(); //获取根节点 是先要获取根节点吗
                String rootName=root.getNodeName(); //获取节点的名称
                System.out.println("rootName:"+rootName);
                System.out.println(rootName+"类型为:"+root.getNodeType()); //获取节点类型 dom的级别

                NodeList nodelist=root.getElementsByTagName("book"); //获取节点列表
                int size=nodelist.getLength();
                for(int k=0;k<size;k++){
                    Node node=nodelist.item(k); //获取节点
                    String name=node.getNodeName(); //节点名称
                    String content=node.getTextContent(); //获取内容  包含子孙节点中的文本数据
                    System.out.println(name+":"+content);
                    //System.out.println(name+"节点类型:"+node.getNodeType()); //获取节点类型 dom的级别
                }

            } catch (SAXException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        } catch (ParserConfigurationException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

}