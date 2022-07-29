package com.local.service.YueSound;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.local.YueSound.YueSoundUtils;
import com.local.entity.YueSound.YueSoundCustcategory;
import com.local.entity.YueSound.YueSoundManagementClass;
import com.local.entity.YueSound.YueSoundUnit;
import com.local.service.U9.U9Interface;
import com.local.util.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;



/**
 * @author hhs
 * @create 2022-07-01 11:27
 */
@Slf4j
@Service
public class YueSoundOpenImpl implements YueSoundOpenApi {
    @Value("${yueSound.appKey:5ff99ad0f5c646088f336eb7b0322cc9}")
    private  String appKey;
    //获取物料分类树请求路径
    @Value("${yueSound.managementClassTreeUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/managementclass/tree}")
    private String managementClassTreeUrl;
    //获取物料分类详情请求路径
    @Value("${yueSound.managementClassDetailUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/managementclass/detail}")
    private String managementClassDetailUrl;
    @Value("${yueSound.managementClassSaveUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/managementclass/save}")
    private String managementClassSaveUrl;
    //获取物料档案批量详情请求路径
    @Value("${yueSound.batchDetailUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/product/batch}")
    private String batchDetailUrl;
    //获取客户分类树请求路径
    @Value("${yueSound.custcategoryTreeUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/custcategory/tree}")
    private String custcategoryTreeUrl;
    //获取客户分类详情请求路径
    @Value("${yueSound.custcategoryDetailUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/custcategory/detail}")
    private String custcategoryDetailUrl;
    //获取物料详情请求路径
    @Value("${yueSound.productDetailUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/product/detail}")
    private String productDetailUrl;
    //获取计量单位树请求路径
    @Value("${yueSound.unitListUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/unit/list}")
    private String unitListUrl;
    //客户档案
    @Value("${yueSound.merchantDetailUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/merchant/detail}")
    private String merchantDetailUrl;
    //
    @Value("${yueSound.customerUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/crm/customer/getbyid}")
    private String customerUrl;
    //获取客户行业详情请求路径
    @Value("${yueSound.customertradeDetailUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/customertrade/detail}")
    private String customertradeDetailUrl;
    @Value("${yueSound.staffUserUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/staff/detail}")
    private String staffUserUrl;
    @Value("${yueSound.adminDeptDetailUrl:https://ywzt.chinahys.com/iuap-api-gateway/yonbip/digitalModel/admindept/detail}")
    private String adminDeptDetailUrl;
    private static final String cacheName = "YueSound_cache";
    @Autowired
    private YueSoundUtils yueSoundUtils;
    @Autowired
    private CacheManager cacheManager;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private U9Interface u9Interface;
    private static  final  String SUCCESS_CODE="200";
    private static  final  String FLAG_TRUE="true";
    private static  final  String FLAG_FALSE="false";



    //获取物料分类详情并调用u9料品分类新增接口
    @Override
    public Response saveOrUpdateManagementClassDetail(YueSoundParam param) {
        Response response = this.generateToken();
        if (ObjectUtils.isEmpty(response.getData())){
            return response;
        }
        String token =(String)response.getData();
        Map<String,Object> map =new HashMap<>();
        map.put("access_token",token);
        map.put("id",param.getId());
        JSONObject jsonObject = yueSoundUtils.getYueSoundData(managementClassDetailUrl, map, null, ConvertConstant.GET_METHOD);
        if(jsonObject.get("code").equals(SUCCESS_CODE)){
            Map data = JSONObject.parseObject(jsonObject.get("data").toString(), Map.class);
            //调用U9新增料品分类接口
           response = u9Interface.CreateItemCategory(data);
           if(!response.isSuccess()){
               //调用bip接口进行回写错误信息
               Response res = this.generateToken();
               if (ObjectUtils.isEmpty(res.getData())){
                   return response;
               }
               String accessToken =(String)res.getData();
               Map<String,Object> urlMap=new HashMap<>();
               urlMap.put("access_token",accessToken);
               Map<String,Object>outerMap=new HashMap<>();
               Map<String,Object> dataMap=new HashMap<>();
               Map<String,Object> nameMap=new HashMap<>();
               nameMap.put("zh_CN","透明胶带");
               dataMap.put("id",param.getId());
               dataMap.put("code",data.get("code"));
               dataMap.put("_status",ConvertConstant.ACTION_UPDATE);
               dataMap.put("name",nameMap);
               dataMap.put("define60","false");
               dataMap.put("define59","该分类已存在");
               outerMap.put("data",dataMap);
               JSONObject yueSoundData = yueSoundUtils.getYueSoundData(managementClassSaveUrl, urlMap, outerMap, ConvertConstant.POST_METHOD);
               System.out.println(yueSoundData);
               //如果失败放到缓存里面
               //saveFailSendIdsCache(param,"ManagementClassIds");
           }

        }else{
            //如果失败放到缓存里面
            //saveFailSendIdsCache(param,"ManagementClassIds");
            return Response.fail().setMsg(jsonObject.get("message").toString());
        }
        return response;
    }

