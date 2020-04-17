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
 * 通过已经存在的文件 获取Document对象
 * 修改DOM后
 * DOM创建XML文件
 * @author licheng
 *
 */
public class JAXPTransformer {
    public static void main(String[] args) {

        ModifyNode modify=new ModifyNode();
        try {
            DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
            factory.setIgnoringElementContentWhitespace(true);  //忽略空白缩进
            DocumentBuilder domParser=factory.newDocumentBuilder();
            Document document=domParser.parse(new File("mobileNumber.xml")); //通过已经存在的文件创建Document对象
            Element root=document.getDocumentElement();
            NodeList nodeList=root.getChildNodes();
            modify.modifyNode(nodeList, document); //调用修改DOM的方法
            TransformerFactory transFactory=TransformerFactory.newInstance(); //工厂对象获取transFactory实例
            Transformer transformer=transFactory.newTransformer(); //获取Transformer实例
            DOMSource domSource=new DOMSource(document);
            File file=new File("newXML.xml");
            FileOutputStream out=new FileOutputStream(file);
            StreamResult xmlResult=new StreamResult(out);
            transformer.transform(domSource, xmlResult);

            out.close();
        } catch (Exception e) {
            e.printStackTrace();
        }


    }
}







class ModifyNode{
    int m=0;
    Document document;
    public  void modifyNode(NodeList nodeList,Document document){
        this.document=document;
        int size=nodeList.getLength();
        for(int k=0;k<size;k++){
            Node node=nodeList.item(k);
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element elementNode=(Element)node;  //这里获取节点
                String name=elementNode.getNodeName();//节点名称
                if(name.equals("用户")){ //节点判断
                    m++;
                    Node textN=document.createTextNode("80元"); //创建文本节点
                    Node elementN=document.createElement("月租费"); //穿件节点
                    elementN.appendChild(textN);
                    elementNode.appendChild(elementN);
                }
                NodeList nodes=elementNode.getChildNodes();
                modifyNode(nodes, document); //此处递归
            }
        }

    }

}