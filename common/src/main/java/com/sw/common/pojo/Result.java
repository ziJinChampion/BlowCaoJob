package com.sw.common.pojo;


public class Result<T> {

    private Boolean flag;
    private Integer code;
    private String message;
    private T data;

    public Result(Boolean flag, Integer code, String message, Object data) {
        this.flag = flag;
        this.code = code;
        this.message = message;
        this.data = (T) data;
    }

    public Result(Boolean flag, Integer code, String message) {
        this.flag = flag;
        this.code = code;
        this.message = message;
    }

    public Result(){
        this.flag = true;
        this.code = StatusCode.OK;
        this.message = "success";
    }
}
