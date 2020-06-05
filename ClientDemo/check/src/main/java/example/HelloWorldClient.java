package example;

import com.HelloWorldServiceLocator;
import com.HelloWorld_PortType;

import java.util.Scanner;

public class HelloWorldClient {
  public static void main(String[] argv) {
      try {
          HelloWorldServiceLocator locator = new HelloWorldServiceLocator();
          HelloWorld_PortType service = locator.getHelloWorld();
          Scanner scanner = new Scanner(System.in);
          String targetAddress = scanner.nextLine();
          System.out.println(service.sendMailTo(targetAddress));

          String inputCode = scanner.nextLine();
          System.out.println(service.checkCode(inputCode));

      } catch (javax.xml.rpc.ServiceException ex) {
          ex.printStackTrace();
      } catch (java.rmi.RemoteException ex) {
          ex.printStackTrace();
      }  
  }
}
