package com.local.test;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

/**
 * @author hhs
 * @create 2022-07-22 15:14
 */
@Slf4j
@RestController
@RequestMapping("/integrateThd")
public class OATestController {
    @PostMapping("/todoReceive")
    public String test(@RequestBody String json){
        log.info("bip提交单子数据:"+json);
        return json;
    }
}
