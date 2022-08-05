package com.local.dao;

import com.local.entity.YueSound.YueSoundAgentCategoryDefine;
import com.local.entity.YueSound.YueSoundDeliveryVoucherFreeItem;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-29 11:23
 */
@Repository
public interface DeliveryVoucherFreeItemDao {

    List<YueSoundDeliveryVoucherFreeItem> selectById(String id);

    void insert(YueSoundDeliveryVoucherFreeItem yueSoundDeliveryVoucherFreeItem);

    void updateById(YueSoundDeliveryVoucherFreeItem yueSoundDeliveryVoucherFreeItem);
}
