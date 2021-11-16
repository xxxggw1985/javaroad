package com.javaroad.transaction.common;

import java.io.Serializable;

/**
 * 接口返回数据格式
 *
 * @author Ltri
 * @date 2020年7月22日
 */

public class Results<T> implements Serializable {

    private static final long serialVersionUID = 1L;


    /**
     * 返回处理消息
     */
    private String msg = "操作成功！";

    /**
     * 返回代码
     */
    private Integer code = 2000;

    /**
     * 返回数据对象 data
     */
    private T data;

    public static <T> Results<T> success() {
        Results<T> r = new Results<T>();
        r.setCode(RetCode.SUCCESS.code);
        r.setMsg("SUCCESS");
        return r;
    }

    public static <T> Results<T> success(T data) {
        Results<T> r = new Results<T>();
        r.setCode(RetCode.SUCCESS.code);
        r.setData(data);
        r.setMsg("SUCCESS");
        return r;
    }

    public static <T> Results<T> success(T data, String msg, int code) {
        Results<T> r = new Results<T>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static Results<String> success(String msg) {
        Results<String> r = new Results<>();
        r.setData(msg);
        r.setCode(RetCode.SUCCESS.code);
        r.setMsg(msg);
        return r;
    }

    public static Results<Object> error(String msg) {
        return error(RetCode.Fail.code, msg);
    }

    public static <T> Results<T> error(T data, String msg) {
        Results<T> r = new Results<T>();
        r.setCode(RetCode.SUCCESS.code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static <T> Results<T> error(T data, String msg, int code) {
        Results<T> r = new Results<T>();
        r.setCode(code);
        r.setData(data);
        r.setMsg(msg);
        return r;
    }

    public static Results<Object> error(int code, String msg) {
        Results<Object> r = new Results<>();
        r.setCode(code);
        r.setMsg(msg);
        return r;
    }

    public static Results of(Object o, int code, String msg) {
        Results<Object> r = new Results<>();
        r.setCode(code);
        r.setMsg(msg);
        r.setData(o);
        return r;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}