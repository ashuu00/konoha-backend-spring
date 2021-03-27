package com.konoha.konoha_server.Exception;

import org.springframework.stereotype.Component;

@Component
public class ApiException extends RuntimeException{
    private String message;
    public ApiException() {
        this.message="Category Already Exists";
    }

    @Override
    public String getMessage() {
        return message;
    }
}
