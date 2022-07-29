package com.local.controller;

import com.local.service.YueSound.YueSoundOpenApi;
import com.local.util.Response;
import com.local.util.YueSoundParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author hhs
 * @create 2022-07-08 14:45
 */
@RequestMapping(value = "/tran")
@RestController
public class TranDataController {

    @Autowired
    private YueSoundOpenApi yueSoundOpenApi;
    //bip物料分类同步u9料品分类
    @PostMapping("/U9CreateItemCategory")
    public Response saveOrUpdateManagementClassDetail(@RequestBody YueSoundParam param){
        return yueSoundOpenApi.saveOrUpdateManagementClassDetail(param);
    }
    //bip物料同步u9料品
    @PostMapping("/U9ItemMasterAddCreate")
    public Response saveOrUpdateProduct(@RequestBody YueSoundParam param){
        return yueSoundOpenApi.saveOrUpdateProduct(param);
    }
    //bip客户分类同步u9客户分类
    @PostMapping("/U9CustCategoryCreateSyncer")
    public Response saveOrUpdateCustcategory(@RequestBody YueSoundParam param){
        return yueSoundOpenApi.saveOrUpdateCustcategory(param);
    }
    //bip客户行业同步u9客户行业
    @PostMapping("/U9TradeCategoryCreate")
    public Response saveOrUpdateCustomerTrade(@RequestBody YueSoundParam param){
        return yueSoundOpenApi.saveOrUpdateCustomerTrade(param);
    }
    //bip员工同步u9业务员
    @PostMapping("/U9OperatorCreateSyncer")
    public Response saveOrUpdareStaffDetail(@RequestBody YueSoundParam param){
        return yueSoundOpenApi.saveOrUpdareStaffDetail(param);
    }

}
