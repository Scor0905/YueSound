package org.ufida;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.Action;
import javax.xml.ws.FaultAction;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.2.4
 * Wed Jun 29 18:16:39 CST 2022
 * Generated source version: 2.2.4
 */

@WebService(targetNamespace = "http://www.UFIDA.org", name = "UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService")
@XmlSeeAlso({com.microsoft.schemas._2003._10.serialization.ObjectFactory.class, org.datacontract.schemas._2004._07.ufsoft.ObjectFactory.class, org.datacontract.schemas._2004._07.ufsoft_ubf_util.ObjectFactory.class, com.microsoft.schemas._2003._10.serialization.arrays.ObjectFactory.class, ObjectFactory.class, org.datacontract.schemas._2004._07.ufsoft_ubf.ObjectFactory.class, exceptions.ubf.ufsoft.ObjectFactory.class, org.datacontract.schemas._2004._07.system.ObjectFactory.class})
public interface UFIDAU9SzCusDevJARInterfaceSVISyncService {

    @WebMethod(operationName = "Do", action = "http://www.UFIDA.org/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService/Do")
    @Action(input = "http://www.UFIDA.org/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService/Do", output = "http://www.UFIDA.org/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService/DoResponse", fault = {@FaultAction(className = UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceExceptionFaultFaultMessage.class, value = "http://www.UFIDA.org/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService/DoServiceExceptionFault"), @FaultAction(className = UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoExceptionBaseFaultFaultMessage.class, value = "http://www.UFIDA.org/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService/DoExceptionBaseFault"), @FaultAction(className = UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceLostExceptionFaultFaultMessage.class, value = "http://www.UFIDA.org/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService/DoServiceLostExceptionFault"), @FaultAction(className = UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoExceptionFaultFaultMessage.class, value = "http://www.UFIDA.org/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService/DoExceptionFault"), @FaultAction(className = UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceExceptionDetailFaultFaultMessage.class, value = "http://www.UFIDA.org/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService/DoServiceExceptionDetailFault")})
    @RequestWrapper(localName = "Do", targetNamespace = "http://www.UFIDA.org", className = "org.ufida.Do")
    @ResponseWrapper(localName = "DoResponse", targetNamespace = "http://www.UFIDA.org", className = "org.ufida.DoResponse")
    @WebResult(name = "DoResult", targetNamespace = "http://www.UFIDA.org")
    public java.lang.String _do(
            @WebParam(name = "inStr", targetNamespace = "http://www.UFIDA.org")
                    java.lang.String inStr
    ) throws UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceExceptionFaultFaultMessage, UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoExceptionBaseFaultFaultMessage, UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceLostExceptionFaultFaultMessage, UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoExceptionFaultFaultMessage, UFIDAU9SzCusDevJARInterfaceSVISyncServiceDoServiceExceptionDetailFaultFaultMessage;
}