    private void saveFailSendIdsCache(YueSoundParam param,String key) {
        Object detailIds = redisTemplate.opsForValue().get(key);
        if(ObjectUtils.isEmpty(detailIds)){
            redisTemplate.opsForValue().set(key,param.getId());
        }else{
            String ids=(String) detailIds;
            ids=ids+","+param.getId();
            redisTemplate.opsForValue().set(key,ids);
        }
    }

    //获取计量单位列表
    @Async
    @Override
    public Response getUnitList() {
        Response response = this.generateToken();
        if(ObjectUtils.isEmpty(generateToken())){return response;}
        String token=(String) response.getData();
        Map<String,Object> map =new HashMap<>();
        map.put("access_token",token);
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("pageIndex",1);
        paramMap.put("pageSize",100);
        JSONObject jsonObject = yueSoundUtils.getYueSoundData(unitListUrl, map, paramMap, ConvertConstant.POST_METHOD);
        if(jsonObject.get("code").equals(SUCCESS_CODE)){
            Map dataMap = JSONObject.parseObject(jsonObject.get("data").toString(), Map.class);
            if(!ObjectUtils.isEmpty(dataMap.get("recordList"))){
                List<YueSoundUnit> yueSoundUnits = JSONArray.parseArray(dataMap.get("recordList").toString(), YueSoundUnit.class);
                Object yueSoundUnitList = redisTemplate.opsForValue().get("YueSoundUnitList");
                if(ObjectUtils.isEmpty(yueSoundUnitList)){
                    redisTemplate.opsForValue().set("YueSoundUnitList",JSONObject.toJSON(yueSoundUnits).toString(),12,TimeUnit.HOURS);
                    response.setData(yueSoundUnits);
                }else{
                    List<YueSoundUnit> yueSoundUnitLists = JSONArray.parseArray(yueSoundUnitList.toString(), YueSoundUnit.class);
                    response.setData(yueSoundUnitLists);
                }
            }
            return response;
        }else{
            return Response.fail().setMsg(jsonObject.get("message").toString());
        }

    }

