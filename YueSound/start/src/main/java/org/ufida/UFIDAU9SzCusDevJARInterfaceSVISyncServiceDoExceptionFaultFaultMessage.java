
package org.ufida;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.2.4
 * Wed Jun 29 18:16:39 CST 2022
 * Generated source version: 2.2.4
 */

@WebFault(name = "Exception", targetNamespace = "http://schemas.datacontract.org/2004/07/System")
public class UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoExceptionFaultFaultMessage extends Exception {
    public static final long serialVersionUID = 20220629181639L;

    private org.datacontract.schemas._2004._07.system.Exception exception;

    public UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoExceptionFaultFaultMessage() {
        super();
    }

    public UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoExceptionFaultFaultMessage(String message) {
        super(message);
    }

    public UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoExceptionFaultFaultMessage(String message, Throwable cause) {
        super(message, cause);
    }

    public UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoExceptionFaultFaultMessage(String message, org.datacontract.schemas._2004._07.system.Exception exception) {
        super(message);
        this.exception = exception;
    }

    public UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoExceptionFaultFaultMessage(String message, org.datacontract.schemas._2004._07.system.Exception exception, Throwable cause) {
        super(message, cause);
        this.exception = exception;
    }

    public org.datacontract.schemas._2004._07.system.Exception getFaultInfo() {
        return this.exception;
    }
}
