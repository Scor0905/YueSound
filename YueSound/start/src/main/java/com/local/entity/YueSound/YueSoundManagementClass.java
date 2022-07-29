package com.local.entity.YueSound;

import lombok.Data;

import java.util.List;

/**
 * @author hhs
 * @create 2022-07-04 9:43
 * 悦声物料实体
 */
@Data
public class YueSoundManagementClass {
    private String name; //物料分类名称
    private String code; //物料分类编码
    private String pCode; //上级分类编码
    private String erpCode; //外部编码
    private Integer productCount; //商品数量
    private Long parent; //上级分类id
    private String parent_name; //上级物料分类名称
    private Integer level; //层级
    private String path; //路径
    private String productClass; //商品分类id
    private String order; //排序
    private String productClassName; //商品分类名称
    private String template; //物料模板id
    private String templateName; //物料模板名称
    private Boolean isEnd; //是否末级
    private Long id; //物料分类id
    private String pubts; //时间戳
    private String stopstatus; //是否启用
    private String fullPath; //上下文路径
    private List<YueSoundManagementClass> children;
}
