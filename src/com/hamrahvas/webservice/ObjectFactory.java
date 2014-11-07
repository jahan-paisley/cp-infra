
package com.hamrahvas.webservice;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.hamrahvas.webservice package. 
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

    private final static QName _String_QNAME = new QName("http://tempuri.org/", "string");
    private final static QName _ArrayOfString_QNAME = new QName("http://tempuri.org/", "ArrayOfString");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.hamrahvas.webservice
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link MessageUploadResponse }
     * 
     */
    public MessageUploadResponse createMessageUploadResponse() {
        return new MessageUploadResponse();
    }

    /**
     * Create an instance of {@link MessageListUploadWithServiceId }
     * 
     */
    public MessageListUploadWithServiceId createMessageListUploadWithServiceId() {
        return new MessageListUploadWithServiceId();
    }

    /**
     * Create an instance of {@link ArrayOfString }
     * 
     */
    public ArrayOfString createArrayOfString() {
        return new ArrayOfString();
    }

    /**
     * Create an instance of {@link ArrayOfInt }
     * 
     */
    public ArrayOfInt createArrayOfInt() {
        return new ArrayOfInt();
    }

    /**
     * Create an instance of {@link MessageUploadWithServiceIdResponse }
     * 
     */
    public MessageUploadWithServiceIdResponse createMessageUploadWithServiceIdResponse() {
        return new MessageUploadWithServiceIdResponse();
    }

    /**
     * Create an instance of {@link MessageUpload }
     * 
     */
    public MessageUpload createMessageUpload() {
        return new MessageUpload();
    }

    /**
     * Create an instance of {@link MessageListUpload }
     * 
     */
    public MessageListUpload createMessageListUpload() {
        return new MessageListUpload();
    }

    /**
     * Create an instance of {@link MessageUploadWithServiceId }
     * 
     */
    public MessageUploadWithServiceId createMessageUploadWithServiceId() {
        return new MessageUploadWithServiceId();
    }

    /**
     * Create an instance of {@link MessageListUploadWithServiceIdResponse }
     * 
     */
    public MessageListUploadWithServiceIdResponse createMessageListUploadWithServiceIdResponse() {
        return new MessageListUploadWithServiceIdResponse();
    }

    /**
     * Create an instance of {@link MessageListUploadResponse }
     * 
     */
    public MessageListUploadResponse createMessageListUploadResponse() {
        return new MessageListUploadResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "string")
    public JAXBElement<String> createString(String value) {
        return new JAXBElement<String>(_String_QNAME, String.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ArrayOfString }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tempuri.org/", name = "ArrayOfString")
    public JAXBElement<ArrayOfString> createArrayOfString(ArrayOfString value) {
        return new JAXBElement<ArrayOfString>(_ArrayOfString_QNAME, ArrayOfString.class, null, value);
    }

}
