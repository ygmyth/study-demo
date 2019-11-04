package com.myth.common;


public class Response<T> {
    private int code;
    private String msg;
    private T data;


    public static <T> Response<T> success() {

        return new Response<T>(0);
    }

    public static <T> Response<T> success(T data) {
        return new Response<T>(0, data);
    }

    public static <T> Response<T> success(String msg) {
        return new Response<>(0, msg);
    }

    public static <T> Response<T> error(int code, String msg) {
        return new Response<T>(code, msg);
    }

    public static <T> Response<T> error(int code, T data) {
        return new Response<T>(code, data);
    }


    private Response(int code) {
        this.code = code;
    }

    private Response(int code, String msg) {
        this.code =code;
        this.msg =msg;
    }

    private Response(int code, T data) {
        this.code = code;
        this.data = data;
    }

    private Response(int code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
