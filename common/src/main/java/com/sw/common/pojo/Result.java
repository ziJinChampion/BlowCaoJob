package com.sw.common.pojo;

public class Result<T> {

    private Boolean flag;
    private Integer code;
    private String message;
    private T data;

    public Result(Boolean flag, Integer code, String message, T data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = data;
    }
}
