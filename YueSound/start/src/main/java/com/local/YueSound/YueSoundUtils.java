package com.local.YueSound;

import cn.hutool.core.util.XmlUtil;
import com.alibaba.fastjson.JSONObject;
import com.local.util.ConvertConstant;
import com.local.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.binary.Base64;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.LaxRedirectStrategy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.*;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import javax.crypto.Mac;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * @author hhs
 * @create 2022-07-01 10:56
 */
@Slf4j
@Component
public class YueSoundUtils {
    @Value("${yueSound.appSecret:64ffcbd3830846018cfbc12271c1920c}")
    private  String appSecret;
    @Value("${yueSound.generateTokenUrl: https://ywzt.chinahys.com/iuap-api-auth/open-auth/selfAppAuth/getAccessToken}")
    private  String generateTokenUrl;
    private static RestTemplate restTemplate = new RestTemplateBuilder().build();
    private static final String HMAC_SHA_256 = "HmacSHA256";
    private String signature;
    private static final String SOAP_ENVELOPE_START = "<soapenv:Envelope ";
    private static final String SOAP_ENVELOPE_END = "</soapenv:Envelope> ";
    private static final String XMLNS_PARAM = "xmlns:soapenv=\\\"http://schemas.xmlsoap.org/soap/envelope/\\\" xmlns:ufid=\\\"http://www.UFIDA.org\\\">";
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
    private static final String OA_URL = "http://192.168.2.220/sys/webservice/kmReviewWebserviceService?wsdl";
    private static final String OA_XMLNS_PARAM="xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">" ;
    private static final String OA_KM_REVIEW_PARAMTER_FROM_START="<soap:kmReviewParamterForm>" ;
    private static final String OA_KM_REVIEW_PARAMTER_FROM_END="</soap:kmReviewParamterForm>" ;
    private static final String OA_ATTACHMENT_FROMS_START="<soap:attachmentForms>" ;
    private static final String OA_ATTACHMENT_FROMS_END="</soap:attachmentForms>" ;
    private static final String OA_ATTACHMENT_VALUES_START="<soap:attachmentValues>" ;
    private static final String OA_ATTACHMENT_VALUES_END="</soap:attachmentValues>" ;
    private static final String OA_AUTH_AREA_ID_START="<soap:authAreaId>" ;
    private static final String OA_AUTH_AREA_ID_END="</soap:authAreaId>" ;
    private static final String OA_DOC_CONTENT_START="<soap:docContent>" ;
    private static final String OA_DOC_CONTENT_END="</soap:docContent>" ;
    private static final String OA_DOC_CREATOR_START="<soap:docCreator>" ;
    private static final String OA_DOC_CREATOR_END="</soap:docCreator>" ;
    private static final String OA_DOC_PROPERTY_START="<soap:docProperty>" ;
    private static final String OA_DOC_PROPERTY_END="</soap:docProperty>" ;
    private static final String OA_DOC_STATUS_START="<soap:docStatus>" ;
    private static final String OA_DOC_STATUS_END="</soap:docStatus>" ;
    private static final String OA_DOC_SUBJECT_START="<soap:docSubject>" ;
    private static final String OA_DOC_SUBJECT_END="</soap:docSubject>" ;
    private static final String OA_FDID_START="<soap:fdId>" ;
    private static final String OA_FDID_END="</soap:fdId>" ;
    private static final String OA_FD_KEYWORD_START="<soap:fdKeyword>" ;
    private static final String OA_FD_KEYWORD_END="</soap:fdKeyword>" ;
    private static final String OA_FD_SOURCE_START="<soap:fdSource>" ;
    private static final String OA_FD_SOURCE_END="</soap:fdSource>" ;
    private static final String OA_FD_TEMPLATEID_START="<soap:fdTemplateId>" ;
    private static final String OA_FD_TEMPLATEID_END="</soap:fdTemplateId>" ;
    private static final String OA_FLOW_PARAM_START="<soap:flowParam>" ;
    private static final String OA_FLOW_PARAM_END="</soap:flowParam>" ;
    private static final String OA_FROM_VALUES_START="<soap:formValues>" ;
    private static final String OA_FROM_VALUES_END="</soap:formValues>" ;

