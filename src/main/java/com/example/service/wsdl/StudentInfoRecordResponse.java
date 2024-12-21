
package com.example.service.wsdl;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡrecordMessage���Ե�ֵ��
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
     * ����recordMessage���Ե�ֵ��
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
     * ��ȡrecordStatus���Ե�ֵ��
     * 
     */
    public boolean isRecordStatus() {
        return recordStatus;
    }

    /**
     * ����recordStatus���Ե�ֵ��
     * 
     */
    public void setRecordStatus(boolean value) {
        this.recordStatus = value;
    }

}
