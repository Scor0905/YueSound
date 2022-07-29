package com.local.service.U9;

import org.springframework.beans.factory.annotation.Value;

/**
 * @author hhs
 * @create 2022-07-04 13:35
 */
public class U9Utils {
    @Value("${U9.soapAction:http://www.UFIDA.org/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService/Do}")
    private String soapAction;
    @Value("${U9.U9InterfaceUrl:http://192.168.2.233:80/U9/Services/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService.svc}")
    private String U9InterfaceUrl;
}
