package main.java;
import service.*;

import javax.xml.bind.JAXBException;
import javax.xml.ws.Endpoint;
import java.io.IOException;

public class app {
    public static void main(String[] args) throws JAXBException, IOException, dataException {
        service7 s=new service7Impl();
//        s.create("000001","总评成绩","171250573",0);
//        s.retrieve("000002","平时成绩","161250188");
//        s.update("000001","总评成绩","171250573",11);
//        s.delete("000001","总评成绩","171250573");

        String address = "http://localhost:8989/service7";
        Endpoint.publish(address,s);
        System.out.println("发布成功");


//        Service7Impl test = new Service7ImplService().getService7ImplPort();
//        String result = test.create("000001","总评成绩","171250573",0);
//        System.out.println(result);
    }
}
