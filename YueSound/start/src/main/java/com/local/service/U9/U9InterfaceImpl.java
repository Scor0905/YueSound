package com.local.service.U9;

import cn.hutool.core.date.DateUtil;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.local.YueSound.YueSoundUtils;
import com.local.entity.YueSound.YueSoundUnit;
import com.local.service.YueSound.YueSoundOpenApi;
import com.local.util.ConvertConstant;
import com.local.util.ObjectUtils;
import com.local.util.Response;
import com.local.util.UnitTranUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;
import java.util.stream.Collectors;

/**
 * @author hhs
 * @create 2022-07-04 13:37
 * U9对接接口实现
 */
@Service
@Slf4j
public class U9InterfaceImpl implements U9Interface {
    @Autowired
    private YueSoundUtils yueSoundUtils;
    @Autowired
    private RedisTemplate redisTemplate;
    @Autowired
    private YueSoundOpenApi yueSoundOpenApi;


    //销售退回处理创建
    @Override
    public Response RMACreateSyncer(Map<String, Object> param) {
        if(ObjectUtils.isEmpty(param)){return Response.fail().setMsg("查询bip销售退回处理数据为空");}
        Map<String,Object> outerMap=new HashMap<>();
        outerMap.put("doctype_no","");//单据类型编码
        outerMap.put("items","");//明细行(集合) [{ "line_id": -1,"qty": 0.00}]
        outerMap.put("is_approve",Boolean.FALSE);//名称
        outerMap.put("description","");//描述
        outerMap.put("action","RMA_Create");//操作名
        outerMap.put("projectName","DGHYS");//项目名
        outerMap.put("os_doc_no","");//其他系统单号
        outerMap.put("record_doc_no_field","");//记录其他系统单号的字段
        Map<String,Object> contextMap=new HashMap<>();
        contextMap.put("enterpriseId",ConvertConstant.U9_ENTERPRISEID);
        contextMap.put("orgId",ConvertConstant.U9_ORG_ID);//组织
        contextMap.put("userId",ConvertConstant.U9_USER_ID);//用户
        contextMap.put("userName","bip");//用户
        contextMap.put("operateDateTime",DateUtil.now());//日期
        outerMap.put("context",contextMap);//上下文
        return null;
    }

    //销售退回收货创建
    @Override
    public Response SMRcvCreateSyncer(Map<String, Object> param) {
        if(ObjectUtils.isEmpty(param)){return Response.fail().setMsg("查询bip销售退回收货数据为空");}
        Map<String,Object> outerMap=new HashMap<>();
        outerMap.put("doctype_no","");//单据类型编码
        outerMap.put("wh_no",0);//收获仓编码
        outerMap.put("items","");//退回处理明细行(集合) [{ "line_id": -1,"qty": 0.00,"sn_no_list": ["string"]}],
        outerMap.put("is_approve",Boolean.FALSE);//名称
        outerMap.put("description","");//描述
        outerMap.put("action","SMRcv_Create");//操作名
        outerMap.put("projectName","DGHYS");//项目名
        outerMap.put("os_doc_no","");//其他系统单号
        outerMap.put("record_doc_no_field","");//记录其他系统单号的字段
        Map<String,Object> contextMap=new HashMap<>();
        contextMap.put("enterpriseId",ConvertConstant.U9_ENTERPRISEID);
        contextMap.put("orgId",ConvertConstant.U9_ORG_ID);//组织
        contextMap.put("userId",ConvertConstant.U9_USER_ID);//用户
        contextMap.put("userName","bip");//用户
        contextMap.put("operateDateTime",DateUtil.now());//日期
        outerMap.put("context",contextMap);//上下文
        return null;
    }

