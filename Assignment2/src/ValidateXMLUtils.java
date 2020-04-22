import org.xml.sax.SAXException;

import javax.xml.transform.Source;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ValidateXMLUtils {
    /**
     * 根据xsd文件验证xml文件是否符合规则
     * @param xsdpath
     * @param xmlpath
     * @return
     * @throws SAXException
     * @throws IOException
     */
    public static Map<String,Object>   Validatexml(String xmlpath,String xsdpath) throws SAXException,IOException{
        Map<String,Object> result = new HashMap<String,Object>();
        //建立schema工厂
        SchemaFactory schemaFactory=SchemaFactory.newInstance("http://www.w3.org/2001/XMLSchema");
        //建立验证文档文件对象，利用此文件对象所封装的文件进行schema验证
        File schemaFile=new File(xsdpath);
        //利用schema工厂，接收验证文档文件对象生成Schema对象
        Schema schema=schemaFactory.newSchema(schemaFile);
        //通过Schema产生针对于此Schema的验证器，利用schenaFile进行验证
        Validator validator=schema.newValidator();
        //得到验证的数据源
        Source source=new StreamSource(xmlpath);
        //开始验证，成功输出success!!!，失败输出fail
        try{
            validator.validate(source);
            result.put("status", 1);
            result.put("message", "数据校验成功");
            return result;
        }catch(Exception ex){
            ex.printStackTrace();
            String error=ex.getMessage();
            error = error.substring(error.indexOf("valid.1.2.1:")+12,error.length());
            result.put("status", 0);
            result.put("message", error);
            return result;

        }
    }
    public static void main(String[] args) {
        String xsdpath="ScoreList.xsd";
        String xmlpath="ScoreList.xml";
        try {
            Map<String, Object> validatexml = Validatexml(xmlpath,xsdpath);
            System.out.println(validatexml);//{message=数据校验成功, status=1}
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
