import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;


/**
 * 递归方法输出节点中的数据
 * @author licheng
 *
 */
public class JAXPTwo {

    /**
     *主函数
     */
    public static void main(String[] args) {
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder domParser=factory.newDocumentBuilder();

            Document document=domParser.parse(new File("bookinfo.xml"));
            NodeList nodeList=document.getChildNodes();
            output(nodeList);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }


    /**
     * 递归输出节点数据
     * @param nodelist 节点列表
     */
    public static  void output(NodeList nodelist){
        int size=nodelist.getLength();  //获取接点列表的长度
        for(int k=0;k<size;k++){
            Node node=nodelist.item(k); //获取节点列表中的一项 
            if(node.getNodeType()==node.TEXT_NODE){ //节点类型为TEXT
                Text textNode=(Text)node;
                String content=textNode.getWholeText();
                System.out.print(content);
            }
            if(node.getNodeType()==Node.ELEMENT_NODE){ //节点类型为ELEMENT
                Element elementNode=(Element)node;
                String name=elementNode.getNodeName();
                System.out.print(name);
                NodeList nodes=elementNode.getChildNodes();
                output(nodes);  //递归掉用该方法
            }
        }

    }

}