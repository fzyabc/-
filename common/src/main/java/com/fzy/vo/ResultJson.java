package com.fzy.vo;


import com.fzy.core.ResultCode;
import lombok.Getter;

@Getter
public class ResultJson<T> {

    private Integer code; //200 500 401 403
    private T content;
    private String message;

    private ResultJson(Integer code, T content, String message) {
        this.code = code;
        this.content = content;
        this.message = message;
    }

    public static <T> ResultJson<T> getInstance(ResultCode resultCode, T content, String message) {
        return new ResultJson<>(resultCode.getCode(), content, message);
    }

    public static <T> ResultJson<T> success(T content, String message) {
        return getInstance(ResultCode.SUCCESS, content, message);
    }
    public static <T> ResultJson<T> success(T content) {

        return success(content, null);
    }

    public static <T> ResultJson<T> failed(T content, String message) {
        return getInstance(ResultCode.FAILED, content, message);
    }

    public static <T> ResultJson<T> failed(String message) {

        return failed(null, message);
    }

}
