
package org.datacontract.schemas._2004._07.ufsoft_ubf;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;

import org.datacontract.schemas._2004._07.system.ExceptionDetail;
import org.datacontract.schemas._2004._07.ufsoft.ExceptionBase;


/**
 * <p>Java class for ServiceExceptionDetail complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ServiceExceptionDetail">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/System.ServiceModel}ExceptionDetail">
 *       &lt;sequence>
 *         &lt;element name="Exception" type="{http://schemas.datacontract.org/2004/07/UFSoft.UBF}ExceptionBase" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ServiceExceptionDetail", namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", propOrder = {
        "exception"
})
public class ServiceExceptionDetail
        extends ExceptionDetail {

    @XmlElementRef(name = "Exception", namespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service", type = JAXBElement.class)
    protected JAXBElement<ExceptionBase> exception;

    /**
     * Gets the value of the exception property.
     *
     * @return possible object is
     * {@link JAXBElement }{@code <}{@link ExceptionBase }{@code >}
     */
    public JAXBElement<ExceptionBase> getException() {
        return exception;
    }

    /**
     * Sets the value of the exception property.
     *
     * @param value allowed object is
     *              {@link JAXBElement }{@code <}{@link ExceptionBase }{@code >}
     */
    public void setException(JAXBElement<ExceptionBase> value) {
        this.exception = ((JAXBElement<ExceptionBase>) value);
    }

}