    //获取物料分类树
    @Override
    public Response getManagementClassTree() {
        List<YueSoundManagementClass> parent=new ArrayList<>();
        Response response = this.generateToken();
        if(!ObjectUtils.isEmpty(response.getData())) {
            String token = (String)response.getData();
            Map<String,Object> map=new HashMap<>();
            map.put("access_token",token);
            JSONObject jsonObject = yueSoundUtils.getYueSoundData(managementClassTreeUrl,map,null, ConvertConstant.POST_METHOD);
            if(jsonObject.get("code").equals(SUCCESS_CODE)){
                List<YueSoundManagementClass> managementClassList = JSONArray.parseArray(jsonObject.get("data").toString(), YueSoundManagementClass.class);
                //将分类树递归赋值
               // List<YueSoundManagementClass> list=managementClassList.stream().filter(item->item.getStopstatus().equals(FLAG_FALSE)).collect(Collectors.toList());
                if(!CollectionUtils.isEmpty(managementClassList)){
                    recursiveParent(managementClassList,parent);
                    //saveManagementClassCache(parent);
                }

              /*  for(YueSoundManagementClass managementClass:parent){
                    if(!CollectionUtils.isEmpty(managementClass.getChildren())){
                        managementClass.getChildren().stream().forEach(item->{
                            item.setPCode(managementClass.getCode());
                            child.add(item);
                        });
                    }
                }*/

                return response.setData(parent);
            }else{
                log.info("获取物料分类树失败:"+jsonObject.get("message").toString());
                return Response.fail().setMsg("获取物料分类树失败:"+jsonObject.get("message").toString());
            }
        }
        return response;
    }

    //获取物料档案批量详情并调用U9接口保存
    @Override
    public Response saveOrUpdateProduct(YueSoundParam yueSoundParam) {
        Response response = this.generateToken();
        if(ObjectUtils.isEmpty(response.getData())){
            return Response.fail().setMsg(response.getMsg());
        }else{
            String id=yueSoundParam.getId();
            String code=yueSoundParam.getCode();
            List<String> ids =null;
            List<String> codes=null;
            if(!StringUtil.isEmpty(id)){
                ids=Arrays.asList(id);
            }else{
                ids=new ArrayList<>();
            }
            if(!StringUtil.isEmpty(code)){
                codes=Arrays.asList(code);
            }else{
                codes=new ArrayList<>();
            }
            String token=(String)response.getData();
            Map<String,Object> urlMap=new HashMap<>();
            urlMap.put("access_token",token);
            Map<String,Object> map=new HashMap<>();
            Map<String,Object> param=new HashMap<>();
            param.put("id",ids);
            param.put("code",codes);
            map.put("data",param);
            JSONObject jsonObject = yueSoundUtils.getYueSoundData(batchDetailUrl, urlMap, map, ConvertConstant.POST_METHOD);
            if(jsonObject.get("code").equals(SUCCESS_CODE)){
                Map<String, Object> innerMap = jsonObject.getInnerMap();
                response=u9Interface.ItemMasterAddCreate(innerMap);
                if(!response.isSuccess()){
                    //失败加入缓存
                    //saveFailSendIdsCache(yueSoundParam,"productDetailIds");
                }
            }else{
                //失败加入缓存
                //saveFailSendIdsCache(yueSoundParam,"productDetailIds");
                return Response.fail().setMsg(jsonObject.get("message").toString());
            }

        }
        return response;
    }


    //保存或修改客户行业
    @Override
    public Response saveOrUpdateCustomerTrade(YueSoundParam param) {
        Response response = this.generateToken();
        if(ObjectUtils.isEmpty(response.getData())){return response;}
        String token=(String) response.getData();
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("access_token", token);
        paramMap.put("id", param.getId());
        JSONObject jsonObject = yueSoundUtils.getYueSoundData(customertradeDetailUrl, paramMap, null, ConvertConstant.GET_METHOD);
        if(jsonObject.get("code").equals(SUCCESS_CODE)){
            Map data = jsonObject.parseObject(jsonObject.get("data").toString(), Map.class);
            //调用U9接口
            response = u9Interface.TradeCategoryCreate(data);
        }else{
            return Response.fail().setMsg(jsonObject.get("message").toString());
        }
        return response;
    }

    //保存或修改正式用户
    @Override
    public Response saveOrUpdateCustomer(YueSoundParam param) {
        Response response = this.generateToken();
        if(ObjectUtils.isEmpty(response.getData())){return response;}
        String token=(String) response.getData();
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("access_token", token);
        paramMap.put("id", param.getId());
        paramMap.put("orgId", param.getOrgId());
        JSONObject jsonObject = yueSoundUtils.getYueSoundData(customerUrl, paramMap, null, ConvertConstant.GET_METHOD);
        if(jsonObject.get("code").equals(SUCCESS_CODE)){
            Map data = jsonObject.parseObject(jsonObject.get("data").toString(), Map.class);
            //调用U9接口

        }else{
            return Response.fail().setMsg(jsonObject.get("message").toString());
        }

        return response;
    }

