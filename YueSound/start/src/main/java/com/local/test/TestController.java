package com.local.test;

import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;

import java.net.URLDecoder;
import java.util.Map;

/**
 * @author hhs
 * @create 2022-06-29 10:09
 */

public class TestController {
    @Autowired
    private RestTemplate restTemplate = new RestTemplate();
    private static final String SOAP_ENVELOPE_START = "<soapenv:Envelope ";
    private static final String SOAP_ENVELOPE_END = "</soapenv:Envelope> ";
    private static final String XMLNS_PARAM = "xmlns:soapenv=\\\"http://schemas.xmlsoap.org/soap/envelope\\\" xmlns:ufid=\\\"http://www.UFIDA.org\\\">";
    private static final String SOAP_HEADER_START = "<soapenv:Header>";
    private static final String SOAP_HEADER_END = "</soapenv:Header>";
    private static final String SOAP_BODY_START = "<soapenv:Body>";
    private static final String SOAP_BODY_END = "</soapenv:Body>";
    private static final String UFID_DO_START = "<ufid:Do>";
    private static final String UFID_DO_END = "</ufid:Do>";
    private static final String UFID_INSTR_START = "<ufid:inStr>";
    private static final String UFID_INSTR_END = "</ufid:inStr>";
    private static final String TEST_URL = "http://192.168.2.233:80/U9/Services/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService.svc";
    private static final String SOAP_ACTION = "http://www.UFIDA.org/UFIDA.U9.SzCusDev.JAR.InterfaceSV.ISyncService/Do";

