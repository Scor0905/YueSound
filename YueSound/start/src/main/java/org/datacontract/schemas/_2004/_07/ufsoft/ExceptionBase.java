
package org.datacontract.schemas._2004._07.ufsoft;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;

import org.datacontract.schemas._2004._07.system.Exception;
import org.datacontract.schemas._2004._07.ufsoft_ubf.BusinessException;
import org.datacontract.schemas._2004._07.ufsoft_ubf.ServiceException;
import org.datacontract.schemas._2004._07.ufsoft_ubf.ServiceLostException;


/**
 * <p>Java class for ExceptionBase complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ExceptionBase">
 *   &lt;complexContent>
 *     &lt;extension base="{http://schemas.datacontract.org/2004/07/System}Exception">
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ExceptionBase")
@XmlSeeAlso({
        BusinessException.class,
        UnknownException.class,
        ServiceLostException.class,
        ServiceException.class
})
public class ExceptionBase
        extends Exception {


}
