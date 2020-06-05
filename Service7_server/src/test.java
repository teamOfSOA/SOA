
import service.IOException_Exception;
import service.JAXBException_Exception;
import service.Service7Impl;
import service.Service7ImplService;

public class test {
    public static void main(String[] args) throws JAXBException_Exception, IOException_Exception {
        Service7Impl test = new Service7ImplService().getService7ImplPort();
        String result = test.create("000001","总评成绩","171250573",0);
        System.out.println(result);
    }
}
