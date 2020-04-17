import java.io.File;
import java.io.IOException;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


/**
 * 检查命xml标记的命名空间
 * @author licheng
 *
 */
public class TestNamespace {
    /**
     * @param args
     */
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        SAXParserFactory factory=SAXParserFactory.newInstance(); //语法解析器的工厂对象
        factory.setNamespaceAware(true); //允许使用命名空间
        try {
            SAXParser saxParser=factory.newSAXParser();  //获取语法解析器
            MyHeader handle=new MyHeader(); //创建输出句柄
            saxParser.parse(new File("student.xml"), handle); //开始语法解析   文件放到项目根目录不是WebRoot
        }  catch (IOException e) {  //抛出异常
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        }
    }

}

/**
 * 创建输出句柄对象
 * @author Administrator
 *
 */
class MyHeader extends DefaultHandler{
    @Override //覆盖父类的方法
    public void startElement(String uri, String localName, String qName,
                             Attributes attributes) throws SAXException {
        if(uri.length()>0)
            System.out.println("标记:"+localName+"的命名空间是:"+uri);
        else
            System.out.println("标记:"+localName+"没有命名空间");
    }
}