    //销售出货创建
    @Override
    public Response ShipCreateSyncer(Map<String, Object> param) {
        if(ObjectUtils.isEmpty(param)){return Response.fail().setMsg("查询bip销售出货单数据为空");}
        List<Map> items=new ArrayList<>();
        List<Map> deliveryDetails = JSONObject.parseObject(param.get("deliveryDetails").toString(), List.class);
        if(!CollectionUtils.isEmpty(deliveryDetails)){
            for (int i = 0; i <deliveryDetails.size() ; i++) {
                int j=i;
                Map map = deliveryDetails.get(i);
                Response data = yueSoundOpenApi.getWareHouseDetail(map.get("stockId").toString());
                Map paramMap=new HashMap();
                paramMap.put("line_no",++j);
                paramMap.put("wh_code",data.getData().toString());
                paramMap.put("qty",map.get("qty"));
                paramMap.put("plan_date",map.get("sendDate"));
                paramMap.put("so_doc_no",map.get("orderNo"));
                paramMap.put("so_docline_no","");
                paramMap.put("sn_no_list","");
                paramMap.put("line_id",-1);
                paramMap.put("wh_id",-1);
                items.add(paramMap);
            }

        }
        Map<String,Object> outerMap=new HashMap<>();
        outerMap.put("doctype_no","SP1");//单据类型编码  //SP1:普通销售  SP2: 内部销售
        outerMap.put("source_type",0);//来源类型(0 销售订单 1 出货计划)
        outerMap.put("doc_date",param.get("createDate"));//来源类型(0 销售订单 1 出货计划)
        /*
        明细行(集合) [{
        "line_no": "", //行号
        "wh_code": "", //仓库编码
        "qty": 0.00, //数量
        "plan_date": "", //计划出货日期
        "so_doc_no": , //销售订单号
        "so_docline_no": "", //销售订单行号
        "sn_no_list": ["",""], //
        "line_id": -1, //销售计划行id
        "wh_id": -1, //仓库id
        * */
        outerMap.put("items",items);//
        outerMap.put("is_approve",Boolean.FALSE);//名称
        outerMap.put("description","");//描述
        outerMap.put("action","Ship_Create");//操作名
        outerMap.put("projectName","DGHYS");//项目名
        outerMap.put("os_doc_no","");//其他系统单号
        outerMap.put("record_doc_no_field","");//记录其他系统单号的字段
        Map<String,Object> contextMap=new HashMap<>();
        contextMap.put("enterpriseId",ConvertConstant.U9_ENTERPRISEID);
        contextMap.put("orgId",ConvertConstant.U9_ORG_ID);//组织
        contextMap.put("userId",ConvertConstant.U9_USER_ID);//用户
        contextMap.put("userName","bip");//用户
        contextMap.put("operateDateTime",DateUtil.now());//日期
        outerMap.put("context",contextMap);//上下文
        String json = JSONObject.toJSON(outerMap).toString();
        Object data = yueSoundUtils.getU9Data(json);
        Map u9Data = JSONObject.parseObject(data.toString(), Map.class);
        if(u9Data.get("isSuccess").equals(Boolean.TRUE)){
            return Response.success().setMsg("新增销售发货单成功");
        }
        return Response.fail().setMsg(u9Data.get("message").toString());
    }

