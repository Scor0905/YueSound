package com.local.dao;

import com.local.entity.YueSound.YueSoundManagementClassDefine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-29 10:40
 */
@Repository
public interface ManagementClassDefineDao {

   List<YueSoundManagementClassDefine> selectById (String id);

   void updateById(YueSoundManagementClassDefine managementClassDefine);

    void insert(YueSoundManagementClassDefine managementClassDefine);
}