    /* @Autowired
    private RedisTemplate redisTemplate;
    public static RedisTemplate redis;*/
    static {
        HttpComponentsClientHttpRequestFactory factory =
                new HttpComponentsClientHttpRequestFactory();


        CloseableHttpClient httpClient = HttpClientBuilder.create()
                .setRedirectStrategy(new LaxRedirectStrategy())
                .build();

        factory.setHttpClient(httpClient);
        restTemplate.setRequestFactory(factory);
    }

    /*@PostConstruct
    public void setRedisTemplate(){
        redis=this.redisTemplate;
    }*/
    //获取token
    public Response generateToken(Map<String,Object> param){
            String message=getMessage(param);
            signature = getSignature(appSecret,message);
            param.put("signature",signature);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            HttpEntity<Map> requestEntity = new HttpEntity<>(param, headers);
            String url=getSendUrl(param,generateTokenUrl);
            ResponseEntity<Map> resEntity = restTemplate.exchange(url, HttpMethod.GET, requestEntity, Map.class);
            Map body = resEntity.getBody();
            if(body.get("code").equals("00000")){
                Map data = (Map)body.get("data");
                String access_token = (String)data.get("access_token");
                log.info("token:"+access_token);
                return Response.success().setData(access_token);
            }else {
                log.info("获取token失败:"+body.get("message").toString());
                return Response.fail().setMsg(body.get("message").toString());
            }
    }
    //拼接请求路径
    private String getSendUrl(Map<String,Object> paramMap,String url) {
        StringBuilder param = new StringBuilder("?");
        if (paramMap != null) {
            for(Map.Entry<String,Object> entry: paramMap.entrySet()) {
                param.append(entry.getKey());
                param.append("=");
                param.append(entry.getValue());
                param.append("&");
            }
            param.deleteCharAt(param.length() - 1);
        }
        return url+param;
    }