    //销售订单创建
    @Override
    public Response SOCreateSyncer(Map<String, Object> param) {
        if(ObjectUtils.isEmpty(param)){return Response.fail().setMsg("查询bip销售订单数据为空");}
        List<Map> items=new ArrayList<>();
        String vouchdate = param.get("vouchdate").toString(); //单据日期
        String vouchDateTime = DateUtil.format(DateUtil.parseDate(vouchdate), "yyyy-MM-dd");
        List<Map> orderDetails = JSONObject.parseObject(param.get("orderDetails").toString(), List.class);
        for (int i = 0; i <orderDetails.size() ; i++) {
            int j=i;
            Map map = orderDetails.get(i);
            Map data=new HashMap();
            data.put("line_no",++j);
            data.put("item_code",map.get("productCode"));
            data.put("qty",Double.parseDouble(map.get("qty").toString()));
            Map orderDetailPrices = (Map)map.get("orderDetailPrices");
            data.put("price",orderDetailPrices.get("natTaxUnitPrice"));
            data.put("final_price",orderDetailPrices.get("natSum"));
            items.add(data);
        }
        Map<String,Object> outerMap=new HashMap<>();
        outerMap.put("doctype_no","SO3");//单据类型编码
        outerMap.put("doc_date",vouchDateTime);//单据日期
        outerMap.put("cust_code",param.get("agentId_code"));//客户编码
        outerMap.put("seller_code","");//业务员编码
        outerMap.put("dept_code","");//部门编码
        outerMap.put("created_by","");//创建人
        outerMap.put("created_on",param.get("createTime"));//创建时间
        outerMap.put("ship_address",param.get("orderShippingAddress"));//收货地址
        outerMap.put("ship_to",param.get("receiver"));//收货人

        /*
        [ { "line_no": 0,
            "item_code": "string",
            "qty": 0.00,
            "price": 0.00,
            "final_price": 0.00} ]
        * */
        outerMap.put("items",items);//销售订单明细行(集合)
        outerMap.put("is_approve",Boolean.FALSE);//名称
        outerMap.put("description","");//描述
        outerMap.put("action","SO_Create");//操作名
        outerMap.put("projectName","DGHYS");//项目名
        outerMap.put("os_doc_no","");//其他系统单号
        outerMap.put("record_doc_no_field","");//记录其他系统单号的字段
        Map<String,Object> contextMap=new HashMap<>();
        contextMap.put("enterpriseId",ConvertConstant.U9_ENTERPRISEID);
        contextMap.put("orgId",ConvertConstant.U9_ORG_ID);//组织
        contextMap.put("userId",ConvertConstant.U9_USER_ID);//用户
        contextMap.put("userName","bip");//用户
        contextMap.put("operateDateTime",DateUtil.now());//日期
        outerMap.put("context",contextMap);//上下文
        String json = JSONObject.toJSON(outerMap).toString();
        Object data = yueSoundUtils.getU9Data(json);
        Map u9Data = JSONObject.parseObject(data.toString(), Map.class);
        if(u9Data.get("isSuccess").equals(Boolean.TRUE)){
            return Response.success().setMsg("新增客户行业成功");
        }
        return Response.fail().setMsg(u9Data.get("message").toString());
    }

    //新增客户行业
    @Override
    public Response TradeCategoryCreate(Map<String, Object> param) {
        if(ObjectUtils.isEmpty(param)){return Response.fail().setMsg("查询bip客户行业为空");}
        Map nameMap=(Map)param.get("name");
        Map<String,Object> outerMap=new HashMap<>();
        outerMap.put("code",param.get("code"));//编码
        outerMap.put("name",nameMap.get("zh_CN").toString());//名称
        outerMap.put("description","");//描述
        outerMap.put("action","TradeCategory_Create");//操作名
        outerMap.put("projectName","DGHYS");//项目名
        outerMap.put("os_doc_no","");//其他系统单号
        outerMap.put("record_doc_no_field","");//记录其他系统单号的字段
        Map<String,Object> contextMap=new HashMap<>();
        contextMap.put("enterpriseId",ConvertConstant.U9_ENTERPRISEID);
        contextMap.put("orgId",ConvertConstant.U9_ORG_ID);//组织
        contextMap.put("userId",ConvertConstant.U9_USER_ID);//用户
        contextMap.put("userName","bip");//用户
        contextMap.put("operateDateTime",DateUtil.now());//日期
        outerMap.put("context",contextMap);//上下文
        String json = JSONObject.toJSON(outerMap).toString();
        Object data = yueSoundUtils.getU9Data(json);
        Map u9Data = JSONObject.parseObject(data.toString(), Map.class);
        if(u9Data.get("isSuccess").equals(Boolean.TRUE)){
            return Response.success().setMsg("新增客户行业成功");
        }
        return Response.fail().setMsg(u9Data.get("message").toString());
    }

