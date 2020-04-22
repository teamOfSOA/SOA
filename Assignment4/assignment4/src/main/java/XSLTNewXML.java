import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

public class XSLTNewXML {
    /**
     * 使用XSLT转换XML文件
     * @param args 命令行参数
     */
    public static void main(String[] args) {
        TransformerFactory tf = TransformerFactory.newInstance();
        try{
            Transformer transformer=tf.newTransformer(new StreamSource("src/main/resources/ScoreList.xsl"));
            transformer.transform(new StreamSource("src/main/resources/test1.xml"),
                    new StreamResult("src/main/resources/test2.xml"));
        } catch (TransformerConfigurationException e) {
            e.printStackTrace();
        } catch (TransformerException e) {
            e.printStackTrace();
        }
    }

}
