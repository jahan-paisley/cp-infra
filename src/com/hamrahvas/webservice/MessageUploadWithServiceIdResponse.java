
package com.hamrahvas.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="MessageUploadWithServiceIdResult" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
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
    "messageUploadWithServiceIdResult"
})
@XmlRootElement(name = "MessageUploadWithServiceIdResponse")
public class MessageUploadWithServiceIdResponse {

    @XmlElement(name = "MessageUploadWithServiceIdResult")
    protected String messageUploadWithServiceIdResult;

    /**
     * Gets the value of the messageUploadWithServiceIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMessageUploadWithServiceIdResult() {
        return messageUploadWithServiceIdResult;
    }

    /**
     * Sets the value of the messageUploadWithServiceIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMessageUploadWithServiceIdResult(String value) {
        this.messageUploadWithServiceIdResult = value;
    }

}
