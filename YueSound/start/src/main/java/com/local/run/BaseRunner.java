package com.local.run;

import com.local.service.YueSound.YueSoundOpenApi;
import com.local.util.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * @author hhs
 * @create 2022-07-06 15:46
 * 启动前先尝试获取基础数据(计量单位树、客户分类树)
 */
@Component
@Slf4j
public class BaseRunner implements CommandLineRunner {
    @Autowired
    private YueSoundOpenApi yueSoundOpenApi;
    @Override
    public void run(String... args)  {
        try{
            Boolean flag=Boolean.TRUE;
            //获取token
            while (flag){
                Response response = yueSoundOpenApi.generateToken();
                flag=response.isSuccess();
            }
            //计量单位树
            yueSoundOpenApi.getUnitList();
            //客户分类树
            //yueSoundOpenApi.getCustcategoryTree();
        }catch (Exception e){
            log.error("BaseRunner 尝试获取基础数据树失败",e);
        }

    }
}