    //新增料品
    @Override
    public Response ItemMasterAddCreate(Map<String, Object> param) {
        if(ObjectUtils.isEmpty(param)){return Response.fail().setMsg("查询bip物料信息为空");}
        List<YueSoundUnit> yueSoundUnitLists=null;
        Map dataMap = (Map)param.get("data");
        List<Map> recordList = JSONObject.parseObject(dataMap.get("recordList").toString(), List.class);
        Map dataList = recordList.get(0);
        String uomCode="";
        log.info("开始获取缓存中的计量单位集合");
        Object yueSoundUnitList = redisTemplate.opsForValue().get("YueSoundUnitList");
        if(ObjectUtils.isEmpty(yueSoundUnitList)){
            uomCode="F040";
        }else {
            yueSoundUnitLists = JSONArray.parseArray(yueSoundUnitList.toString(), YueSoundUnit.class);
            List<YueSoundUnit> units= yueSoundUnitLists.stream().filter(item -> item.getId().toString().equals(dataList.get("unit").toString())).collect(Collectors.toList());
            if(CollectionUtils.isEmpty(units)){
                uomCode="F040";
            }else{
                uomCode=units.get(0).getCode();
                Map<String, String> unitMap = UnitTranUtil.getUnitMap();
                if(ObjectUtils.isEmpty(unitMap.get(uomCode))){
                    uomCode="F040";
                }
            }
        }
        Map<String,Object> outerMap=new HashMap<>();
        outerMap.put("code",dataList.get("code"));//编码
        outerMap.put("name",dataList.get("name"));//名称
        outerMap.put("type_code","01");//体系编码 (bip物料分类没有分类体系编码设置为默认:财务分类)
        outerMap.put("uom_code",uomCode);//计量单位编码(bip物料分类没有计量单位编码设置为默认:个(财务))
        outerMap.put("description","");//描述
        outerMap.put("maincate_code","");//主分类
        outerMap.put("pricecate_code","");//价格分类
        outerMap.put("salecate_code","");//销售分类
        outerMap.put("assetcate_code","");//财务分类
        outerMap.put("purchasecate_code ","");//采购分类
        outerMap.put("specs ","");//规格
        outerMap.put("item_form ",0);//料品形态
        outerMap.put("action","ItemCategory_Create");//操作名
        outerMap.put("projectName","DGHYS");//项目名
        outerMap.put("os_doc_no","");//其他系统单号
        outerMap.put("record_doc_no_field","");//记录其他系统单号的字段
        Map<String,Object> contextMap=new HashMap<>();
        contextMap.put("enterpriseId",ConvertConstant.U9_ENTERPRISEID);
        contextMap.put("orgId",ConvertConstant.U9_ORG_ID);//组织
        contextMap.put("userId",ConvertConstant.U9_USER_ID);//用户
        contextMap.put("userName","bip");//用户
        contextMap.put("operateDateTime",DateUtil.now());//日期
        outerMap.put("context",contextMap);//上下文
        String json = JSONObject.toJSON(outerMap).toString();
        Object data = yueSoundUtils.getU9Data(json);
        Map u9Data = JSONObject.parseObject(data.toString(), Map.class);
        if(u9Data.get("isSuccess").equals(Boolean.TRUE)){
            return Response.success().setMsg("新增料品成功");
        }
        return Response.fail().setMsg(u9Data.get("message").toString());
    }

