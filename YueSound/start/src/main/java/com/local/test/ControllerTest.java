package com.local.test;

import cn.hutool.core.date.DateTime;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONObject;
import com.local.YueSound.YueSoundUtils;
import com.local.service.YueSound.YueSoundOpenApi;
import com.local.service.km.webservice.Exception_Exception;
import com.local.service.km.webservice.IKmReviewWebserviceService;
import com.local.service.km.webservice.IKmReviewWebserviceServiceService;
import com.local.service.km.webservice.KmReviewParamterForm;
import com.local.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.namespace.QName;
import java.net.URL;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author hhs
 * @create 2022-06-29 11:52
 */
@Controller
@RequestMapping("/yonyou")
@Slf4j
public class ControllerTest {
    /*
   fdKey(string)
   附件的关键字，富文本模式下为"fdAttachment"，表单模式下为附件控件的id
   * */
    private static final String OA_KM_FD_KEY_START="<fdKey>" ;
    private static final String OA_KM_FD_KEY_END="</fdKey>" ;
    /*
    fdFileName(string)
    * 附件文件名
    * */
    private static final String OA_KM_FD_FILENAME_START="<fdFileName>" ;
    private static final String OA_KM_FD_FILENAME_END="</fdFileName>" ;
    /*
   fdAttachment (字节数组 byte[])
   * 附件内容，格式为字节编码
   * */
    private static final String OA_KM_FD_ATTACHMENT_START="<fdAttachment>" ;
    private static final String OA_KM_FD_ATTACHMENT_END="</fdAttachment>" ;
    private static final String OA_ATTACHMENT_FROMS_START="<attachmentForms>" ;
    private static final String OA_ATTACHMENT_FROMS_END="</attachmentForms>" ;
    private static final String OA_ATTACHMENT_VALUES_START="<attachmentValues>" ;
    private static final String OA_ATTACHMENT_VALUES_END="</attachmentValues>" ;
    private static final String OA_AUTH_AREA_ID_START="<authAreaId>" ;
    private static final String OA_AUTH_AREA_ID_END="</authAreaId>" ;
    private static final String OA_DOC_CONTENT_START="<docContent>" ;
    private static final String OA_DOC_CONTENT_END="</docContent>" ;
    private static final String OA_DOC_CREATOR_START="<docCreator>" ;
    private static final String OA_DOC_CREATOR_END="</docCreator>" ;
    private static final String OA_DOC_PROPERTY_START="<docProperty>" ;
    private static final String OA_DOC_PROPERTY_END="</docProperty>" ;
    private static final String OA_DOC_STATUS_START="<docStatus>" ;
    private static final String OA_DOC_STATUS_END="</docStatus>" ;
    private static final String OA_DOC_SUBJECT_START="<docSubject>" ;
    private static final String OA_DOC_SUBJECT_END="</docSubject>" ;
    private static final String OA_FDID_START="<fdId>" ;
    private static final String OA_FDID_END="</fdId>" ;
    private static final String OA_FD_KEYWORD_START="<fdKeyword>" ;
    private static final String OA_FD_KEYWORD_END="</fdKeyword>" ;
    private static final String OA_FD_SOURCE_START="<fdSource>" ;
    private static final String OA_FD_SOURCE_END="</fdSource>" ;
    private static final String OA_FD_TEMPLATEID_START="<fdTemplateId>" ;
    private static final String OA_FD_TEMPLATEID_END="</fdTemplateId>" ;
    private static final String OA_FLOW_PARAM_START="<flowParam>" ;
    private static final String OA_FLOW_PARAM_END="</flowParam>" ;
    private static final String OA_FROM_VALUES_START="<formValues>" ;
    private static final String OA_FROM_VALUES_END="</formValues>" ;
    private static final QName SERVICE_NAME = new QName("http://webservice.review.km.kmss.landray.com/", "IKmReviewWebserviceServiceService");
    URL wsdlURL = IKmReviewWebserviceServiceService.WSDL_LOCATION;
    IKmReviewWebserviceServiceService ss = new IKmReviewWebserviceServiceService(wsdlURL, SERVICE_NAME);
    IKmReviewWebserviceService port = ss.getIKmReviewWebserviceServicePort();
    @Autowired
    private YueSoundOpenApi yueSoundOpenApi;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private YueSoundUtils yueSoundUtils;
    @Autowired
    private FMWebServiceConfig cfg;

