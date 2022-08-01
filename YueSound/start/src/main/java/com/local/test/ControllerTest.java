package com.local.test;

import com.local.YueSound.YueSoundUtils;
import com.local.service.YueSound.YueSoundOpenApi;
import com.local.service.km.webservice.Exception_Exception;
import com.local.service.km.webservice.IKmReviewWebserviceService;
import com.local.service.km.webservice.IKmReviewWebserviceServiceService;
import com.local.util.Response;
import com.local.util.FMWebServiceConfig;
import com.local.util.YueSoundParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.xml.namespace.QName;
import java.net.URL;

/**
 * @author hhs
 * @create 2022-06-29 11:52
 */
@Controller
@RequestMapping("/yonyou")
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
    public Response test() {
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
        param.setId("2850094838649088");
        //param.setCode("09001");
        //param.setMerchantApplyRangeId("2809528424648961");
        //return yueSoundOpenApi.saveBatchDetail(param);
        //2850170246107392
        return yueSoundOpenApi.saveOrUpdateManagementClassDetail(param);
        //return Response.success().setData(redisTemplate.opsForValue().get("test"));
    }
    @GetMapping("/test2")
    @ResponseBody
    public String test2() {
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
        StringBuilder sb=new StringBuilder();
        sb.append(OA_ATTACHMENT_FROMS_START).append(OA_KM_FD_KEY_START).append(OA_KM_FD_KEY_END).append(OA_KM_FD_FILENAME_START).append(OA_KM_FD_FILENAME_END).append(OA_KM_FD_ATTACHMENT_START).append(OA_KM_FD_ATTACHMENT_END).append(OA_ATTACHMENT_FROMS_END)
                .append(OA_ATTACHMENT_VALUES_START).append(OA_ATTACHMENT_VALUES_END).append(OA_AUTH_AREA_ID_START).append(OA_AUTH_AREA_ID_END).append(OA_DOC_CONTENT_START).append(OA_DOC_CONTENT_END).append(OA_DOC_CREATOR_START).append("{\"PersonNo\": \"00012\"}").append(OA_DOC_CREATOR_END).append(OA_DOC_PROPERTY_START).append("{}")
                .append(OA_DOC_PROPERTY_END).append(OA_DOC_STATUS_START).append(OA_DOC_STATUS_END).append(OA_DOC_SUBJECT_START).append(OA_DOC_SUBJECT_END).append(OA_FDID_START).append(OA_FDID_END
        ).append(OA_FD_KEYWORD_START).append(OA_FD_KEYWORD_END).append(OA_FD_SOURCE_START).append(OA_FD_SOURCE_END).append(OA_FD_TEMPLATEID_START).append(OA_FD_TEMPLATEID_END).append(OA_FLOW_PARAM_START).append("{}").append(OA_FLOW_PARAM_END).append(OA_FROM_VALUES_START).append("{}").append(OA_FROM_VALUES_END);
        URL wsdlURL = IKmReviewWebserviceServiceService.WSDL_LOCATION;
        IKmReviewWebserviceServiceService ss = new IKmReviewWebserviceServiceService(wsdlURL, SERVICE_NAME);
        IKmReviewWebserviceService port = ss.getIKmReviewWebserviceServicePort();
        com.local.service.km.webservice.KmReviewParamterForm _addReview_arg0 = new com.local.service.km.webservice.KmReviewParamterForm();
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
        }

        return "ssss";
    }

}

