package com.local.dao;

import com.local.entity.YueSound.YueSoundProductDefine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-29 11:23
 */
@Repository
public interface ProductDefineDao {

    List<YueSoundProductDefine> select(YueSoundProductDefine yueSoundProductDefine);

    void insert(YueSoundProductDefine yueSoundProductDefine);

    void updateById(YueSoundProductDefine yueSoundProductDefine);
}
