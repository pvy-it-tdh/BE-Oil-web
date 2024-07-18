package com.example.demo.Response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ApiResponse <T> {
    private boolean success;
    private String message;
    private T data;

    public ApiResponse( boolean success,String message, T data) {
        this.message = message;
        this.success = success;
        this.data = data;
    }
}