    //保存或修改员工
    @Override
    public Response saveOrUpdareStaffDetail(YueSoundParam param) {
        if(ObjectUtils.isEmpty(param.getId())||ObjectUtils.isEmpty(param.getCode())){
            return Response.fail().setMsg("员工编码和id必须同时录入");
        }
        Response response = this.generateToken();
        if(ObjectUtils.isEmpty(response.getData())){return response;}
        String token = (String) response.getData();
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("access_token", token);
        paramMap.put("id", param.getId());
        paramMap.put("code",param.getCode());
        JSONObject jsonObject = yueSoundUtils.getYueSoundData(staffUserUrl, paramMap, null, ConvertConstant.GET_METHOD);
        if(jsonObject.get("code").equals(SUCCESS_CODE)){
            Map data = JSONObject.parseObject(jsonObject.get("data").toString(), Map.class);
            //获取部门id调用bip部门详情接口获取部门编码
             String deptCode=getDeptCodeById(data);
             data.put("deptCode",deptCode);
            //调用U9新增业务员接口
            response = u9Interface.OperatorCreateSyncer(data);
        } else{
            return Response.fail().setMsg(jsonObject.get("message").toString());
        }
        return response;
    }

    //获取部门编码
    private String getDeptCodeById(Map data) {
        List<Map> mainJobList = JSONArray.parseArray(data.get("mainJobList").toString(), Map.class);
        Map map = mainJobList.get(0);
        String deptId=map.get("dept_id").toString();
        Response response = this.getDeptDetail(deptId);
        if(ObjectUtils.isEmpty(response.getData())){
            return null;
        }
        Map dataMap=(Map) response.getData();
        return dataMap.get("code").toString();
    }


    @Override
    //保存或修改客户分类接口
    public Response saveOrUpdateCustcategory(YueSoundParam param) {
        Response response = this.generateToken();
        if(ObjectUtils.isEmpty(response.getData())){return response;}
        String token = (String)response.getData();
        String custCategoryApplyRangesId="";
        Map<String, Object> map = new HashMap<>();
        map.put("access_token", token);
        map.put("id", param.getId());
        //custCategoryApplyRangesId= getCacheCustcategory(param);
        //if(ObjectUtils.isEmpty(custCategoryApplyRangesId)&&ObjectUtils.isEmpty(param.getCustCategoryApplyRangesId())){return Response.fail().setMsg("客户分类适用范围id未设置");}
        map.put("custCategoryApplyRangesId", param.getCustCategoryApplyRangesId());
        JSONObject jsonObject = yueSoundUtils.getYueSoundData(custcategoryDetailUrl, map, null, ConvertConstant.GET_METHOD);
        if (jsonObject.get("code").equals(SUCCESS_CODE)) {
            Map data = JSONObject.parseObject(jsonObject.get("data").toString(), Map.class);
            //调用U9客户分类新增接口
            response = u9Interface.CustCategoryCreateSyncer(data);
            if(!response.isSuccess()){
                //失败写入缓存
                //saveFailSendIdsCache(param,"custCategoryApplyIds");
            }
        }else{
            //失败写入缓存
            //saveFailSendIdsCache(param,"custCategoryApplyIds");
            return Response.fail().setMsg(jsonObject.get("message").toString());
        }

       return response;
    }

