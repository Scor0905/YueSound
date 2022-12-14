
package org.ufida;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.2.4
 * Wed Jun 29 18:16:39 CST 2022
 * Generated source version: 2.2.4
 */

@WebFault(name = "ServiceException", targetNamespace = "http://schemas.datacontract.org/2004/07/UFSoft.UBF.Service")
public class UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceExceptionFaultFaultMessage extends Exception {
    public static final long serialVersionUID = 20220629181639L;

    private org.datacontract.schemas._2004._07.ufsoft_ubf.ServiceException serviceException;

    public UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceExceptionFaultFaultMessage() {
        super();
    }

    public UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceExceptionFaultFaultMessage(String message) {
        super(message);
    }

    public UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceExceptionFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceExceptionFaultFaultMessage(String message, org.datacontract.schemas._2004._07.ufsoft_ubf.ServiceException serviceException) {
        super(message);
        this.serviceException = serviceException;
    }

    public UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceExceptionFaultFaultMessage(String message, org.datacontract.schemas._2004._07.ufsoft_ubf.ServiceException serviceException, Throwable cause) {
        super(message, cause);
        this.serviceException = serviceException;
    }

    public org.datacontract.schemas._2004._07.ufsoft_ubf.ServiceException getFaultInfo() {
        return this.serviceException;
    }
}
