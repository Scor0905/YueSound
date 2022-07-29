package entity;

import java.util.Date;
import java.io.Serializable;

/**
 * (AaCustomertrade)实体类
 *
 * @author makejava
 * @since 2022-07-29 14:47:11
 */
public class AaCustomertrade implements Serializable {
    private static final long serialVersionUID = -84538872868216442L;

    private String ccode;

    private String cname;
    /**
     * 创建者类型
     */
    private Integer icreatortype;
    /**
     * 创建者名称
     */
    private String ccreatorname;

    private String orgid;
    /**
     * 所属客商
     */
    private Long ishopid;
    /**
     * ERP编码
     */
    private String cerpcode;
    /**
     * 是否启用
     */
    private Integer isenabled;
    /**
     * 状态
     */
    private Integer ideleted;
    /**
     * 顺序
     */
    private Integer iorder;
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
    private Integer isend;
    /**
     * 停用状态
     */
    private Integer stopstatus;
    /**
     * 停用时间
     */
    private Date stopTime;
    /**
     * ID
     */
    private Long id;
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
    private Long creatorid;
    /**
     * 修改人
     */
    private Long modifierid;
    /**
     * 自定义客户行业名称
     */
    private String cname2;
    /**
     * 自定义客户行业名称
     */
    private String cname3;
    /**
     * 自定义客户行业名称
     */
    private String cname4;
    /**
     * 自定义客户行业名称
     */
    private String cname5;
    /**
     * 自定义客户行业名称
     */
    private String cname6;

    private String ytenantId;


    public String getCcode() {
        return ccode;
    }

    public void setCcode(String ccode) {
        this.ccode = ccode;
    }

    public String getCname() {
        return cname;
    }

    public void setCname(String cname) {
        this.cname = cname;
    }

    public Integer getIcreatortype() {
        return icreatortype;
    }

    public void setIcreatortype(Integer icreatortype) {
        this.icreatortype = icreatortype;
    }

    public String getCcreatorname() {
        return ccreatorname;
    }

    public void setCcreatorname(String ccreatorname) {
        this.ccreatorname = ccreatorname;
    }

    public String getOrgid() {
        return orgid;
    }

    public void setOrgid(String orgid) {
        this.orgid = orgid;
    }

    public Long getIshopid() {
        return ishopid;
    }

    public void setIshopid(Long ishopid) {
        this.ishopid = ishopid;
    }

    public String getCerpcode() {
        return cerpcode;
    }

    public void setCerpcode(String cerpcode) {
        this.cerpcode = cerpcode;
    }

    public Integer getIsenabled() {
        return isenabled;
    }

    public void setIsenabled(Integer isenabled) {
        this.isenabled = isenabled;
    }

    public Integer getIdeleted() {
        return ideleted;
    }

    public void setIdeleted(Integer ideleted) {
        this.ideleted = ideleted;
    }

    public Integer getIorder() {
        return iorder;
    }

    public void setIorder(Integer iorder) {
        this.iorder = iorder;
    }

    public Long getParentId() {
        return parentId;
    }

    public void setParentId(Long parentId) {
        this.parentId = parentId;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getIsend() {
        return isend;
    }

    public void setIsend(Integer isend) {
        this.isend = isend;
    }

    public Integer getStopstatus() {
        return stopstatus;
    }

    public void setStopstatus(Integer stopstatus) {
        this.stopstatus = stopstatus;
    }

    public Date getStopTime() {
        return stopTime;
    }

    public void setStopTime(Date stopTime) {
        this.stopTime = stopTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Date getPubts() {
        return pubts;
    }

    public void setPubts(Date pubts) {
        this.pubts = pubts;
    }

    public Long getTenantId() {
        return tenantId;
    }

    public void setTenantId(Long tenantId) {
        this.tenantId = tenantId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public Date getModifyDate() {
        return modifyDate;
    }

    public void setModifyDate(Date modifyDate) {
        this.modifyDate = modifyDate;
    }

    public String getCreator() {
        return creator;
    }

    public void setCreator(String creator) {
        this.creator = creator;
    }

    public String getModifier() {
        return modifier;
    }

    public void setModifier(String modifier) {
        this.modifier = modifier;
    }

    public Long getCreatorid() {
        return creatorid;
    }

    public void setCreatorid(Long creatorid) {
        this.creatorid = creatorid;
    }

    public Long getModifierid() {
        return modifierid;
    }

    public void setModifierid(Long modifierid) {
        this.modifierid = modifierid;
    }

    public String getCname2() {
        return cname2;
    }

    public void setCname2(String cname2) {
        this.cname2 = cname2;
    }

    public String getCname3() {
        return cname3;
    }

    public void setCname3(String cname3) {
        this.cname3 = cname3;
    }

    public String getCname4() {
        return cname4;
    }

    public void setCname4(String cname4) {
        this.cname4 = cname4;
    }

    public String getCname5() {
        return cname5;
    }

    public void setCname5(String cname5) {
        this.cname5 = cname5;
    }

    public String getCname6() {
        return cname6;
    }

    public void setCname6(String cname6) {
        this.cname6 = cname6;
    }

    public String getYtenantId() {
        return ytenantId;
    }

    public void setYtenantId(String ytenantId) {
        this.ytenantId = ytenantId;
    }

}
