
package com.example.service.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="recordMessage" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="recordStatus" type="{http://www.w3.org/2001/XMLSchema}boolean"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "recordMessage",
    "recordStatus"
})
@XmlRootElement(name = "StudentInfoRecordResponse")
public class StudentInfoRecordResponse {

    @XmlElement(required = true)
    protected String recordMessage;
    protected boolean recordStatus;

    /**
     * 获取recordMessage属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordMessage() {
        return recordMessage;
    }

    /**
     * 设置recordMessage属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordMessage(String value) {
        this.recordMessage = value;
    }

    /**
     * 获取recordStatus属性的值。
     * 
     */
    public boolean isRecordStatus() {
        return recordStatus;
    }

    /**
     * 设置recordStatus属性的值。
     * 
     */
    public void setRecordStatus(boolean value) {
        this.recordStatus = value;
    }

}
