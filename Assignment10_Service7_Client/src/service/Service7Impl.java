
package service;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebService(name = "service7Impl", targetNamespace = "http://service/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface Service7Impl {


    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws JAXBException_Exception
     * @throws DataException_Exception
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "update", targetNamespace = "http://service/", className = "service.Update")
    @ResponseWrapper(localName = "updateResponse", targetNamespace = "http://service/", className = "service.UpdateResponse")
    @Action(input = "http://service/service7Impl/updateRequest", output = "http://service/service7Impl/updateResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://service/service7Impl/update/Fault/IOException"),
        @FaultAction(className = JAXBException_Exception.class, value = "http://service/service7Impl/update/Fault/JAXBException"),
        @FaultAction(className = DataException_Exception.class, value = "http://service/service7Impl/update/Fault/dataException")
    })
    public String update(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        int arg3)
        throws DataException_Exception, IOException_Exception, JAXBException_Exception
    ;

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws JAXBException_Exception
     * @throws DataException_Exception
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "delete", targetNamespace = "http://service/", className = "service.Delete")
    @ResponseWrapper(localName = "deleteResponse", targetNamespace = "http://service/", className = "service.DeleteResponse")
    @Action(input = "http://service/service7Impl/deleteRequest", output = "http://service/service7Impl/deleteResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://service/service7Impl/delete/Fault/IOException"),
        @FaultAction(className = JAXBException_Exception.class, value = "http://service/service7Impl/delete/Fault/JAXBException"),
        @FaultAction(className = DataException_Exception.class, value = "http://service/service7Impl/delete/Fault/dataException")
    })
    public String delete(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2)
        throws DataException_Exception, IOException_Exception, JAXBException_Exception
    ;

    /**
     * 
     * @param arg3
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws JAXBException_Exception
     * @throws DataException_Exception
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "create", targetNamespace = "http://service/", className = "service.Create")
    @ResponseWrapper(localName = "createResponse", targetNamespace = "http://service/", className = "service.CreateResponse")
    @Action(input = "http://service/service7Impl/createRequest", output = "http://service/service7Impl/createResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://service/service7Impl/create/Fault/IOException"),
        @FaultAction(className = DataException_Exception.class, value = "http://service/service7Impl/create/Fault/dataException"),
        @FaultAction(className = JAXBException_Exception.class, value = "http://service/service7Impl/create/Fault/JAXBException")
    })
    public String create(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2,
        @WebParam(name = "arg3", targetNamespace = "")
        int arg3)
        throws DataException_Exception, IOException_Exception, JAXBException_Exception
    ;

    /**
     * 
     * @param arg2
     * @param arg1
     * @param arg0
     * @return
     *     returns java.lang.String
     * @throws JAXBException_Exception
     * @throws DataException_Exception
     * @throws IOException_Exception
     */
    @WebMethod
    @WebResult(targetNamespace = "")
    @RequestWrapper(localName = "retrieve", targetNamespace = "http://service/", className = "service.Retrieve")
    @ResponseWrapper(localName = "retrieveResponse", targetNamespace = "http://service/", className = "service.RetrieveResponse")
    @Action(input = "http://service/service7Impl/retrieveRequest", output = "http://service/service7Impl/retrieveResponse", fault = {
        @FaultAction(className = IOException_Exception.class, value = "http://service/service7Impl/retrieve/Fault/IOException"),
        @FaultAction(className = JAXBException_Exception.class, value = "http://service/service7Impl/retrieve/Fault/JAXBException"),
        @FaultAction(className = DataException_Exception.class, value = "http://service/service7Impl/retrieve/Fault/dataException")
    })
    public String retrieve(
        @WebParam(name = "arg0", targetNamespace = "")
        String arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        String arg1,
        @WebParam(name = "arg2", targetNamespace = "")
        String arg2)
        throws DataException_Exception, IOException_Exception, JAXBException_Exception
    ;

}