    //新增业务员
    @Override
    public Response OperatorCreateSyncer(Map<String, Object> param) {
        if(ObjectUtils.isEmpty(param)){return  Response.fail().setMsg("查询bip员工数据为空");}
        List<String> typeList = Arrays.asList("0", "1", "2", "3","4");
        Map<String,Object> outerMap=new HashMap<>();
        outerMap.put("code", param.get("code"));//编码
        outerMap.put("name",param.get("name"));//名称
        outerMap.put("dept_code",param.get("deptCode"));//部门编码
        outerMap.put("description","");//描述
        outerMap.put("typeList",typeList);//业务员类型分类(默认授予全部类型:采购业务员，销售业务员，库管员，捡货员，理货员)
        outerMap.put("action","Operator_Create");//操作名
        outerMap.put("projectName","DGHYS");//项目名
        outerMap.put("os_doc_no","");//其他系统单号
        outerMap.put("record_doc_no_field","");//记录其他系统单号的字段
        Map<String,Object> contextMap=new HashMap<>();
        contextMap.put("enterpriseId",ConvertConstant.U9_ENTERPRISEID);
        contextMap.put("orgId","1001505124451956");//组织
        contextMap.put("userId","1001505124454187");//用户
        contextMap.put("userName","bip");//用户
        contextMap.put("operateDateTime",DateUtil.now());//日期
        outerMap.put("context",contextMap);//上下文
        String json = JSONObject.toJSON(outerMap).toString();
        Object data = yueSoundUtils.getU9Data(json);
        Map u9Data = JSONObject.parseObject(data.toString(), Map.class);
        if(u9Data.get("isSuccess").equals(Boolean.TRUE)){
            return Response.success().setMsg("新增业务员成功");
        }
        return Response.fail().setMsg(u9Data.get("message").toString());
    }

