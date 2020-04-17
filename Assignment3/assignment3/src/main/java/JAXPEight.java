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




public class JAXPEight {


    public static void main(String[] args) {
        GiveD give=new GiveD();
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();
        factory.setIgnoringElementContentWhitespace(true); //这个确实是可行的吗？？
        try {
            DocumentBuilder domPaser=factory.newDocumentBuilder();
            Document document=domPaser.parse("student.xml");
            Element root=document.getDocumentElement();
            NodeList nodeList=root.getChildNodes();

            give.output(nodeList);

        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }
    }

}


class GiveD{
    int m=0;
    public void output(NodeList nodeList){
        int size=nodeList.getLength();
        for(int k=0;k<size;k++){
            Node node=nodeList.item(k);
            if(node.getNodeType()==Node.TEXT_NODE){
                Text textNode=(Text)node;
                String content=textNode.getWholeText();
                m++;
                System.out.print(content);
            }
            if(node.getNodeType()==Node.ELEMENT_NODE){
                Element elementNode=(Element)node;
                String name=elementNode.getNodeName();
                System.out.print(" ");
                NodeList nodes=elementNode.getChildNodes();
                output(nodes);
            }
        }
    }

}