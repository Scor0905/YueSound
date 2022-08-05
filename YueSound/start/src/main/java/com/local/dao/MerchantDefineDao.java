package com.local.dao;

import com.local.entity.YueSound.YueSoundMerchantDefine;
import com.local.entity.YueSound.YueSoundOrderChangeDefine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-29 14:36
 */
@Repository
public interface MerchantDefineDao {

    List<YueSoundMerchantDefine> selectById(String id);

    void updateById(YueSoundMerchantDefine yueSoundMerchantDefine);

    void insert(YueSoundMerchantDefine yueSoundMerchantDefine);
}
