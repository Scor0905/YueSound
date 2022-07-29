package com.local.entity.YueSound;

import lombok.Data;

/**
 * @author hhs
 * @create 2022-07-06 15:00
 * 计量单位实体(只用到部分字段并未全部定义)
 */
@Data
public class YueSoundUnit {
    private String code; // 编码
    private String name; // 名称
    private Boolean stopstatus; // 是否启用
    private Long id; // 主键
}