    //尝试获取某个客户分类的适用范围id
    private String getCacheCustcategory(YueSoundParam param) {
        this.getCustcategoryTree();
        Object categorueList = redisTemplate.opsForValue().get("CategorueList");
        if(!ObjectUtils.isEmpty(categorueList)){
            List<YueSoundCustcategory> yueSoundCustcategoryList = JSONArray.parseArray(categorueList.toString(), YueSoundCustcategory.class);
            List<String> ids = yueSoundCustcategoryList.stream().filter(item -> item.getId().toString().equals(param.getId())).map(YueSoundCustcategory::getCustCategoryApplyRanges_id).collect(Collectors.toList());
            if(!CollectionUtils.isEmpty(ids)){return  ids.get(0);}
        }
        if(!ObjectUtils.isEmpty(param.getCustCategoryApplyRangesId())){
            return param.getCustCategoryApplyRangesId();
        }
        return null;
    }

    //获取部门详情信息
    @Override
    public Response getDeptDetail(String id) {
        Response response = this.generateToken();
        String token = (String)response.getData();
        response.setData(null);
        if(ObjectUtils.isEmpty(token)){
            return Response.fail().setMsg(response.getMsg());
        } else{
            Map<String,Object> map=new HashMap<>();
            map.put("access_token",token);
            map.put("id",id);
            JSONObject jsonObject = yueSoundUtils.getYueSoundData(adminDeptDetailUrl, map, null, ConvertConstant.GET_METHOD);
           if(jsonObject.get("code").equals(SUCCESS_CODE)) {
               Map data = JSONObject.parseObject(jsonObject.get("data").toString(), Map.class);
               response.setData(data);
           }else{
               return Response.fail().setMsg(jsonObject.get("message").toString());
           }

        }
        return response;
    }

    @Async
    @Override
    //获取客户分类树
    public Response getCustcategoryTree() {
        List<YueSoundCustcategory> yueSoundCustcategoryList=new ArrayList<>();
        Response response = this.generateToken();
        String token = (String)response.getData();
        if(ObjectUtils.isEmpty(token)){
            return Response.fail().setMsg(response.getMsg());
        }
        else{
            Map<String,Object> map=new HashMap<>();
            map.put("access_token",token);
            if(ObjectUtils.isEmpty(redisTemplate.opsForValue().get("CategorueList"))){
                JSONObject jsonObject = yueSoundUtils.getYueSoundData(custcategoryTreeUrl, map, null, ConvertConstant.POST_METHOD);
                if(jsonObject.get("code").equals(SUCCESS_CODE)){
                    List<YueSoundCustcategory> custcategoryList = JSONArray.parseArray(jsonObject.get("data").toString(), YueSoundCustcategory.class);
                    //List<YueSoundCustcategory> custcategories = custcategoryList.stream().filter(item -> item.getIsEnabled().equals(Boolean.TRUE)).collect(Collectors.toList());
                    if(!CollectionUtils.isEmpty(custcategoryList)){
                        recursiveCategorues(custcategoryList,yueSoundCustcategoryList);
                        Object json = JSONObject.toJSON(yueSoundCustcategoryList);
                        redisTemplate.opsForValue().set("CategorueList",json.toString(),12, TimeUnit.HOURS);
                        response.setData(yueSoundCustcategoryList);
                    }else {response.setData(null);}

                }else{ response.setData(null); }

            }else{
                Object categorueList = redisTemplate.opsForValue().get("CategorueList");
                yueSoundCustcategoryList = JSONArray.parseArray(categorueList.toString(), YueSoundCustcategory.class);
                response.setData(yueSoundCustcategoryList);
            }
        }
        return response;
    }
  /*  //@Cacheable(value = cacheName,key = "'YueSoundCustcategory:'+'cache'")
    public void saveCategorueListCache(List<YueSoundCustcategory> yueSoundCustcategoryList) {
        Cache cache = cacheManager.getCache(cacheName);
        cache.put("CategorueList",yueSoundCustcategoryList);
        if(ObjectUtils.isEmpty(redisTemplate.opsForValue().get("CategorueList"))){
            Object json = JSONObject.toJSON(yueSoundCustcategoryList);
            redisTemplate.opsForValue().set("CategorueList",json.toString());
        }else{
            Object categorueList = redisTemplate.opsForValue().get("CategorueList");
            yueSoundCustcategoryList = JSONArray.parseArray(categorueList.toString(), YueSoundCustcategory.class);
        }
    }*/

