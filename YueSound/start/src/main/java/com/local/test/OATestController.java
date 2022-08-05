package com.local.test;

import com.alibaba.fastjson.JSONObject;
import com.local.util.StringUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @author hhs
 * @create 2022-07-22 15:14
 */
@Slf4j
@RestController
@RequestMapping("/integrateThd")
public class OATestController {
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
    @PostMapping("/todoReceive")
    public String todoReceive(@RequestBody String json){
        Map returnMap=new HashMap();
        log.info("bip提交单子数据:"+json);
        if(StringUtil.isEmpty(json)){
            returnMap.put("success",Boolean.FALSE);
            returnMap.put("message","接收到的数据为空");
            return JSONObject.toJSON(returnMap).toString();
        }else{
            returnMap.put("success",Boolean.TRUE);
            returnMap.put("message","接收成功");
            return JSONObject.toJSON(returnMap).toString();
        }

    }

    @PostMapping("/toTranReceive")
    public String toTranReceive(@RequestBody String json){
        Map returnMap=new HashMap();
        log.info("接收bip已办单子数据:"+json);
        if(StringUtil.isEmpty(json)){
            returnMap.put("success",Boolean.FALSE);
            returnMap.put("message","接收到的数据为空");
            return JSONObject.toJSON(returnMap).toString();
        }else{
            returnMap.put("success",Boolean.TRUE);
            returnMap.put("message","接收成功");
            return JSONObject.toJSON(returnMap).toString();
        }
    }
}