    //新增客户档案
    @Override
    public Response CustomerCreateSyncer(Map<String, Object> param) {
        if(ObjectUtils.isEmpty(param)) return Response.fail().setMsg("bip客户档案查询为空");
        String stateTaxNo="";
        String buyerNoteName="";
        String buyerAccount="";
        String buyerAccountCode="";
        String checkPhone="";
        String checkMail="";
        List<Map> contactList=new ArrayList<>();
        Map personMap=new HashMap();
        Map addressMap=new HashMap();
        personMap.put("CRUD",4);
        addressMap.put("CRUD",4);
        // 地址信息
        Object merchantAddressInfos = param.get("merchantAddressInfos");
        if(!ObjectUtils.isEmpty(merchantAddressInfos)){
            List<Map>merchantAddressInfoList = JSONObject.parseObject(merchantAddressInfos.toString(), List.class);
            Map map = merchantAddressInfoList.get(0);
            if(!ObjectUtils.isEmpty(map.get("addressCode"))){
                addressMap.put("code",map.get("addressCode"));
            }
            if(!ObjectUtils.isEmpty(map.get("address"))){
                addressMap.put("address1",map.get("address"));
            }
        }
        // 联系人信息
        Object merchantContacterInfos = param.get("merchantContacterInfos");
        if(!ObjectUtils.isEmpty(merchantContacterInfos)){
            List<Map> merchantContacterInfoList = JSONObject.parseObject(merchantContacterInfos.toString(), List.class);
            Map map = merchantContacterInfoList.get(0);
            if(!ObjectUtils.isEmpty(map.get("id"))){
                personMap.put("code",map.get("id"));
            }
            if(!ObjectUtils.isEmpty(map.get("fullName"))){
                Map nameMap = (Map)map.get("fullName");
                personMap.put("code",nameMap.get("zh_CN"));
            }
            personMap.put("is_default",Boolean.FALSE);
        }
        // 发票信息
        Object merchantAgentInvoiceInfos = param.get("merchantAgentInvoiceInfos");
        if(!ObjectUtils.isEmpty(merchantAgentInvoiceInfos)){
            List<Map> merchantAgentInvoiceInfoList = JSONArray.parseObject(merchantAgentInvoiceInfos.toString(), List.class);
            Map map = merchantAgentInvoiceInfoList.get(0);
            if(!ObjectUtils.isEmpty(map.get("taxNo"))){
                stateTaxNo=map.get("taxNo").toString();
            }
            if(!ObjectUtils.isEmpty(map.get("title"))){
                buyerNoteName=map.get("title").toString();
            }
            if(!ObjectUtils.isEmpty(map.get("bankName_Name"))){
                buyerAccount=map.get("bankName_Name").toString();
            }
            if(!ObjectUtils.isEmpty(map.get("receievInvoiceMobile"))){
                checkPhone=map.get("receievInvoiceMobile").toString();
            }
            if(!ObjectUtils.isEmpty(map.get("receievInvoiceEmail"))){
                checkMail=map.get("receievInvoiceEmail").toString();
            }
            if(!ObjectUtils.isEmpty(map.get("bankAccount"))){
                buyerAccountCode=map.get("bankAccount").toString();
            }

        }
        personMap.put("address",addressMap);
        contactList.add(personMap);
        Map nameMap=(Map)param.get("name");
        String code = (String)param.get("code");
        Map<String,Object> outerMap=new HashMap<>();
        outerMap.put("CRUD",0);//操作 0-新增 1-修改 2-删除
        outerMap.put("code",code);//编码
        outerMap.put("name",nameMap.get("zh_CN"));//名称
        outerMap.put("description","");//描述
        outerMap.put("short_name","");//简称
        outerMap.put("category_code",param.get("customerClass_code"));//客户分类编码
        outerMap.put("district_code","");//地区编码
        outerMap.put("trade_category",param.get("customerIndustry_code"));//行业编码
        outerMap.put("contact_code","");//联系对象
        outerMap.put("operator_code","");//业务员编码
        outerMap.put("dept_code","");//部门编码
        outerMap.put("tc_code",ConvertConstant.U9_CNY);//币种编码 设置默认值 人民币
        outerMap.put("shiprule_code","");//出货原则编码
        outerMap.put("is_shiprule_editable",Boolean.TRUE);//出货原则是否可改
        outerMap.put("taxschedule_code","");//税组合编码
        outerMap.put("rc_code",ConvertConstant.U9_CNY);//收款币种编码 设置默认值 人民币
        outerMap.put("recterm_code",ConvertConstant.U9_COLLECTION_CODE);//收款条件编码
        outerMap.put("arterm_code","");//立账条件编码
        outerMap.put("apply_seq",0);//核销顺序
        outerMap.put("apply_by",0);//核销明细
        outerMap.put("state_taxno",stateTaxNo);//纳税人识别号
        outerMap.put("buyer_notename",buyerNoteName);//购买方开票名称
        outerMap.put("buyer_account",buyerAccount);//购买方开票行
        outerMap.put("buyer_accountcode",buyerAccountCode);//购买方账号
        outerMap.put("check_phone",checkPhone);//收票人手机
        outerMap.put("check_mail",checkMail);//收票人邮件
        outerMap.put("district_taxno","");//地税号
        /*
        *
        * [{"CRUD": 0, // 默认新增 0新增 1修改 2删除 其他不做任何处理
        * "code": "string", // 联系人编码 当联系人有数据时必须录入
        * "name": "string", // 联系人名称 当联系人有数据时必须录入
        * "is_default": false, //是否默认
        * "address":{"CRUD": 0, //默认新增 0新增 1修改 2删除 其他不做任何处理
        *           "code": "string", // 地址编码  必须录入
         *           "address1": "string", //地址1
        *           "address2": "string",  //地址2
        *           "address3": "string"   //地址3
        *           }
        *  }]
        * */
        outerMap.put("contactList",contactList);//联系人集合
        outerMap.put("action","Customer_Sync");//操作名
        outerMap.put("projectName","DGHYS");//项目名
        outerMap.put("os_doc_no","");//其他系统单号
        outerMap.put("record_doc_no_field","");//记录其他系统单号的字段
        Map<String,Object> contextMap=new HashMap<>();
        contextMap.put("enterpriseId",ConvertConstant.U9_ENTERPRISEID);
        contextMap.put("orgId",ConvertConstant.U9_ORG_ID);//组织
        contextMap.put("userId",ConvertConstant.U9_USER_ID);//用户
        contextMap.put("userName","bip");//用户
        contextMap.put("operateDateTime",DateUtil.now());//日期
        outerMap.put("context",contextMap);//上下文
        String json = JSONObject.toJSON(outerMap).toString();
        Object u9Data = yueSoundUtils.getU9Data(json);
        Map map = JSONObject.parseObject(u9Data.toString(), Map.class);
        if(map.get("isSuccess").equals(Boolean.TRUE)){
            return Response.success().setMsg("请求U9接口新增客户档案成功");
        }
        return Response.fail().setMsg("请求U9新增客户档案接口失败:"+map.get("message"));
    }

