package com.local.dao;

import com.local.entity.YueSound.YueSoundAgentCategoryDefine;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-29 11:23
 */
@Repository
public interface AgentCategoryDefineDao {

    List<YueSoundAgentCategoryDefine> selectById(String id);

    void insert(YueSoundAgentCategoryDefine yueSoundAgentCategoryDefine);

    void updateById(YueSoundAgentCategoryDefine yueSoundAgentCategoryDefine);

}
