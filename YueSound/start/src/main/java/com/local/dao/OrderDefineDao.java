package com.local.dao;

import com.local.entity.YueSound.YueSoundOrderDefine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-29 14:36
 */
@Repository
public interface OrderDefineDao {

    List<YueSoundOrderDefine> select(YueSoundOrderDefine yueSoundOrderDefine);

    void insert(YueSoundOrderDefine yueSoundOrderDefine);

    void updateById(YueSoundOrderDefine yueSoundOrderDefine);
}