    @GetMapping("/test")
    @ResponseBody
    public Response test() throws Exception{
        /*String id="40924656721938";
        Productsku productsku = productskuDao.queryById(Long.parseLong(id));
        System.out.println(productsku);
        List<ProductManagementClass> productManagementClasses = productskuDao.selectIuap();
        System.out.println(productManagementClasses);
        Productsku productsku1 = productskuService.queryById(Long.parseLong(id));*/
      /*  TestController testController=new TestController();
        testController.test();*/
       // redisTemplate.opsForValue().set("test","test");
        /*YueSoundUtils.redis.opsForValue().set("name","test");
        Object name = YueSoundUtils.redis.opsForValue().get("name");
        Object test = YueSoundUtils.redis.opsForValue().get("test");
        System.out.println(test);
        System.out.println(name)*/;

        YueSoundParam param=new YueSoundParam();
        param.setId("1513574358064824321");// 1513024576481132552 1513632365893124098
        //param.setCode("09001");
        //param.setMerchantApplyRangeId("2809528424648961");
        //return yueSoundOpenApi.saveBatchDetail(param);
        //2850170246107392
        //return yueSoundOpenApi.saveOrUpdateManagementClassDetail(param);
        //return Response.success().setData(redisTemplate.opsForValue().get("test"));

        String string="sourceID=TODO_CENTER&eventType=ADD_TODO&tenantCode=t514k1ry&id=123dc189931-550b-4675-8766-ba27ec93177c&userObject=%7B%22actionKey%22%3A%22ucenter%22%2C%22value%22%3A%22%7B%5C%22appId%5C%22%3A%5C%221082%5C%22%2C%5C%22approveSource%5C%22%3A%5C%22SCMSA%5C%22%2C%5C%22businessKey%5C%22%3A%5C%2261fe9dda-116c-11ed-b51b-62ae331eb701%5C%22%2C%5C%22commitTsLong%5C%22%3A1659339271657%2C%5C%22commitUserId%5C%22%3A%5C%22b1b430a3-bb19-4423-9b6a-fc8140ff1a4f%5C%22%2C%5C%22content%5C%22%3A%5C%22%E5%8F%91%E8%B5%B7%E4%BA%BA%EF%BC%9A%E9%BE%99%E6%94%BF%E5%BD%AC%5C%5Cn%E5%8F%91%E8%B5%B7%E6%97%B6%E9%97%B4%EF%BC%9A08-0115%3A34%5C%5Cn%E5%8F%91%E8%B5%B7%E4%BA%BA%E9%83%A8%E9%97%A8%EF%BC%9A%E9%87%87%E8%B4%AD%E9%83%A8%5C%22%2C%5C%22formId%5C%22%3A%5C%22SCMSA.voucher_order%5C%22%2C%5C%22labelCode%5C%22%3A%5C%22bizProcessGeneralDomain%5C%22%2C%5C%22mUrl%5C%22%3A%5C%22https%3A%2F%2Fywzt.chinahys.com%2Fyonbip-ec-iform%2Fesn%2Fsso%2Fredirect%3Fappsource%3Du8c%26acBillNo%3Dvoucher_order%26acBillId%3D1513024576481132552%26source%3DSCMSA%26turl%3D%26pkBo%3D%26pkBoins%3D%26taskId%3D61fe9dda-116c-11ed-b51b-62ae331eb701%26processDefId%3Dprocesscenter_40400662%3A1%3A90d47b49-0d8c-11ed-b51b-62ae331eb701%26processDefinitionId%3Dprocesscenter_40400662%3A1%3A90d47b49-0d8c-11ed-b51b-62ae331eb701%26formId%3D%26formInstanceId%3D%26processInstId%3D61f17e9e-116c-11ed-b51b-62ae331eb701%26isFromEsnDocument%3Dfalse%26isDocumentQuery%3Dfalse%26page%3DiformBrowse%26systype%3Dapprovecenter%26id%3D61fe9dda-116c-11ed-b51b-62ae331eb701%26formKey%3D%26businessKey%3Dvoucher_order_1513024576481132552%26activityId%3DapproveUserTask_b75e5b0b96554caa9a331028987bc4eb%26taskId%3D61fe9dda-116c-11ed-b51b-62ae331eb701%26processInstanceId%3D61f17e9e-116c-11ed-b51b-62ae331eb701%26copyToId%3D%26tenantId%3Dt514k1ry%26code%3D%24%7Besncode%7D%26from_mc_workflow%3D1%5C%22%2C%5C%22msgTsLong%5C%22%3A1659339271682%2C%5C%22omitNotify%5C%22%3Afalse%2C%5C%22resendable%5C%22%3Afalse%2C%5C%22richText%5C%22%3A%5C%22%3Cp%3E%E5%8F%91%E8%B5%B7%E4%BA%BA%EF%BC%9A%E9%BE%99%E6%94%BF%E5%BD%AC%3C%2Fp%3E%3Cp%3E%E5%8F%91%E8%B5%B7%E6%97%B6%E9%97%B4%EF%BC%9A08-01+15%3A34%3C%2Fp%3E%3Cp%3E%E5%8F%91%E8%B5%B7%E4%BA%BA%E9%83%A8%E9%97%A8%EF%BC%9A%E9%87%87%E8%B4%AD%E9%83%A8%3C%2Fp%3E%5C%22%2C%5C%22showAgree%5C%22%3Atrue%2C%5C%22showReject%5C%22%3Atrue%2C%5C%22srcAppId%5C%22%3A%5C%22msgplatform%5C%22%2C%5C%22srcMsgId%5C%22%3A%5C%222022080120220801332208f8-9781-4f57-8eea-b474e50f4ab4%3Aiuap_apcom_workflow%5C%22%2C%5C%22systemSource%5C%22%3A%5C%221082%5C%22%2C%5C%22tenantId%5C%22%3A%5C%22t514k1ry%5C%22%2C%5C%22title%5C%22%3A%5C%22%E5%BE%85%E5%8A%9E%3A%E9%94%80%E5%94%AE%E8%AE%A2%E5%8D%95QbOJ-000000000-20220801-000001%5C%22%2C%5C%22todoTemplateId%5C%22%3A%5C%2260dd7e1ab1a50765ebfb3697%5C%22%2C%5C%22todoTemplateVars%5C%22%3A%7B%5C%22apptype%5C%22%3A%5C%22mdf%5C%22%2C%5C%22domainKey%5C%22%3A%5C%22udinghuo%5C%22%2C%5C%22mobileBillNo%5C%22%3A%5C%22voucher_orderMobileArchive%5C%22%2C%5C%22processDefinitionId%5C%22%3A%5C%22processcenter_40400662%3A1%3A90d47b49-0d8c-11ed-b51b-62ae331eb701%5C%22%2C%5C%22processInstanceId%5C%22%3A%5C%2261f17e9e-116c-11ed-b51b-62ae331eb701%5C%22%2C%5C%22u8cFormId%5C%22%3A%5C%221513024576481132552%5C%22%2C%5C%22eventType%5C%22%3A%5C%22create%5C%22%2C%5C%22source%5C%22%3A%5C%22SCMSA%5C%22%2C%5C%22activityId%5C%22%3A%5C%22approveUserTask_b75e5b0b96554caa9a331028987bc4eb%5C%22%2C%5C%22documentQuery%5C%22%3A%5C%22false%5C%22%2C%5C%22fromEsnDocument%5C%22%3A%5C%22false%5C%22%2C%5C%22u8cForm%5C%22%3A%5C%22voucher_order%5C%22%2C%5C%22executionBusinessKey%5C%22%3A%5C%22voucher_order_1513024576481132552%5C%22%2C%5C%22u8cSource%5C%22%3A%5C%22SCMSA%5C%22%2C%5C%22taskId%5C%22%3A%5C%2261fe9dda-116c-11ed-b51b-62ae331eb701%5C%22%2C%5C%22isFromEsnDocument%5C%22%3A%5C%22false%5C%22%2C%5C%22isDocumentQuery%5C%22%3A%5C%22false%5C%22%7D%2C%5C%22todoTmpRelationId%5C%22%3A%5C%2260de74fcfa5b8b002c32dba1%5C%22%2C%5C%22todoType%5C%22%3A%5C%22approve%5C%22%2C%5C%22typeName%5C%22%3A%5C%22%E5%BE%85%E5%8A%9E%E6%B6%88%E6%81%AF%5C%22%2C%5C%22userToItemId%5C%22%3A%7B%7D%2C%5C%22webUrl%5C%22%3A%5C%22https%3A%2F%2Fywzt.chinahys.com%2Fmdf-node%2Fmeta%2Fvoucher%2Fvoucher_order%2F1513024576481132552%3FdomainKey%3Dudinghuo%26tenantId%3Dt514k1ry%26apptype%3Dmdf%26from_mc_workflow%3D1%26isMobile%3Dfalse%5C%22%2C%5C%22yyUserIds%5C%22%3A%5B%5C%22b1b430a3-bb19-4423-9b6a-fc8140ff1a4f%5C%22%5D%7D%22%7D";
        String decode = URLDecoder.decode(string, "UTF-8");
        //System.out.println(decode);
        //System.out.println(decode.substring(decode.indexOf("{"), decode.length()));
        Map map = JSONObject.parseObject(decode.substring(decode.indexOf("{"), decode.length()), Map.class);
        Map jsonMap = JSONObject.parseObject(map.get("value").toString(), Map.class);
        System.out.println(jsonMap);
        String title = jsonMap.get("title").toString();
        Map templateMap = (Map)jsonMap.get("todoTemplateVars");
        String u8cFormId = templateMap.get("u8cFormId").toString();
        //根据title判断单据类型
        if(title.contains("销售订单")){

        }
        if(title.contains("销售变更")){

        }
        if(title.contains("销售退货")){

        }

        Response pom=sendSaleOrder(param,title);


        /*
         * 销售变更
         * */

        /*
         * 销售退货
         * */
       /* Response data = yueSoundOpenApi.getReturnOrderDetail(param);
        Map dataMap=(Map)data.getData();
        String id = dataMap.get("id").toString();//主表id
        String salesOrgId = dataMap.get("salesOrgId").toString();//销售组织id
        String salesOrgIdName = dataMap.get("salesOrgId_name").toString();//销售组织名称
        String vouchdate = dataMap.get("vouchdate").toString(); //单据日期
        String code = dataMap.get("code").toString(); //单据编号
        String agentIdName = dataMap.get("agentId_name").toString(); //客户名称
        String agentId = dataMap.get("agentId").toString(); //客户id
        String saleDepartmentIdName =""; //销售部门名称
        if(!ObjectUtils.isEmpty(dataMap.get("saleDepartmentId_name"))){
            saleDepartmentIdName = dataMap.get("saleDepartmentId_name").toString();
        }
        String corpContactUserName="";
        if(!ObjectUtils.isEmpty(dataMap.get("corpContactUserName"))){
            corpContactUserName = dataMap.get("corpContactUserName").toString(); //销售业务员
        }
        String currencyName = dataMap.get("currencyName").toString(); //币种
        String status = dataMap.get("status").toString(); //单据状态
        List<Map> saleReturnDetails = (List<Map>) JSONObject.parseObject(dataMap.get("saleReturnDetails").toString(), List.class);
        for(Map detail:saleReturnDetails){
            String fdId = detail.get("id").toString(); //子表id
            String saleReturnId = detail.get("saleReturnId").toString(); //主表id
            String productCode = detail.get("productCode").toString(); //商品编码
            String productName = detail.get("productName").toString(); //商品名称
            String skuCode = detail.get("skuCode").toString(); //sku编码
            String skuName = detail.get("skuName").toString(); //sku名称
            String productAuxUnitName = detail.get("productAuxUnitName").toString(); //销售单位
            String subQty = detail.get("subQty").toString(); //退货销售数量
            String qtyName = detail.get("qtyName").toString(); //主计量
            String qty = detail.get("qty").toString(); //退货数量
            String stockName = detail.get("stockName").toString(); //退货仓库
            String stockOrgIdName = detail.get("stockOrgId_name").toString(); //库存组织名称
            String oriTaxUnitPrice = detail.get("oriTaxUnitPrice").toString(); //含税成交价
            String oriSum = detail.get("oriSum").toString(); //含税金额
            String oriTax = detail.get("oriTax").toString(); //税额
            String taxRate = detail.get("taxRate").toString(); //税率
        }
*/


        return pom;
    }

