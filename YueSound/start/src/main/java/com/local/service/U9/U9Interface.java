package com.local.service.U9;

import com.local.util.Response;

import java.util.Map;

/**
 * @author hhs
 * @create 2022-07-04 11:29
 * U9对接接口
 */
public interface U9Interface {

    Response CreateItemCategory(Map<String,Object> param);

    Response CustCategoryCreateSyncer(Map<String,Object> param);

    Response CustomerCreateSyncer(Map<String,Object> param);

    Response OperatorCreateSyncer(Map<String,Object> param);

    Response ItemMasterAddCreate(Map<String, Object> param);

    Response TradeCategoryCreate(Map<String, Object> param);

    Response SOCreateSyncer(Map<String,Object> param);

    Response ShipCreateSyncer(Map<String,Object> param);

    Response SMRcvCreateSyncer(Map<String,Object> param);

    Response RMACreateSyncer(Map<String,Object> param);
}
