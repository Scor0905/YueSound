
package org.datacontract.schemas._2004._07.system;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.datacontract.schemas._2004._07.ufsoft_ubf.ServiceExceptionDetail;


/**
 * <p>Java class for ExceptionDetail complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ExceptionDetail">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="HelpLink" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="InnerException" type="{http://schemas.datacontract.org/2004/07/System.ServiceModel}ExceptionDetail" minOccurs="0"/>
 *         &lt;element name="Message" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="StackTrace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="Type" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExceptionDetail", namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", propOrder = {
        "helpLink",
        "innerException",
        "message",
        "stackTrace",
        "type"
})
@XmlSeeAlso({
        ServiceExceptionDetail.class
})
public class ExceptionDetail {

    @XmlElementRef(name = "HelpLink", namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", type = JAXBElement.class)
    protected JAXBElement<String> helpLink;
    @XmlElementRef(name = "InnerException", namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", type = JAXBElement.class)
    protected JAXBElement<ExceptionDetail> innerException;
    @XmlElementRef(name = "Message", namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", type = JAXBElement.class)
    protected JAXBElement<String> message;
    @XmlElementRef(name = "StackTrace", namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", type = JAXBElement.class)
    protected JAXBElement<String> stackTrace;
    @XmlElementRef(name = "Type", namespace = "http://schemas.datacontract.org/2004/07/System.ServiceModel", type = JAXBElement.class)
    protected JAXBElement<String> type;

    /**
     * Gets the value of the helpLink property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getHelpLink() {
        return helpLink;
    }

    /**
     * Sets the value of the helpLink property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setHelpLink(JAXBElement<String> value) {
        this.helpLink = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the innerException property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ExceptionDetail }{@code >}
     */
    public JAXBElement<ExceptionDetail> getInnerException() {
        return innerException;
    }

    /**
     * Sets the value of the innerException property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link ExceptionDetail }{@code >}
     */
    public void setInnerException(JAXBElement<ExceptionDetail> value) {
        this.innerException = ((JAXBElement<ExceptionDetail>) value);
    }

    /**
     * Gets the value of the message property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getMessage() {
        return message;
    }

    /**
     * Sets the value of the message property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setMessage(JAXBElement<String> value) {
        this.message = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the stackTrace property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getStackTrace() {
        return stackTrace;
    }

    /**
     * Sets the value of the stackTrace property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setStackTrace(JAXBElement<String> value) {
        this.stackTrace = ((JAXBElement<String>) value);
    }

    /**
     * Gets the value of the type property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public JAXBElement<String> getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link String }{@code >}
     */
    public void setType(JAXBElement<String> value) {
        this.type = ((JAXBElement<String>) value);
    }

}
