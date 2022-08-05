package com.local.service.YueSound;

import com.local.util.Response;
import com.local.util.YueSoundParam;

/**
 * @author hhs
 * @create 2022-07-01 10:41
 * 悦声openApi
 */

public interface YueSoundOpenApi {

    Response generateToken();

    Response getManagementClassTree();

    Response  getUnitList();

    Response  getCustcategoryTree();

    Response getDeptDetail(String id);

    Response  saveOrUpdateProduct(YueSoundParam param);//*

    Response  saveOrUpdateManagementClassDetail(YueSoundParam param);//*

    Response  saveOrUpdateCustcategory(YueSoundParam param);//*

    Response  saveOrUpdateCustomerTrade(YueSoundParam param);//*

    Response  saveOrUpdateCustomer(YueSoundParam param);

    Response  saveOrUpdareStaffDetail(YueSoundParam param);

    Response saveMerchantDetail(YueSoundParam param);//*

    Response getSaleOrderDetail(YueSoundParam param);

    Response getReturnOrderDetail(YueSoundParam param);

    Response getSaleDeliveryDetail(YueSoundParam param);

    Response getWareHouseDetail(String id);

}
