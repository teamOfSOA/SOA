import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.xml.sax.SAXException;
import org.xml.sax.SAXParseException;
import org.xml.sax.helpers.DefaultHandler;

/**
 * 检查XML文件的有效性
 * 浏览器中的XML解析器只检查XML文件是否是规范的,并不检查XML文件是否遵守DTD规定的约束条件。
 * 此时就可以使用DOM解析器来检查一个XML文件是否是有效的。
 * @author licheng
 *
 */
public class TestValidate {


    public static void main(String[] args) {
        String fileName=null;
        Scanner reader=new Scanner(System.in);
        System.out.print("请输入要验证有效性的XML的文件:");
        fileName=reader.nextLine();  //从控制台读取一行数据
        DocumentBuilderFactory factory=DocumentBuilderFactory.newInstance();  //DocumentBuilderFactory工厂对象 获取自身实例 
        factory.setValidating(true);  //设置有效性检测为真
        try {
            DocumentBuilder builer=factory.newDocumentBuilder();//获取DOM解析器
            MyHandler handler=new MyHandler(); //创建MyHandler实例
            builer.setErrorHandler(handler); // 设置解析器的错误句柄为 MyHandler的实例
            Document document=builer.parse(new File(fileName)); //DOM解析器解析 XML文件
            if(handler.errorMessage==null){   //判断handler对象是否含有错误信息
                System.out.print(fileName+"文件是效的");
            }else{
                System.out.print(fileName+"文件是无效的");
            }

        } catch (IOException e) {
            e.printStackTrace();
        } catch (ParserConfigurationException e) {
            e.printStackTrace();
        } catch (SAXException e) {
            e.printStackTrace();
        }

    }

}

/**
 * 创建输出句柄
 * @author Administrator
 *
 */
class MyHandler extends DefaultHandler{

    String errorMessage=null;
    /**
     * 一般性错误
     */
    public void error(SAXParseException e) throws SAXException {
        errorMessage=e.getMessage();
        System.out.print("一般错误:"+ errorMessage);
    }

    /*
     *
     * 致命错误 程序终止
     */
    public void fatalError(SAXParseException e) throws SAXException {
        errorMessage=e.getMessage();
        System.out.print("致命错误:"+ errorMessage);
    }


}