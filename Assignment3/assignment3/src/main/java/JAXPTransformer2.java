import java.io.File;
import java.io.FileOutputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;


/**
 * domPaser 调用 newDocument() 方法获取Document对象
 * 然后为Document节点添加子孙节点
 * 使用Transformer生成一个新的XML文件
 * @author licheng
 *
 */
public class JAXPTransformer2 {
    public static void main(String[] args) {
        try {
            String[] personName={"张三","李四","王五"};
            String[] phoneNumber={"123","456","789"};
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);  //忽略空白缩进
            DocumentBuilder domParser=factory.newDocumentBuilder();
            Document document=domParser.newDocument(); //通过调用newDocument() 方法获取实例
            document.setXmlVersion("1.0"); //设置 xml版本号
            Element root=document.createElement("手机用户表");
            document.appendChild(root); //添加根节点
            for(int k=1;k<=personName.length;k++){
                Node node=document.createElement("用户"); //添加多个用户节点
                root.appendChild(node);
            }
            NodeList nodeList=document.getElementsByTagName("用户");
            int size=nodeList.getLength();
            for(int k=0;k<size;k++){
                Node node=nodeList.item(k);
                if(node.getNodeType()==Node.ELEMENT_NODE){
                    Element elementNode=(Element)node; //有必要创建此对象吗???
                    Node nodeName=document.createElement("姓名");
                    Node nodeNumber=document.createElement("号码");
                    nodeName.appendChild(document.createTextNode(personName[k]));
                    nodeNumber.appendChild(document.createTextNode(phoneNumber[k]));
                    elementNode.appendChild(nodeName);
                    elementNode.appendChild(nodeNumber);
                }
            }
            TransformerFactory transFactory=TransformerFactory.newInstance(); //工厂对象获取transFactory实例
            Transformer transformer=transFactory.newTransformer(); //获取Transformer实例
            DOMSource domSource=new DOMSource(document);
            File file=new File("phone.xml");
            FileOutputStream out=new FileOutputStream(file);
            StreamResult xmlResult=new StreamResult(out);
            transformer.transform(domSource, xmlResult);
            out.close();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}