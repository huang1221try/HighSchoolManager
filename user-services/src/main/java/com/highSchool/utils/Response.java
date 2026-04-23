package com.highSchool.utils;

import lombok.Data;

@Data
public class Response<T> {

    String code;
    String message;
    T data;


    public static <T> Response<T> ok(String code, String message) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        return response;
    }

    public static <T> Response<T> ok(String code, String message, T data) {
        Response response = new Response();
        response.setCode(code);
        response.setMessage(message);
        response.setData(data);
        return response;
    }
}
