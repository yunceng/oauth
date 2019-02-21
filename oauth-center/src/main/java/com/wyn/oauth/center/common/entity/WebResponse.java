package com.wyn.oauth.center.common.entity;

import java.io.Serializable;

public class WebResponse implements Serializable {
    private static final long serialVersionUID = 7514826298158585250L;
    private Integer code;
    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public static class Code{
        public static final int SUCCESS = 200;
        public static final int FAILED = 500;
    }

    public static class Msg{
        public static final String SUCCESS = "success";
        public static final String FAILED = "failed";
        public static final String LOGOUT_SUCCESS = "注销成功";
        public static final String LOGOUT_FAILED = "注销失败";
    }
}