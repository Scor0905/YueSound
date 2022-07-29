package com.local.test;

import com.local.YueSound.YueSoundUtils;
import com.local.service.YueSound.YueSoundOpenApi;
import com.local.util.Response;
import com.local.util.YueSoundParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.time.Period;

/**
 * @author hhs
 * @create 2022-06-29 11:52
 */
@Controller
@RequestMapping("/yonyou")
public class ControllerTest {

    @Autowired
    private YueSoundOpenApi yueSoundOpenApi;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private YueSoundUtils yueSoundUtils;

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
        yueSoundUtils.getOAData();
        return "ssss";
    }

}

