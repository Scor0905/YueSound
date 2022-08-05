package com.local.dao;

import com.local.entity.YueSound.YueSoundSaleReturnDefine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-29 14:36
 */
@Repository
public interface SaleReturnDefineDao {

    List<YueSoundSaleReturnDefine> select(YueSoundSaleReturnDefine yueSoundSaleReturnDefine);

    void insert(YueSoundSaleReturnDefine yueSoundSaleReturnDefine);

    void updateById(YueSoundSaleReturnDefine yueSoundSaleReturnDefine);
}
