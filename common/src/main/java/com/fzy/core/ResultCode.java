package com.fzy.core;

import lombok.Getter;

@Getter
public enum ResultCode {

    SUCCESS(200),
    FAILED(500),
    UNLOGIN(401),
    FORBID(403);

    private Integer code;

    ResultCode(Integer code){
        this.code = code;
    }


}

