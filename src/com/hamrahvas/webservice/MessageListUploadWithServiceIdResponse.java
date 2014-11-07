
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
 *         &lt;element name="MessageListUploadWithServiceIdResult" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/>
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
    "messageListUploadWithServiceIdResult"
})
@XmlRootElement(name = "MessageListUploadWithServiceIdResponse")
public class MessageListUploadWithServiceIdResponse {

    @XmlElement(name = "MessageListUploadWithServiceIdResult")
    protected ArrayOfString messageListUploadWithServiceIdResult;

    /**
     * Gets the value of the messageListUploadWithServiceIdResult property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getMessageListUploadWithServiceIdResult() {
        return messageListUploadWithServiceIdResult;
    }

    /**
     * Sets the value of the messageListUploadWithServiceIdResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setMessageListUploadWithServiceIdResult(ArrayOfString value) {
        this.messageListUploadWithServiceIdResult = value;
    }

}
