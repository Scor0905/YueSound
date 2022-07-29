package com.local.util;

import lombok.Data;

/**
 * @author hhs
 * @create 2022-07-05 13:44
 * 悦声入参通用参数
 */
@Data
public class YueSoundParam {
    private String id;//主键
    private String custCategoryApplyRangesId;//客户分类使用范围id
    private String orgId; //组织id
    private String code;
    private String merchantApplyRangeId;//客户分配id
}
