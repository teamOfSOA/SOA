import service.*;

public class test {
    public static void main(String[] args) throws JAXBException_Exception, IOException_Exception {
        Service8Impl service8 = new Service8ImplService().getService8ImplPort();
        String result = service8.create("171250654", "徐志威", "男", 21);
        System.out.println(result);
    }
}
