import service.Service8;
import service.Service8Impl;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Endpoint;
import java.io.IOException;

public class App {
    public static void main(String[] args) throws JAXBException, IOException {
        Service8 s=new Service8Impl();
//        s.create("000001","总评成绩","171250573",0);
//        s.retrieve("000002","平时成绩","161250188");
//        s.update("000001","总评成绩","171250573",11);
//        s.delete("000001","总评成绩","171250573");

        String address = "http://localhost:8990/service8";
        Endpoint.publish(address,s);
        System.out.println("成功");


//        Service7Impl test = new Service7ImplService().getService7ImplPort();
//        String result = test.create("000001","总评成绩","171250573",0);
//        System.out.println(result);
    }
}
