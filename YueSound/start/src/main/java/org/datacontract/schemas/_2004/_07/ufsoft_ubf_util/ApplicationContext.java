
package org.datacontract.schemas._2004._07.ufsoft_ubf_util;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.microsoft.schemas._2003._10.serialization.arrays.ArrayOfKeyValueOfanyTypeanyType;


/**
 * <p>Java class for ApplicationContext complex type.
 *
 * <p>The following schema fragment specifies the expected content contained within this class.
 *
 * <pre>
 * &lt;complexType name="ApplicationContext">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="nameValueHas" type="{http://schemas.microsoft.com/2003/10/Serialization/Arrays}ArrayOfKeyValueOfanyTypeanyType"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ApplicationContext", propOrder = {
        "nameValueHas"
})
public class ApplicationContext {

    @XmlElement(required = true, nillable = true)
    protected ArrayOfKeyValueOfanyTypeanyType nameValueHas;

    /**
     * Gets the value of the nameValueHas property.
     *
     * @return possible object is
     * {@link ArrayOfKeyValueOfanyTypeanyType }
     */
    public ArrayOfKeyValueOfanyTypeanyType getNameValueHas() {
        return nameValueHas;
    }

    /**
     * Sets the value of the nameValueHas property.
     *
     * @param value allowed object is
     *              {@link ArrayOfKeyValueOfanyTypeanyType }
     */
    public void setNameValueHas(ArrayOfKeyValueOfanyTypeanyType value) {
        this.nameValueHas = value;
    }

}
