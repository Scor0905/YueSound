package com.local.dao;

import com.local.entity.YueSound.YueSoundOrderChangeDefine;
import com.local.entity.YueSound.YueSoundOrderDefine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-29 14:36
 */
@Repository
public interface OrderChangeDefineDao {

    List<YueSoundOrderChangeDefine> select(YueSoundOrderChangeDefine yueSoundOrderChangeDefine);

    void insert(YueSoundOrderChangeDefine yueSoundOrderChangeDefine);

    void updateById(YueSoundOrderChangeDefine yueSoundOrderChangeDefine);
}