    private Response sendSaleOrder(YueSoundParam param,String title) {
        List<String> fdIds=new ArrayList<>();//fd_id
        List<String> productCodeList=new ArrayList<>();//fd_3af3908a86cd8e
        List<String> productNameList=new ArrayList<>();//fd_3af3908c17236e
        List<String> skuCodeList=new ArrayList<>();//fd_3af3908d45b60a
        List<String> skuNameList=new ArrayList<>();//fd_3af3908ea41516
        List<String> productAuxUnitNameList=new ArrayList<>();//fd_3af39091a06b6c
        List<String> subQtyList=new ArrayList<>();//fd_3af390950ff450
        List<String> productUnitNameList=new ArrayList<>();//fd_3af39098005bda
        List<String> priceQtyList=new ArrayList<>();//fd_3af3909899d700
        List<String> qtyNameList=new ArrayList<>();//fd_3af39099494a50
        List<String> qtyList=new ArrayList<>();//fd_3af3909a414c54
        List<String> stockNameList=new ArrayList<>();//fd_3af3909af45118
        List<String> stockOrgIdNameList=new ArrayList<>();//fd_3af3909c1c379e
        List<String> consignTimeList=new ArrayList<>();//fd_3af3909cd218d2
        List<String> oriTaxUnitPriceList=new ArrayList<>();//fd_3af3909db54a18
        List<String> salePriceOrigTaxfreeList=new ArrayList<>();//fd_3af3909e698130
        List<String> oriSumList=new ArrayList<>();//fd_3af3909f4c8636
        List<String> taxRateList=new ArrayList<>();//fd_3af390a47ece9a
        List<String> oriTaxList=new ArrayList<>();//fd_3af390a5299f54
        List<String> natSumList=new ArrayList<>();//fd_3af390a5d472c4
        List<String> natMoneyList=new ArrayList<>();//fd_3af390a7c7e0d4
        List<String> natTaxUnitPriceList=new ArrayList<>();//fd_3af390a8b531dc
        Map sendMap=new HashMap();
        Response data = yueSoundOpenApi.getSaleOrderDetail(param);
        if(ObjectUtils.isEmpty(data.getData())){return data;}
        Map dataMap=(Map)data.getData();
        System.out.println(dataMap);
        String id = dataMap.get("id").toString();//父表主键id
        sendMap.put("fdId",id);
        String salesOrgIdName = dataMap.get("salesOrgId_name").toString();//销售组织名称
        sendMap.put("fd_3af38e6a401bf2",salesOrgIdName);
        String transactionTypeIdName = dataMap.get("transactionTypeId_name").toString();//交易类型名称
        sendMap.put("fd_3af38e6d2fd7bc",transactionTypeIdName);
        String vouchdate = dataMap.get("vouchdate").toString(); //单据日期
        String vouchDateTime = DateUtil.format(DateUtil.parseDate(vouchdate), "yyyy-MM-dd");
        sendMap.put("fd_3af38e6fe6bfb2",vouchDateTime);
        String code = dataMap.get("code").toString(); //单据编号
        sendMap.put("fd_3af38e6aff9172",code);
        //String receiveContacter = dataMap.get("receiveContacter").toString(); //客户联系人
        String agentIdName = dataMap.get("agentId_name").toString(); //客户名称
        sendMap.put("fd_3af38e6e2dac00",agentIdName);
        String saleDepartmentIdName =""; //销售部门名称
        if(!ObjectUtils.isEmpty(dataMap.get("saleDepartmentId_name"))){
            saleDepartmentIdName = dataMap.get("saleDepartmentId_name").toString();
        }
        sendMap.put("fd_3af38e710c9f62",saleDepartmentIdName);
        String corpContactUserName="";
        if(!ObjectUtils.isEmpty(dataMap.get("corpContactUserName"))){
            corpContactUserName = dataMap.get("corpContactUserName").toString(); //销售业务员
        }
        sendMap.put("fd_3af38e6be3d028",corpContactUserName);
        String settlementOrgIdName ="";
        if(!ObjectUtils.isEmpty(dataMap.get("settlementOrgId_name"))){
            settlementOrgIdName = dataMap.get("settlementOrgId_name").toString(); //开票组织名称
        }
        sendMap.put("fd_3af38e6efbfbba",settlementOrgIdName);
        String source = dataMap.get("source").toString(); //单据来源 0:PC端、1:移动端、3:导入、4:ERP
        if(source.equals("0")){
            sendMap.put("fd_3af38e72fd740e","PC端");
        }
        if(source.equals("1")){
            sendMap.put("fd_3af38e72fd740e","移动端");
        }
        if(source.equals("3")){
            sendMap.put("fd_3af38e72fd740e","导入");
        }
        if(source.equals("4")){
            sendMap.put("fd_3af38e72fd740e","ERP");
        }
        String creditBalance="";
        if(!ObjectUtils.isEmpty(dataMap.get("creditBalance"))){
            creditBalance = dataMap.get("creditBalance").toString(); //客户信用余额
        }
        sendMap.put("fd_3af38e7a605982",creditBalance);
        String nextStatus = dataMap.get("nextStatus").toString(); //订单状态
        sendMap.put("fd_3af38e7b964d6a",nextStatus);
        Map orderPriceMap = JSONObject.parseObject(dataMap.get("orderPrices").toString(), Map.class);
        String originalName = orderPriceMap.get("originalName").toString();//币种
        sendMap.put("fd_3af38e756fa89e",originalName);
        String exchRate = orderPriceMap.get("exchRate").toString();//汇率
        sendMap.put("fd_3af38e76c92966",exchRate);
        String exchangeRateTypeName = orderPriceMap.get("exchangeRateType_name").toString();//汇率类型名称
        sendMap.put("fd_3af38e77ff628e",exchangeRateTypeName);
        String domesticCode = orderPriceMap.get("domesticCode").toString();//本币简称
        sendMap.put("fd_3af38e791f51bc",domesticCode);
        String originalCode = orderPriceMap.get("originalCode").toString();//原币简称
        sendMap.put("fd_3af38e7468223e",originalCode);
        List<Map> orderDetails = (List<Map>) JSONObject.parseObject(dataMap.get("orderDetails").toString(), List.class);
        for (Map detail: orderDetails){
            Map priceMap=JSONObject.parseObject(detail.get("orderDetailPrices").toString(),Map.class);
           /* String orderId = detail.get("orderId").toString();//父表主键id
            fdPIds.add(orderId);*/
            String detailId = detail.get("id").toString();//子表主键id
            fdIds.add(detailId);
            String productCode = detail.get("productCode").toString();//商品编码
            productCodeList.add(productCode);
            String productName = detail.get("productName").toString();//商品名称
            productNameList.add(productName);
            String skuCode = detail.get("skuCode").toString();//sku编码
            skuCodeList.add(skuCode);
            String skuName = detail.get("skuName").toString();//sku编码
            skuNameList.add(skuName);
            String productAuxUnitName = detail.get("productAuxUnitName").toString();//销售单位
            productAuxUnitNameList.add(productAuxUnitName);
            String subQty = detail.get("subQty").toString();//销售数量
            subQtyList.add(subQty);
            String productUnitName = detail.get("productUnitName").toString();//计价单位
            productUnitNameList.add(productUnitName);
            String priceQty = detail.get("priceQty").toString();//计价数量
            priceQtyList.add(priceQty);
            String qtyName = detail.get("qtyName").toString();//主计量名称
            qtyNameList.add(qtyName);
            // String masterUnitId = detail.get("masterUnitId").toString();//主计量单位id
            String qty = detail.get("qty").toString();//数量
            qtyList.add(qty);
            if(!ObjectUtils.isEmpty(detail.get("stockName"))){
                String stockName = detail.get("stockName").toString();//发货仓库
                stockNameList.add(stockName);
            }
            String stockOrgIdName = detail.get("stockOrgId_name").toString();//库存组织名称
            stockOrgIdNameList.add(stockOrgIdName);
            String consignTime = detail.get("consignTime").toString();//计划发货日期
            String consignDateTime = DateUtil.format(DateUtil.parse(consignTime), "yyyy-MM-dd");
            consignTimeList.add(consignDateTime);
            String oriTaxUnitPrice = detail.get("oriTaxUnitPrice").toString();//含税成交价
            oriTaxUnitPriceList.add(oriTaxUnitPrice);
            String salePriceOrigTaxfree = priceMap.get("salePrice_orig_taxfree").toString();//无税成交价
            salePriceOrigTaxfreeList.add(salePriceOrigTaxfree);
            String oriSum = detail.get("oriSum").toString();//含税金额
            oriSumList.add(oriSum);
            String taxRate = detail.get("taxRate").toString();//税率
            taxRateList.add(taxRate);
            String oriTax = priceMap.get("oriTax").toString();//税额
            oriTaxList.add(oriTax);
            String natSum = priceMap.get("natSum").toString();//本币含税金额
            natSumList.add(natSum);
            String natMoney = priceMap.get("natMoney").toString();//本币无税金额
            natMoneyList.add(natMoney);
            String natTaxUnitPrice = priceMap.get("natTaxUnitPrice").toString();//本币含税单价
            natTaxUnitPriceList.add(natTaxUnitPrice);
        }
        Map detailMap=new HashMap();
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fdId",fdIds);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3908a86cd8e",productCodeList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3908c17236e",productNameList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3908d45b60a",skuCodeList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3908ea41516",skuNameList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af39091a06b6c",productAuxUnitNameList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af390950ff450",subQtyList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af39098005bda",productUnitNameList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3909899d700",priceQtyList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af39099494a50",qtyNameList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3909a414c54",qtyList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3909af45118",stockNameList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3909c1c379e",stockOrgIdNameList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3909cd218d2",consignTimeList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3909db54a18",oriTaxUnitPriceList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3909e698130",salePriceOrigTaxfreeList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af3909f4c8636",oriSumList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af390a47ece9a",taxRateList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af390a5299f54",oriTaxList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af390a5d472c4",natSumList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af390a7c7e0d4",natMoneyList);
        detailMap.put(ConvertConstant.SALE_ORDER_BODY_ID+".fd_3af390a8b531dc",natTaxUnitPriceList);
        sendMap.put(ConvertConstant.SALE_ORDER_BODY_ID,detailMap);
        String json = JSONObject.toJSONString(sendMap);
        // System.out.println(json);
        KmReviewParamterForm form=new KmReviewParamterForm();
        form.setAttachmentValues(null);
        form.setFdTemplateId(ConvertConstant.SALE_ORDER_FORM_ID); //模板id
        form.setDocSubject(title);//文档标题
        form.setFormValues(json);//流程表单数据
        Map person=new HashMap();
        person.put("PersonNo",ConvertConstant.USER_NO);
        String docCreator = JSONObject.toJSONString(person);
        form.setDocCreator(docCreator);//流程发起人
        form.setDocStatus(ConvertConstant.DOC_STATUS);//文档状态 默认"20"-(10:草稿 ; 20:待审)
        form.setFdKeyword("[]");
        Map map = addReview(form);
        if(ConvertConstant.FLAG_TRUE.equals(map.get("status").toString())){
            return  Response.success().setData(map.get("data"));
        }else{
            return  Response.fail().setData(map.get("data"));
        }
    }