    //递归客户分类树
    private void recursiveCategorues(List<YueSoundCustcategory> custcategories, List<YueSoundCustcategory> yueSoundCustcategoryList) {
        for(YueSoundCustcategory custcategory:custcategories){
            if(!custcategory.getIsEnd()){
                if(custcategory.getLevel()==1){
                    custcategory.setPcode("0");
                    yueSoundCustcategoryList.add(custcategory);
                    custcategory.getChildren().stream().forEach(item->{
                        item.setPcode(custcategory.getCode());
                    });
                    recursiveCategorues(custcategory.getChildren(),yueSoundCustcategoryList);
                }else {
                    yueSoundCustcategoryList.add(custcategory);
                    custcategory.getChildren().stream().forEach(item->{
                        item.setPcode(custcategory.getCode());
                    });
                    recursiveCategorues(custcategory.getChildren(),yueSoundCustcategoryList);
                }

            }else {
                 yueSoundCustcategoryList.add(custcategory);
            }

        }

    }

    //递归分类树
    private void recursiveParent(List<YueSoundManagementClass> list,  List<YueSoundManagementClass> parent) {
        for(YueSoundManagementClass managementClass:list){
            if(!managementClass.getIsEnd()){
                if(managementClass.getLevel()==1){
                    managementClass.setPCode("0");
                    parent.add(managementClass);
                    managementClass.getChildren().stream().forEach(item -> {
                        item.setPCode(managementClass.getCode());
                    });
                    recursiveParent(managementClass.getChildren(), parent);
                }else {
                    parent.add(managementClass);
                    managementClass.getChildren().stream().forEach(item -> {
                        item.setPCode(managementClass.getCode());
                    });
                    recursiveParent(managementClass.getChildren(),  parent);
                }
            }else{
                parent.add(managementClass);
            }
        }



    }

    //将料品分类放入缓存,获取新的料品分类时进行判断,同步增量数据并请求U9接口同步数据
    @Cacheable(value = cacheName,key = "'YueSoundManagementClass:'+'cache'")
     public void saveManagementClassCache(List<YueSoundManagementClass> parent) {
        Cache cache = cacheManager.getCache(cacheName);
        cache.put("parentManagementClass",parent);
    /*    Cache cache = cacheManager.getCache(cacheName);
        if(ObjectUtils.isEmpty(cache)||ObjectUtils.isEmpty(cache.get("parentManagementClass"))){
            cache.put("parentManagementClass",parent);
            //cache.put("childManagementClass",child);

        }else{
            List<YueSoundManagementClass> BeforeParentManagement=(List<YueSoundManagementClass>)cache.get("parentManagementClass").get();
           // List<YueSoundManagementClass> BeforeChildManagement=(List<YueSoundManagementClass>)cache.get("childManagementClass").get();
            //获取到物料分类增量数据
            parent=parent.stream().filter(item->
                !BeforeParentManagement.stream().filter(before ->
                    item.getCode().equals(before.getCode()) || item.getPubts().equals(before.getPubts())
                 ).map(YueSoundManagementClass::getCode).collect(Collectors.toList()).contains(item.getCode())
            ).collect(Collectors.toList());
         *//* //获取到物料增量数据
            child=parent.stream().filter(item->
                    !BeforeChildManagement.stream().filter(before ->
                            item.getCode().equals(before.getCode()) || item.getPubts().equals(before.getPubts())
                    ).map(YueSoundManagementClass::getCode).collect(Collectors.toList()).contains(item.getCode())
            ).collect(Collectors.toList());*//*
            // 获取详细信息

        }*/

    }