    public void test() {
        String jsonData = "{" +
                "  \"action\": \"MiscRcv_Approve\"," +
                "  \"projectName\": \"\"," +
                "  \"os_doc_no\": \"string\"," +
                "  \"record_doc_no_field\": \"string\"," +
                "  \"context\": {" +
                "    \"enterpriseId\": \"09\"," +
                "    \"orgId\": 1001505124451956," +
                "    \"userId\": 1001505124454187," +
                "    \"userName\": \"string\"," +
                "    \"operateDateTime\": \"2022-06-29T00:00:00+08:00\"" +
                "  }" +
                "}";
        String json = "{" +
                "  \"docNo\": \"string\"," +
                "  \"action\": \"Test\"," +
                "  \"projectName\": \"\"," +
                "  \"os_doc_no\": \"string\"," +
                "  \"record_doc_no_field\": \"string\"," +
                "  \"context\": {" +
                "    \"enterpriseId\": \"09\"," +
                "    \"orgId\": 1001505124451956," +
                "    \"userId\": 1001505124454187," +
                "    \"userName\": \"string\"," +
                "    \"operateDateTime\": \"2022-06-29T00:00:00+08:00\"" +
                "  }" +
                "}";
        String param = createParameter(json);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.TEXT_XML);
       // headers.add("Content-Type","text/xml;charset=UTF-8");
        headers.add("SOAPAction", SOAP_ACTION);
        HttpEntity<String> requestEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> resEntity = restTemplate.exchange(TEST_URL, HttpMethod.POST, requestEntity, String.class);
        String message = resEntity.getBody();
        System.out.println(message);
        Map<String, Object> map = XmlUtil.xmlToMap(message);
        System.out.println(map);
        Map body =  (Map)map.get("s:Body");
        Map DoResponse =(Map)body.get("DoResponse");
        Object doResult = DoResponse.get("DoResult");
        System.out.println(doResult);
    }

    private String createParameter(String jsonData) {
        StringBuilder sb = new StringBuilder();
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ufid=\"http://www.UFIDA.org\">").append("<soapenv:Header/>").append(SOAP_BODY_START)
                .append(UFID_DO_START).append(UFID_INSTR_START).append(jsonData).append(UFID_INSTR_END).append(UFID_DO_END)
                .append(SOAP_BODY_END).append(SOAP_ENVELOPE_END);
        return sb.toString().replaceAll("\\\\", "");

    }

    public static void main(String[] args) throws Exception{
    /*    String json = "{" +
                "  \"docNo\": \"string\"," +
                "  \"action\": \"Test\"," +
                "  \"projectName\": \"\"," +
                "  \"os_doc_no\": \"string\"," +
                "  \"record_doc_no_field\": \"string\"," +
                "  \"context\": {" +
                "    \"enterpriseId\": \"09\"," +
                "    \"orgId\": 1001505124451956," +
                "    \"userId\": 1001505124454187," +
                "    \"userName\": \"string\"," +
                "    \"operateDateTime\": \"2022-06-29T00:00:00+08:00\"" +
                "  }" +
                "}";
        SyncServiceStub syncServiceStub=new SyncServiceStub();
        UFIDAU9SzCusDevJARInterfaceSVISyncService basicHttpBindingUFIDAU9SzCusDevJARInterfaceSVISyncService = syncServiceStub.getBasicHttpBindingUFIDAU9SzCusDevJARInterfaceSVISyncService();
        String s = basicHttpBindingUFIDAU9SzCusDevJARInterfaceSVISyncService._do(json);
        System.out.println();*/
       /* TestController testController=new TestController();
        testController.test();*/
      /*  String string="sourceID=TODO_CENTER&eventType=ADD_TODO&tenantCode=t514k1ry&id=12368dee52b-b73b-4cab-a021-e4dc44bde93d&userObject=%7B%22actionKey%22%3A%22ucenter%22%2C%22value%22%3A%22%7B%5C%22appId%5C%22%3A%5C%221082%5C%22%2C%5C%22approveSource%5C%22%3A%5C%22SCMSA%5C%22%2C%5C%22businessKey%5C%22%3A%5C%22bdba781c-0d8c-11ed-b51b-62ae331eb701%5C%22%2C%5C%22commitTsLong%5C%22%3A1658913364805%2C%5C%22commitUserId%5C%22%3A%5C%22b1b430a3-bb19-4423-9b6a-fc8140ff1a4f%5C%22%2C%5C%22content%5C%22%3A%5C%22%E5%8F%91%E8%B5%B7%E4%BA%BA%EF%BC%9A%E9%BE%99%E6%94%BF%E5%BD%AC%5C%5Cn%E5%8F%91%E8%B5%B7%E6%97%B6%E9%97%B4%EF%BC%9A07-2717%3A16%5C%5Cn%E5%8F%91%E8%B5%B7%E4%BA%BA%E9%83%A8%E9%97%A8%EF%BC%9A%E9%87%87%E8%B4%AD%E9%83%A8%5C%22%2C%5C%22formId%5C%22%3A%5C%22SCMSA.voucher_order%5C%22%2C%5C%22labelCode%5C%22%3A%5C%22bizProcessGeneralDomain%5C%22%2C%5C%22mUrl%5C%22%3A%5C%22https%3A%2F%2Fywzt.chinahys.com%2Fyonbip-ec-iform%2Fesn%2Fsso%2Fredirect%3Fappsource%3Du8c%26acBillNo%3Dvoucher_order%26acBillId%3D1509366080388726784%26source%3DSCMSA%26turl%3D%26pkBo%3D%26pkBoins%3D%26taskId%3Dbdba781c-0d8c-11ed-b51b-62ae331eb701%26processDefId%3Dprocesscenter_40400662%3A1%3A90d47b49-0d8c-11ed-b51b-62ae331eb701%26processDefinitionId%3Dprocesscenter_40400662%3A1%3A90d47b49-0d8c-11ed-b51b-62ae331eb701%26formId%3D%26formInstanceId%3D%26processInstId%3Dbda9884c-0d8c-11ed-b51b-62ae331eb701%26isFromEsnDocument%3Dfalse%26isDocumentQuery%3Dfalse%26page%3DiformBrowse%26systype%3Dapprovecenter%26id%3Dbdba781c-0d8c-11ed-b51b-62ae331eb701%26formKey%3D%26businessKey%3Dvoucher_order_1509366080388726784%26activityId%3DapproveUserTask_b75e5b0b96554caa9a331028987bc4eb%26taskId%3Dbdba781c-0d8c-11ed-b51b-62ae331eb701%26processInstanceId%3Dbda9884c-0d8c-11ed-b51b-62ae331eb701%26copyToId%3D%26tenantId%3Dt514k1ry%26code%3D%24%7Besncode%7D%26from_mc_workflow%3D1%5C%22%2C%5C%22msgTsLong%5C%22%3A1658913364835%2C%5C%22omitNotify%5C%22%3Afalse%2C%5C%22resendable%5C%22%3Afalse%2C%5C%22richText%5C%22%3A%5C%22%3Cp%3E%E5%8F%91%E8%B5%B7%E4%BA%BA%EF%BC%9A%E9%BE%99%E6%94%BF%E5%BD%AC%3C%2Fp%3E%3Cp%3E%E5%8F%91%E8%B5%B7%E6%97%B6%E9%97%B4%EF%BC%9A07-27+17%3A16%3C%2Fp%3E%3Cp%3E%E5%8F%91%E8%B5%B7%E4%BA%BA%E9%83%A8%E9%97%A8%EF%BC%9A%E9%87%87%E8%B4%AD%E9%83%A8%3C%2Fp%3E%5C%22%2C%5C%22showAgree%5C%22%3Atrue%2C%5C%22showReject%5C%22%3Atrue%2C%5C%22srcAppId%5C%22%3A%5C%22msgplatform%5C%22%2C%5C%22srcMsgId%5C%22%3A%5C%222022072720220727d792d0b1-39f8-48e5-a2d6-410455b755c7%3Aiuap_apcom_workflow%5C%22%2C%5C%22systemSource%5C%22%3A%5C%221082%5C%22%2C%5C%22tenantId%5C%22%3A%5C%22t514k1ry%5C%22%2C%5C%22title%5C%22%3A%5C%22%E5%BE%85%E5%8A%9E%3A%E9%94%80%E5%94%AE%E8%AE%A2%E5%8D%95QbOJ-000000000-20220727-000000%5C%22%2C%5C%22todoTemplateId%5C%22%3A%5C%2260dd7e1ab1a50765ebfb3697%5C%22%2C%5C%22todoTemplateVars%5C%22%3A%7B%5C%22apptype%5C%22%3A%5C%22mdf%5C%22%2C%5C%22domainKey%5C%22%3A%5C%22udinghuo%5C%22%2C%5C%22mobileBillNo%5C%22%3A%5C%22voucher_orderMobileArchive%5C%22%2C%5C%22processDefinitionId%5C%22%3A%5C%22processcenter_40400662%3A1%3A90d47b49-0d8c-11ed-b51b-62ae331eb701%5C%22%2C%5C%22processInstanceId%5C%22%3A%5C%22bda9884c-0d8c-11ed-b51b-62ae331eb701%5C%22%2C%5C%22u8cFormId%5C%22%3A%5C%221509366080388726784%5C%22%2C%5C%22eventType%5C%22%3A%5C%22create%5C%22%2C%5C%22source%5C%22%3A%5C%22SCMSA%5C%22%2C%5C%22activityId%5C%22%3A%5C%22approveUserTask_b75e5b0b96554caa9a331028987bc4eb%5C%22%2C%5C%22documentQuery%5C%22%3A%5C%22false%5C%22%2C%5C%22fromEsnDocument%5C%22%3A%5C%22false%5C%22%2C%5C%22u8cForm%5C%22%3A%5C%22voucher_order%5C%22%2C%5C%22executionBusinessKey%5C%22%3A%5C%22voucher_order_1509366080388726784%5C%22%2C%5C%22u8cSource%5C%22%3A%5C%22SCMSA%5C%22%2C%5C%22taskId%5C%22%3A%5C%22bdba781c-0d8c-11ed-b51b-62ae331eb701%5C%22%2C%5C%22isFromEsnDocument%5C%22%3A%5C%22false%5C%22%2C%5C%22isDocumentQuery%5C%22%3A%5C%22false%5C%22%7D%2C%5C%22todoTmpRelationId%5C%22%3A%5C%2260de74fcfa5b8b002c32dba1%5C%22%2C%5C%22todoType%5C%22%3A%5C%22approve%5C%22%2C%5C%22typeName%5C%22%3A%5C%22%E5%BE%85%E5%8A%9E%E6%B6%88%E6%81%AF%5C%22%2C%5C%22userToItemId%5C%22%3A%7B%7D%2C%5C%22webUrl%5C%22%3A%5C%22https%3A%2F%2Fywzt.chinahys.com%2Fmdf-node%2Fmeta%2Fvoucher%2Fvoucher_order%2F1509366080388726784%3FdomainKey%3Dudinghuo%26tenantId%3Dt514k1ry%26apptype%3Dmdf%26from_mc_workflow%3D1%26isMobile%3Dfalse%5C%22%2C%5C%22yyUserIds%5C%22%3A%5B%5C%22b1b430a3-bb19-4423-9b6a-fc8140ff1a4f%5C%22%5D%7D%22%7D";

        String decode = URLDecoder.decode(string, "UTF-8");
        System.out.println(decode);
        System.out.println(decode.substring(decode.indexOf("{"), decode.length()));
        Map map = JSONObject.parseObject(decode.substring(decode.indexOf("{"), decode.length()), Map.class);
        Map value = JSONObject.parseObject(map.get("value").toString(), Map.class);
        System.out.println(value);
        System.out.println(map);
        System.out.println(decode);*/
        String str="%7B%22actionKey%22%3A%22finish%3A%3Aucenter%22%2C%22value%22%3A%22%7B%5C%22businessKey%5C%22%3A%5C%22bdba781c-0d8c-11ed-b51b-62ae331eb701%5C%22%2C%5C%22finishAttr%5C%22%3A%7B%5C%22documentQuery%5C%22%3A%5C%22false%5C%22%2C%5C%22fromEsnDocument%5C%22%3A%5C%22false%5C%22%2C%5C%22eventType%5C%22%3A%5C%22complete%5C%22%7D%2C%5C%22msgTsLong%5C%22%3A1658913381965%2C%5C%22srcAppId%5C%22%3A%5C%22msgplatform%5C%22%2C%5C%22srcMsgId%5C%22%3A%5C%222022072720220727aafc06ce-6db6-4dba-8fc7-4faf5a4e2b2f%3Aiuap_apcom_workflow%5C%22%2C%5C%22tenantId%5C%22%3A%5C%22t514k1ry%5C%22%2C%5C%22todoTemplateVars%5C%22%3A%7B%5C%22%24ref%5C%22%3A%5C%22%24.finishAttr%5C%22%7D%2C%5C%22validApp%5C%22%3Afalse%7D%22%7D";
        String test = URLDecoder.decode(str, "UTF-8");
        System.out.println(test);




    }
}
