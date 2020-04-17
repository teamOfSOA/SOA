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
 * 统计一共有多少个Text节点
 * @author licheng
 *
 */
public class JAXPText {

    /**
     *主函数
     */
    public static void main(String[] args) {
        GiveData give=new GiveData();
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder domParser=factory.newDocumentBuilder();

            Document document=domParser.parse(new File("bookinfo.xml"));
            NodeList nodeList=document.getChildNodes();
            give.output(nodeList);
            System.out.println("一共有"+give.m+"个Text节点");
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }


    }

}

class GiveData{
    int m=0; //text的个数
    public   void output(NodeList nodelist){
        int size=nodelist.getLength();  //获取接点列表的长度
        for(int k=0;k<size;k++){
            Node node=nodelist.item(k); //获取节点列表中的一项 
            if(node.getNodeType()==node.TEXT_NODE){ //节点类型为TEXT
                Text textNode=(Text)node;
                String content=textNode.getWholeText();
                m++;
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