package example;
import org.apache.axis.client.Service;
import org.apache.axis.client.Call;
import javax.xml.namespace.QName;
import java.util.Scanner;

public class ClientTest {
  public static void main(String[] argv) {
      Service service = new Service();
      String url = "http://localhost:8080/Demo_war_exploded/services/HelloWorld?wsdl";

      try {
          Call call = (Call)service.createCall();
          call.setTargetEndpointAddress(new java.net.URL(url));
          call.setOperationName(new QName("http://example","sendMailTo"));
          call.addParameter(new QName("from"),org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
          call.setUseSOAPAction(true);
          call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
          String result = (String)call.invoke(new Object[]{"1157982786@qq.com"});

          System.out.println(result);

      } catch (Exception e) {
          e.printStackTrace();
      }

      try {
          Call call = (Call)service.createCall();
          call.setTargetEndpointAddress(new java.net.URL(url));
          call.setOperationName(new QName("http://example","checkCode"));
          call.addParameter(new QName("from"),org.apache.axis.encoding.XMLType.XSD_STRING, javax.xml.rpc.ParameterMode.IN);
          call.setUseSOAPAction(true);
          call.setReturnType(org.apache.axis.encoding.XMLType.SOAP_STRING);
          Scanner scanner = new Scanner(System.in);
          String inputCode = scanner.nextLine();
          String result = (String)call.invoke(new Object[]{"1157982786@qq.com:"+inputCode});
          System.out.println(result);

      }catch (Exception e){
          e.printStackTrace();
      }
  }
}
