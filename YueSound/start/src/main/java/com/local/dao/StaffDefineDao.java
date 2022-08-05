package com.local.dao;

import com.local.entity.YueSound.YueSoundStaffDefine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-29 14:36
 */
@Repository
public interface StaffDefineDao {

    List<YueSoundStaffDefine> selectById(String id);

    void insert(YueSoundStaffDefine yueSoundStaffDefine);

    void updateById(YueSoundStaffDefine yueSoundStaffDefine);
}