    //获取token
    @Override
    @Cacheable(value = cacheName ,key = "'ys_token:'+'ys@Cache'")
    public Response generateToken() {
        String token="";
        Map<String,Object> param=new HashMap<>();
        param.put("appKey",appKey);
        param.put("timestamp",DateUtil.current());
        Cache cache = cacheManager.getCache(cacheName);
        if(!ObjectUtils.isEmpty(cache)&&!ObjectUtils.isEmpty(cache.get("ys_token"))&&!ObjectUtils.isEmpty(cache.get("timestamp"))){
            String timestamp = String.valueOf(cache.get("timestamp").get());
            long beforeTimestamp = Long.parseLong(timestamp);
            long currTimestamp = DateUtil.current();

                long l = (currTimestamp - beforeTimestamp) / 1000 / (60 * 60);
                if(l>2){
                    if(!ObjectUtils.isEmpty(yueSoundUtils.generateToken(param))){
                        token = (String) yueSoundUtils.generateToken(param).getData();
                        cache.put("ys_token",token);
                        cache.put("timestamp",param.get("timestamp").toString());
                    }else{
                        token=String.valueOf(cache.get("ys_token").get());
                    }
                }else{
                    token=String.valueOf(cache.get("ys_token").get());
                }
            return Response.success().setData(token);
        }else{
            Response response = yueSoundUtils.generateToken(param);
            if(ObjectUtils.isEmpty(response.getData())){return response;}
            token=(String)response.getData();
            cache.put("ys_token",token);
            cache.put("timestamp",param.get("timestamp").toString());
            return response.setData(token);
        }

    }

    //保存客户档案
    @Override
    public Response saveMerchantDetail(YueSoundParam param) {
        if(ObjectUtils.isEmpty(param.getId())||ObjectUtils.isEmpty(param.getCode())){
            return Response.fail().setMsg("请确认客户档案ID或客户档案编码是否传入");
        }
        if(ObjectUtils.isEmpty(param.getOrgId())&&ObjectUtils.isEmpty(param.getMerchantApplyRangeId())){
            return Response.fail().setMsg("组织id，与merchantApplyRangeId必输一个");
        }
        Response response = this.generateToken();
        if(ObjectUtils.isEmpty(response.getData())){return response;}
        String token=(String) response.getData();
        Map<String,Object> paramMap=new HashMap<>();
        paramMap.put("access_token", token);
        paramMap.put("id", param.getId());
        paramMap.put("code", param.getCode());
        paramMap.put("merchantApplyRangeId", param.getMerchantApplyRangeId());
        paramMap.put("orgId", param.getOrgId());
        JSONObject jsonObject = yueSoundUtils.getYueSoundData(merchantDetailUrl, paramMap, null, ConvertConstant.GET_METHOD);
        if(jsonObject.get("code").equals(SUCCESS_CODE)){
            Map data = jsonObject.parseObject(jsonObject.get("data").toString(), Map.class);
            //调用U9接口
            response = u9Interface.CustomerCreateSyncer(data);
            // 地址信息
            Object merchantAddressInfos = data.get("merchantAddressInfos");
            if(!ObjectUtils.isEmpty(merchantAddressInfos)){
                Map map = JSONObject.parseObject(merchantAddressInfos.toString(), Map.class);

            }
            // 联系人信息
            Object merchantContacterInfos = data.get("merchantContacterInfos");
            if(!ObjectUtils.isEmpty(merchantContacterInfos)){
                Map map = JSONObject.parseObject(merchantContacterInfos.toString(), Map.class);
            }
            // 发票信息
            Object merchantAgentInvoiceInfos = data.get("merchantAgentInvoiceInfos");
            if(!ObjectUtils.isEmpty(merchantAgentInvoiceInfos)){
                List list = JSONArray.parseObject(merchantAgentInvoiceInfos.toString(), List.class);
                Map map = JSONObject.parseObject(list.get(0).toString(), Map.class);
            }
            // 客户
            if(!response.isSuccess()){
                //失败加入缓存
                //saveFailSendIdsCache(param,"merchantIds");
            }
        }else{
            //失败加入缓存
            //saveFailSendIdsCache(param,"merchantIds");
            return Response.fail().setMsg(jsonObject.get("message").toString());
        }
        return response;
    }

}
