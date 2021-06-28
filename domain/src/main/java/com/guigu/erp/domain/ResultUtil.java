package com.guigu.erp.domain;

import lombok.Data;

@Data
public class ResultUtil<T> {

    private Boolean result;
    private String message;
    private T data;

}
