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
