package com.example.springbatch.exception;

public class ApiRequestException extends RuntimeException{

    public ApiRequestException(String message){
        super(message);
    }
}
