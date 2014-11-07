
package com.hamrahvas.webservice;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="username" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="password" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="numberList" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="contentList" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="origShortCodeList" type="{http://tempuri.org/}ArrayOfString" minOccurs="0"/>
 *         &lt;element name="serviceIdList" type="{http://tempuri.org/}ArrayOfInt" minOccurs="0"/>
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
    "username",
    "password",
    "numberList",
    "contentList",
    "origShortCodeList",
    "serviceIdList"
})
@XmlRootElement(name = "MessageListUploadWithServiceId")
public class MessageListUploadWithServiceId {

    protected String username;
    protected String password;
    protected ArrayOfString numberList;
    protected ArrayOfString contentList;
    protected ArrayOfString origShortCodeList;
    protected ArrayOfInt serviceIdList;

    /**
     * Gets the value of the username property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the value of the username property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUsername(String value) {
        this.username = value;
    }

    /**
     * Gets the value of the password property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the value of the password property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassword(String value) {
        this.password = value;
    }

    /**
     * Gets the value of the numberList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getNumberList() {
        return numberList;
    }

    /**
     * Sets the value of the numberList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setNumberList(ArrayOfString value) {
        this.numberList = value;
    }

    /**
     * Gets the value of the contentList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getContentList() {
        return contentList;
    }

    /**
     * Sets the value of the contentList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setContentList(ArrayOfString value) {
        this.contentList = value;
    }

    /**
     * Gets the value of the origShortCodeList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfString }
     *     
     */
    public ArrayOfString getOrigShortCodeList() {
        return origShortCodeList;
    }

    /**
     * Sets the value of the origShortCodeList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfString }
     *     
     */
    public void setOrigShortCodeList(ArrayOfString value) {
        this.origShortCodeList = value;
    }

    /**
     * Gets the value of the serviceIdList property.
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfInt }
     *     
     */
    public ArrayOfInt getServiceIdList() {
        return serviceIdList;
    }

    /**
     * Sets the value of the serviceIdList property.
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfInt }
     *     
     */
    public void setServiceIdList(ArrayOfInt value) {
        this.serviceIdList = value;
    }

}
