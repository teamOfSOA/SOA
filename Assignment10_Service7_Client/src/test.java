
import service.*;

public class test {
    public static void main(String[] args) throws IOException_Exception, JAXBException_Exception, DataException_Exception {
        Service7Impl test = new Service7ImplService().getService7ImplPort();
        String result = test.create("000001","总评成绩","171250573",0);
        System.out.println(result);
//        result=test.retrieve("000002","平时成绩","161250188");
//        System.out.println(result);
//        result=test.update("000001","总评成绩","171250573",11);
//        System.out.println(result);
//        result=test.delete("000001","总评成绩","171250573");
//        System.out.println(result);
    }
}
