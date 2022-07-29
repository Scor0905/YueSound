package com.local.util;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * @author hhs
 * @create 2022-07-01 11:20
 */
public class Response implements Serializable {
    private static final long serialVersionUID = 47067542629985123L;
    public static final String SUCCESS_CODE = "200";
    public static final String LICENSE_VALIDATE_FAIL_CODE = "201";
    public static final String BATCH_PARTIAL_SUCCESS_CODE = "206";
    public static final String BATCH_WHOLE_FAIL_CODE = "207";
    public static final String BUSINESS_FAIL_CODE = "400";
    public static final String FAIL_CODE = "500";
    public static final String LICENSE_FAIL_CODE = "501";
    public static final String STATUS_BIZ_NEED_CONFIRM = "409";
    public static final String STATUS_BIZ_NEED_LOGIC_CHOICE = "411";
    private String code;
    private String msg;
    private Object data;
    private Long spendTime;

    public Response() {
    }

    public String getCode() {
        return this.code;
    }

    public Response setCode(String code) {
        this.code = code;
        return this;
    }

    public String getMsg() {
        return this.msg;
    }

    public Response setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return this.data;
    }

    public Response setData(Object data) {
        this.data = data;
        return this;
    }

    public Long getSpendTime() {
        return this.spendTime;
    }

    public Response setSpendTime(Long time) {
        this.spendTime = time;
        return this;
    }

    @JsonProperty(
            access = JsonProperty.Access.WRITE_ONLY
    )
    public boolean isSuccess() {
        return this.code.startsWith("2");
    }

    public static Response success() {
        return new Response.SuccessResponse();
    }

    public static Response fail() {
        return new Response.FailResponse();
    }

    public static Response businessFailResponse() {
        return new Response.BusinessFailResponse();
    }

    public static Response businessFailResponse(String msg) {
        return new Response.BusinessFailResponse(msg);
    }

    public static Response businessFailResponse(String errCode, String errMsg) {
        return new Response.BusinessFailResponse(errCode, errMsg);
    }

    public static Response commonResponse(StatusEnum statusEnum) {
        return new Response.CommonResponse(statusEnum);
    }

    public static Response commonResponse(StatusEnum statusEnum, String msg) {
        return new Response.CommonResponse(statusEnum, msg);
    }

    public static Response commonResponse(String code, String msg) {
        return new Response.CommonResponse(code, msg);
    }

    public static Response commonResponse(String msg) {
        return new Response.CommonResponse(msg);
    }

    public static Response commonResponse(boolean status) {
        return commonResponse(status, (String)null, (Object)null);
    }

    public static Response commonResponse(boolean status, String msg) {
        return commonResponse(status, msg, (Object)null);
    }

    public static Response commonResponse(boolean status, String msg, Object data) {
        Response res = success();
        if (!status) {
            res = fail();
        }

        if (StringUtil.isNotEmpty(msg)) {
            res.setMsg(msg);
        }

        if (null != data) {
            res.setData(data);
        }

        return res;
    }

    public static class CommonResponse extends Response {
        private static final long serialVersionUID = -687461638087689607L;

        public CommonResponse(StatusEnum statusEnum) {
            this.setCode(statusEnum.getCode());
            this.setMsg(statusEnum.getMsg());
            this.setData("");
        }

        public CommonResponse(String code, String msg) {
            this.setCode(code);
            this.setMsg(msg);
            this.setData("");
        }

        public CommonResponse(String msg) {
            this.setCode("200");
            this.setMsg(msg);
            this.setData("");
        }

        public CommonResponse(StatusEnum statusEnum, String msg) {
            this.setCode(statusEnum.getCode());
            this.setMsg(statusEnum.getMsg() + ":" + msg);
            this.setData("");
        }
    }

    public static class BusinessFailResponse extends Response {
        private static final long serialVersionUID = -5238070512611403402L;

        public BusinessFailResponse() {
            this.setCode("400");
            this.setMsg("业务处理失败");
        }

        public BusinessFailResponse(String code, String msg) {
            this.setCode(code);
            this.setMsg(msg);
        }

        public BusinessFailResponse(String errMsg) {
            this.setCode("400");
            this.setMsg(errMsg);
        }
    }

    public static class SuccessResponse extends Response {
        private static final long serialVersionUID = -2395996558104816956L;

        public SuccessResponse() {
            this.setCode("200");
            this.setMsg("成功");
        }
    }

    public static class FailResponse extends Response {
        private static final long serialVersionUID = -2485130096025044970L;

        public FailResponse() {
            this.setCode("500");
            this.setMsg("fail");
        }
    }
}
