package service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;

@WebService
public interface Service8 {
    @WebMethod
    String create(String studentID,String name,String gender,int age) throws JAXBException, IOException;
    @WebMethod
    String retrieve(String studentID) throws JAXBException, IOException;
    @WebMethod
    String update(String studentID,String name,String gender,int age) throws JAXBException, IOException;
    @WebMethod
    String delete(String studentID) throws JAXBException, IOException;
}