    //获取签名
    public String getSignature(String secret, String message) {
        try {
            Mac mac = Mac.getInstance(HMAC_SHA_256);
            SecretKey secretKey = new SecretKeySpec(secret.getBytes(StandardCharsets.UTF_8), HMAC_SHA_256);
            mac.init(secretKey);
            byte[] hash = mac.doFinal(message.getBytes(StandardCharsets.UTF_8));
            String base64String = Base64.encodeBase64String(hash);
            return base64String;
        } catch (Exception e) {
            log.error("YueSoundUtils -----getSignature 加密失败",e);
           return null;
        }
    }
    //拼接入参参数
    private String getMessage(Map<String, Object> paramMap) {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Object> entry : paramMap.entrySet()) {
            stringBuilder.append(entry.getKey()).append(entry.getValue());
        }
        return stringBuilder.toString();
    }



    //发送请求请求悦声接口
    public JSONObject getYueSoundData(String managementClassTreeUrl, Map<String,Object> urlParam, Map<String, Object> param, String method) {
        ResponseEntity<String> resEntity=null;
        String sendUrl = getSendUrl(urlParam, managementClassTreeUrl);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Map> requestEntity = new HttpEntity<>(param, headers);
        if(method.equals(ConvertConstant.POST_METHOD)){
             resEntity = restTemplate.exchange(sendUrl, HttpMethod.POST, requestEntity, String.class);

        }else{
             resEntity = restTemplate.exchange(sendUrl, HttpMethod.GET, requestEntity, String.class);

        }
        String body = resEntity.getBody();
        return JSONObject.parseObject(body);
    }
    //构建U9 SOAP报文格式
    private String createParameter(String jsonData) {
        StringBuilder sb = new StringBuilder();
        sb.append(SOAP_ENVELOPE_START).append(XMLNS_PARAM).append(SOAP_HEADER_START).append(SOAP_HEADER_END).append(SOAP_BODY_START)
                .append(UFID_DO_START).append(UFID_INSTR_START).append(jsonData).append(UFID_INSTR_END).append(UFID_DO_END)
                .append(SOAP_BODY_END).append(SOAP_ENVELOPE_END);
        return sb.toString().replaceAll("\\\\", "");

    }
    //请求U9接口获取信息
    public Object getU9Data(String json){
        String param = createParameter(json);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/xml;charset=UTF-8");
        headers.add("SOAPAction", SOAP_ACTION);
        HttpEntity<String> requestEntity = new HttpEntity<>(param, headers);
        ResponseEntity<String> resEntity = restTemplate.exchange(TEST_URL, HttpMethod.POST, requestEntity, String.class);
        String message = resEntity.getBody();
        System.out.println(message);
        Map<String, Object> map = XmlUtil.xmlToMap(message);
        Map body =  (Map)map.get("s:Body");
        Map DoResponse =(Map)body.get("DoResponse");
        Object doResult = DoResponse.get("DoResult");
        return doResult;
    }

    public Object getOAData(){
        // 流程表单
        String formValues = "{\"fd_2eddbf023c8292\":\"张三\", \"fd_2edd2f83f68242\":\"咨询部\", \"fd_2edd2fa69f6fc6\":\"\", \"fd_2eddbf09f9bc96\":\"2011-10-26\", \"fd_2edd2fb18e7f90\":{\"fd_2edd2fb18e7f90.fd_2eddbef4da4688\":[\"555555\",\"777777\"], \"fd_2edd2fb18e7f90.fd_2edd2fc8001062\":[\"444444\",\"666666\"], \"fd_2edd2fb18e7f90.fdId\":[\"1332472122898ac618f3e22460cab595\",\"13324721228b50c184d82c44ceca5301\"]}}";
        // 流程参数
        String flowParam = "{auditNode:\"请审核\", futureNodeId:\"N7\", changeNodeHandlers:[\"N7:1183b0b84ee4f581bba001c47a78b2d9;131d019fbac792eab0f0a684c8a8d0ec\"]}";
        StringBuilder sb=new StringBuilder();
        sb.append(SOAP_ENVELOPE_START).append(OA_XMLNS_PARAM).append(SOAP_HEADER_START).append(SOAP_HEADER_END).append(SOAP_BODY_START)
                .append(OA_KM_REVIEW_PARAMTER_FROM_START).append(OA_ATTACHMENT_FROMS_START).append(OA_ATTACHMENT_FROMS_END).append(OA_ATTACHMENT_VALUES_START).append(OA_ATTACHMENT_VALUES_END)
                .append(OA_AUTH_AREA_ID_START).append(OA_AUTH_AREA_ID_END).append(OA_DOC_CONTENT_START).append(OA_DOC_CONTENT_END).append(OA_DOC_CREATOR_START).append("{\"PersonNo\": \"00012\"}").append(OA_DOC_CREATOR_END)
                .append(OA_DOC_PROPERTY_START).append(OA_DOC_PROPERTY_END).append(OA_DOC_STATUS_START).append(OA_DOC_STATUS_END).append(OA_DOC_SUBJECT_START).append("物料申请采购单").append(OA_DOC_SUBJECT_END).append(OA_FDID_START).append(OA_FDID_END)
                .append(OA_FD_KEYWORD_START).append("[\"物料\", \"采购\"]").append(OA_FD_KEYWORD_END).append(OA_FD_SOURCE_START).append(OA_FD_SOURCE_END).append(OA_FD_TEMPLATEID_START).append("131eb0cfd7db55e6980e9ce4985a1387").append(OA_FD_TEMPLATEID_END)
                .append(OA_FLOW_PARAM_START).append(flowParam).append(OA_FLOW_PARAM_END).append(OA_FROM_VALUES_START).append(formValues).append(OA_FROM_VALUES_END).append(OA_KM_REVIEW_PARAMTER_FROM_END).append(SOAP_BODY_END).append(SOAP_ENVELOPE_END);
        System.out.println(sb);
        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Type","text/xml;charset=UTF-8");
        //headers.add("SOAPAction", SOAP_ACTION);
        HttpEntity<String> requestEntity = new HttpEntity<>(sb.toString(), headers);
        ResponseEntity<String> resEntity = restTemplate.exchange(OA_URL, HttpMethod.POST, requestEntity, String.class);
        String message = resEntity.getBody();
        System.out.println(message);
        return null;
    }

}