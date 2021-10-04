package com.example.springbatch.exception;

public class ApiRequestException extends RuntimeException{

    /**
     *
     * @param message
     */
    public ApiRequestException(String message){
        super(message);
    }
}
