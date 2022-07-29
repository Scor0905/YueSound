package com.local.entity.YueSound;

import lombok.Data;

import java.util.Date;

/**
 * @author hhs
 * @create 2022-07-29 14:48
 * 客户行业
 */
@Data
public class YueSoundCustomerTrade {

    /**
     * ID
     */
    private Long id;

    private String cCode;

    private String cName;
    /**
     * 创建者类型
     */
    private Integer iCreatorType;
    /**
     * 创建者名称
     */
    private String cCreatorName;

    private String orgId;
    /**
     * 所属客商
     */
    private Long iShopId;
    /**
     * ERP编码
     */
    private String cErpCode;
    /**
     * 是否启用
     */
    private Integer isEnabled;
    /**
     * 状态
     */
    private Integer iDeleted;
    /**
     * 顺序
     */
    private Integer iOrder;
    /**
     * 上级分类
     */
    private Long parentId;
    /**
     * 层级
     */
    private Integer level;
    /**
     * 路径
     */
    private String path;
    /**
     * 排序号
     */
    private Integer sortNum;
    /**
     * 是否末级
     */
    private Integer iSend;
    /**
     * 停用状态
     */
    private Integer stopStatus;
    /**
     * 停用时间
     */
    private Date stopTime;

    /**
     * 时间戳
     */
    private Date pubts;
    /**
     * 租户
     */
    private Long tenantId;
    /**
     * 创建时间
     */
    private Date createTime;
    /**
     * 创建日期
     */
    private Date createDate;
    /**
     * 修改时间
     */
    private Date modifyTime;
    /**
     * 修改日期
     */
    private Date modifyDate;
    /**
     * 创建人
     */
    private String creator;
    /**
     * 修改人
     */
    private String modifier;
    /**
     * 创建人
     */
    private Long creatorId;
    /**
     * 修改人
     */
    private Long modifierId;
    /**
     * 自定义客户行业名称
     */
    private String cName2;
    /**
     * 自定义客户行业名称
     */
    private String cName3;
    /**
     * 自定义客户行业名称
     */
    private String cName4;
    /**
     * 自定义客户行业名称
     */
    private String cName5;
    /**
     * 自定义客户行业名称
     */
    private String cName6;

    private String yTenantId;
}
