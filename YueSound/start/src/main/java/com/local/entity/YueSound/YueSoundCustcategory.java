package com.local.entity.YueSound;

import lombok.Data;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-05 10:37
 * 客户分类实体
 */
@Data
public class YueSoundCustcategory {
    private String pubts; //时间戳,格式为:yyyy-MM-dd HH:mm:ss
    private String custCategoryApplyRanges_isEnd; //是否末级，true：是，false：否
    private String custCategoryApplyRanges_orgId    ; //使用组织id
    private String orgId; //管理组织id
    private String custCategoryApplyRanges_isApplied; //是否被分配者使用
    private String orgId_name; //管理组织名称
    private String custCategoryApplyRanges_id; //物料分类适用范围id
    private String custCategoryApplyRanges_clsId; //客户分类id
    private String custCategoryApplyRanges_isCreator; //是否创建者，true：是，false：否
    private String custCategoryApplyRanges_orgId_name; //使用组织名称
    private String code; //编码
    private Boolean isEnd; //是否末级 ture:是 false:否
    private Integer order; //排序
    private Long level; //层级
    private Boolean isEnabled; //	启用状态, true:启用、false:停用
    private String creator; //创建者
    private String path; //	路径
    private String name; //客户分类名称
    private Long id; //	客户分类主键
    private String createTime; //创建时间
    private String pcode;//上级编码
    private List<YueSoundCustcategory> children;
}
