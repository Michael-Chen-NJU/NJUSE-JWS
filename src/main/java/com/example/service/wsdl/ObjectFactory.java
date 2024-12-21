
package com.example.service.wsdl;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.example.service.wsdl package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.example.service.wsdl
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link StudentInfoRecordRequest }
     * 
     */
    public StudentInfoRecordRequest createStudentInfoRecordRequest() {
        return new StudentInfoRecordRequest();
    }

    /**
     * Create an instance of {@link StudentInfoRecordResponse }
     * 
     */
    public StudentInfoRecordResponse createStudentInfoRecordResponse() {
        return new StudentInfoRecordResponse();
    }

    /**
     * Create an instance of {@link AttendanceRequest }
     * 
     */
    public AttendanceRequest createAttendanceRequest() {
        return new AttendanceRequest();
    }

    /**
     * Create an instance of {@link StudentInfoQueryResponse }
     * 
     */
    public StudentInfoQueryResponse createStudentInfoQueryResponse() {
        return new StudentInfoQueryResponse();
    }

    /**
     * Create an instance of {@link AttendanceResponse }
     * 
     */
    public AttendanceResponse createAttendanceResponse() {
        return new AttendanceResponse();
    }

    /**
     * Create an instance of {@link StudentInfoQueryRequest }
     * 
     */
    public StudentInfoQueryRequest createStudentInfoQueryRequest() {
        return new StudentInfoQueryRequest();
    }

}
