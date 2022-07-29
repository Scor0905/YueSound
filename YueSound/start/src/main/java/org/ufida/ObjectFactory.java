
package org.ufida;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the org.ufida package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DoResponseDoResult_QNAME = new QName("http://www.UFIDA.org", "DoResult");
    private final static QName _DoInStr_QNAME = new QName("http://www.UFIDA.org", "inStr");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: org.ufida
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Do }
     */
    public Do createDo() {
        return new Do();
    }

    /**
     * Create an instance of {@link DoResponse }
     */
    public DoResponse createDoResponse() {
        return new DoResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.UFIDA.org", name = "DoResult", scope = DoResponse.class)
    public JAXBElement<String> createDoResponseDoResult(String value) {
        return new JAXBElement<String>(_DoResponseDoResult_QNAME, String.class, DoResponse.class, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link String }{@code >}}
     */
    @XmlElementDecl(namespace = "http://www.UFIDA.org", name = "inStr", scope = Do.class)
    public JAXBElement<String> createDoInStr(String value) {
        return new JAXBElement<String>(_DoInStr_QNAME, String.class, Do.class, value);
    }

}