    // 新增客户分类
    @Override
    public Response CustCategoryCreateSyncer(Map<String, Object> param) {
        if(ObjectUtils.isEmpty(param)) return Response.fail().setMsg("bip客户分类查询为空");
        Map nameMap=(Map)param.get("name");
        Map<String,Object> outerMap=new HashMap<>();
        outerMap.put("code",param.get("code"));//编码
        outerMap.put("name",nameMap.get("zh_CN").toString());//名称
        outerMap.put("description","");//描述
        outerMap.put("action","CustCategory_Create");//操作名
        outerMap.put("projectName","DGHYS");//项目名
        outerMap.put("os_doc_no","");//其他系统单号
        outerMap.put("record_doc_no_field","");//记录其他系统单号的字段
        Map<String,Object> contextMap=new HashMap<>();
        contextMap.put("enterpriseId",ConvertConstant.U9_ENTERPRISEID);
        contextMap.put("orgId",ConvertConstant.U9_ORG_ID);//组织
        contextMap.put("userId",ConvertConstant.U9_USER_ID);//用户
        contextMap.put("userName","bip");//用户
        contextMap.put("operateDateTime",DateUtil.now());//日期
        outerMap.put("context",contextMap);//上下文
        String json = JSONObject.toJSON(outerMap).toString();
        Object u9Data = yueSoundUtils.getU9Data(json);
        Map map = JSONObject.parseObject(u9Data.toString(), Map.class);
        if(map.get("isSuccess").equals(Boolean.TRUE)){
            return Response.success().setMsg("请求U9接口新增客户分类成功");
        }
        return Response.fail().setMsg("请求U9新增客户分类接口失败:"+map.get("message"));
    }

    @Override
    public Response CreateItemCategory(Map<String, Object> paramMap) {
        if(ObjectUtils.isEmpty(paramMap)){
            return Response.fail();
        }
        Map<String,String> nameMap=(Map<String, String>)paramMap.get("name");
        Map<String,Object> outerMap=new HashMap<>();
        outerMap.put("code",paramMap.get("code"));//编码
        outerMap.put("name",nameMap.get("zh_CN"));//名称
        outerMap.put("type_code","01");//体系编码 (bip物料分类没有分类体系编码设置为默认:财务分类)
        outerMap.put("uom_code","F040");//计量单位编码(bip物料分类没有计量单位编码设置为默认:个(财务))
        outerMap.put("description","");//描述
        outerMap.put("action","ItemCategory_Create");//操作名
        outerMap.put("projectName","DGHYS");//项目名
        outerMap.put("os_doc_no","");//其他系统单号
        outerMap.put("record_doc_no_field","");//记录其他系统单号的字段
        Map<String,Object> contextMap=new HashMap<>();
        contextMap.put("enterpriseId",ConvertConstant.U9_ENTERPRISEID);
        contextMap.put("orgId","1001505124451956");//组织
        contextMap.put("userId","1001505124454187");//用户
        contextMap.put("userName","bip");//用户
        contextMap.put("operateDateTime",DateUtil.now());//日期
        outerMap.put("context",contextMap);//上下文
        String json = JSONObject.toJSON(outerMap).toString();
        Map u9Data = JSONObject.parseObject(yueSoundUtils.getU9Data(json).toString(), Map.class);
        if(u9Data.get("isSuccess").equals(Boolean.TRUE)){
           return Response.success().setMsg("新增料品分类成功");
        }
        return Response.fail().setMsg(u9Data.get("message").toString());
    }
}
