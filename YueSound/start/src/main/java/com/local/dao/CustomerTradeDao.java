package com.local.dao;

import com.local.entity.YueSound.YueSoundCustomerTrade;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-29 14:58
 */
@Repository
public interface CustomerTradeDao {

    List<YueSoundCustomerTrade> selectById(String id);

    void insert(YueSoundCustomerTrade yueSoundCustomerTrade);

    void updateById(YueSoundCustomerTrade yueSoundCustomerTrade);
}
