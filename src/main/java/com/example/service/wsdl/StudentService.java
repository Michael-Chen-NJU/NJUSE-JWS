
package com.example.service.wsdl;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.9-b130926.1035
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "StudentService", targetNamespace = "http://example.com/student", wsdlLocation = "file:/D:/Java_Project/SOA/NJUSE-JWS/src/main/resources/wsdl/StudentService.wsdl")
public class StudentService
    extends Service
{

    private final static URL STUDENTSERVICE_WSDL_LOCATION;
    private final static WebServiceException STUDENTSERVICE_EXCEPTION;
    private final static QName STUDENTSERVICE_QNAME = new QName("http://example.com/student", "StudentService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("file:/D:/Java_Project/SOA/NJUSE-JWS/src/main/resources/wsdl/StudentService.wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        STUDENTSERVICE_WSDL_LOCATION = url;
        STUDENTSERVICE_EXCEPTION = e;
    }

    public StudentService() {
        super(__getWsdlLocation(), STUDENTSERVICE_QNAME);
    }

    public StudentService(WebServiceFeature... features) {
        super(__getWsdlLocation(), STUDENTSERVICE_QNAME, features);
    }

    public StudentService(URL wsdlLocation) {
        super(wsdlLocation, STUDENTSERVICE_QNAME);
    }

    public StudentService(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, STUDENTSERVICE_QNAME, features);
    }

    public StudentService(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public StudentService(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns StudentServicePortType
     */
    @WebEndpoint(name = "StudentServicePort")
    public StudentServicePortType getStudentServicePort() {
        return super.getPort(new QName("http://example.com/student", "StudentServicePort"), StudentServicePortType.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns StudentServicePortType
     */
    @WebEndpoint(name = "StudentServicePort")
    public StudentServicePortType getStudentServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://example.com/student", "StudentServicePort"), StudentServicePortType.class, features);
    }

    private static URL __getWsdlLocation() {
        if (STUDENTSERVICE_EXCEPTION!= null) {
            throw STUDENTSERVICE_EXCEPTION;
        }
        return STUDENTSERVICE_WSDL_LOCATION;
    }

}
