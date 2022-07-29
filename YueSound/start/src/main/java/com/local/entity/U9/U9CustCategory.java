package com.local.entity.U9;

import lombok.Data;

import java.util.Map;

/**
 * @author hhs
 * @create 2022-07-05 14:53
 * U9客户分类请求参数
 */
@Data
public class U9CustCategory {
    private String code;
    private String name;
    private String description;//描述
    private String action;//操作名
    private String projectName;//项目名
    private String os_doc_no;//其他系统单号
    private String record_doc_no_field;//其他系统单号
    private Map<String,String> context;//上下文
}