    @GetMapping("/test2")
    @ResponseBody
    public Response test2() {
        /*String id="40924656721938";
        Productsku productsku = productskuDao.queryById(Long.parseLong(id));
        System.out.println(productsku);
        List<ProductManagementClass> productManagementClasses = productskuDao.selectIuap();
        System.out.println(productManagementClasses);
        Productsku productsku1 = productskuService.queryById(Long.parseLong(id));*/
      /*  TestController testController=new TestController();
        testController.test();*/
        // 流程表单
       // String formValues = "{\"fd_2eddbf023c8292\":\"张三\", \"fd_2edd2f83f68242\":\"咨询部\", \"fd_2edd2fa69f6fc6\":\"\", \"fd_2eddbf09f9bc96\":\"2011-10-26\", \"fd_2edd2fb18e7f90\":{\"fd_2edd2fb18e7f90.fd_2eddbef4da4688\":[\"555555\",\"777777\"], \"fd_2edd2fb18e7f90.fd_2edd2fc8001062\":[\"444444\",\"666666\"], \"fd_2edd2fb18e7f90.fdId\":[\"1332472122898ac618f3e22460cab595\",\"13324721228b50c184d82c44ceca5301\"]}}";
        // 流程参数
       // String flowParam = "{auditNode:\"请审核\", futureNodeId:\"N7\", changeNodeHandlers:[\"N7:1183b0b84ee4f581bba001c47a78b2d9;131d019fbac792eab0f0a684c8a8d0ec\"]}";
      /*  StringBuilder sb=new StringBuilder();
        sb.append(OA_ATTACHMENT_FROMS_START).append(OA_KM_FD_KEY_START).append(OA_KM_FD_KEY_END).append(OA_KM_FD_FILENAME_START).append(OA_KM_FD_FILENAME_END).append(OA_KM_FD_ATTACHMENT_START).append(OA_KM_FD_ATTACHMENT_END).append(OA_ATTACHMENT_FROMS_END)
                .append(OA_ATTACHMENT_VALUES_START).append(OA_ATTACHMENT_VALUES_END).append(OA_AUTH_AREA_ID_START).append(OA_AUTH_AREA_ID_END).append(OA_DOC_CONTENT_START).append(OA_DOC_CONTENT_END).append(OA_DOC_CREATOR_START).append("{\"PersonNo\": \"00012\"}").append(OA_DOC_CREATOR_END).append(OA_DOC_PROPERTY_START).append("{}")
                .append(OA_DOC_PROPERTY_END).append(OA_DOC_STATUS_START).append(OA_DOC_STATUS_END).append(OA_DOC_SUBJECT_START).append(OA_DOC_SUBJECT_END).append(OA_FDID_START).append(OA_FDID_END
        ).append(OA_FD_KEYWORD_START).append(OA_FD_KEYWORD_END).append(OA_FD_SOURCE_START).append(OA_FD_SOURCE_END).append(OA_FD_TEMPLATEID_START).append(OA_FD_TEMPLATEID_END).append(OA_FLOW_PARAM_START).append("{}").append(OA_FLOW_PARAM_END).append(OA_FROM_VALUES_START).append("{}").append(OA_FROM_VALUES_END);


        //com.local.service.km.webservice.KmReviewParamterForm _addReview_arg0 = new com.local.service.km.webservice.KmReviewParamterForm();

        KmReviewParamterForm _addReview_arg0=new KmReviewParamterForm();
        try {
            _addReview_arg0.setAttachmentValues(null);
            String formValues = "{\"fd_2eddbf023c8292\":\"张三\", \"fd_2edd2f83f68242\":\"咨询部\", \"fd_2edd2fa69f6fc6\":\"\", \"fd_2eddbf09f9bc96\":\"2011-10-26\", \"fd_2edd2fb18e7f90\":{\"fd_2edd2fb18e7f90.fd_2eddbef4da4688\":[\"555555\",\"777777\"], \"fd_2edd2fb18e7f90.fd_2edd2fc8001062\":[\"444444\",\"666666\"], \"fd_2edd2fb18e7f90.fdId\":[\"1332472122898ac618f3e22460cab595\",\"13324721228b50c184d82c44ceca5301\"]}}";
            // 流程参数
            String flowParam = "{auditNode:\"请审核\", futureNodeId:\"N7\", changeNodeHandlers:[\"N7:1183b0b84ee4f581bba001c47a78b2d9;131d019fbac792eab0f0a684c8a8d0ec\"]}";
            _addReview_arg0.setFlowParam(flowParam);
            _addReview_arg0.setFormValues(formValues);
            _addReview_arg0.setFdTemplateId("224646546545");
            _addReview_arg0.setDocStatus("物料采购申请单");
            _addReview_arg0.setDocCreator("{\"PersonNo\": \"00012\"}");
            _addReview_arg0.setFdKeyword("[\"物料\", \"采购\"]");
            java.lang.String _addReview__return = port.addReview(_addReview_arg0);
            System.out.println("addReview.result=" + _addReview__return);

        } catch (Exception_Exception e) {
            System.out.println("Expected exception: Exception has occurred.");
            System.out.println(e.toString());
        }*/
        YueSoundParam param=new YueSoundParam();
          /*
           客户档案测试
           //param.setId("1515236098090467334"); // 2850631416320256
           //param.setCode("1-106201");
           //param.setMerchantApplyRangeId("1515236098090467353");// 1509181894985515012*/
       // return yueSoundOpenApi.saveMerchantDetail(param);
        //销售订单测试
        //param.setId("1510781778918899721");
        param.setId("1514378607870869513");
        return yueSoundOpenApi.getSaleDeliveryDetail(param);

    }

    //传递参数-OA审批接口-添加审批流程
    private Map addReview(KmReviewParamterForm addReview_arg0) {
        Map resultMap=new HashMap();
        String result="";
        try{
            result=port.addReview(addReview_arg0);
            resultMap.put("data",result);
            resultMap.put("status","true");
        }catch (Exception e){
            log.info("请求OA添加流程失败:"+e);
            result=e.toString();
            resultMap.put("data",result);
            resultMap.put("status","false");
          return resultMap;
        }
        return resultMap;
    }

}

