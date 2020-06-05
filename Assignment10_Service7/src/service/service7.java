package service;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.xml.bind.JAXBException;
import java.io.IOException;
@WebService
public interface service7 {
    @WebMethod
    String create(String courseID,String type,String studentID,int score) throws JAXBException, IOException;
    @WebMethod
    String retrieve(String courseID,String type,String studentID) throws JAXBException, IOException;
    @WebMethod
    String update(String courseID,String type,String studentID,int newScore) throws JAXBException, IOException;
    @WebMethod
    String delete(String courseID,String type,String studentID) throws JAXBException, IOException;
}
