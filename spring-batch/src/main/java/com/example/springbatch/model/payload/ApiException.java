package com.example.springbatch.model.payload;

import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.ZonedDateTime;

@Data
public class ApiException {

    private final int errorCode;
    private final HttpStatus httpStatus;
    private final String message;
    private final ZonedDateTime dateTime;

    public ApiException(Integer errorCode,
                        HttpStatus httpStatus,
                        String message,
                        ZonedDateTime dateTime) {
        this.errorCode = errorCode;
        this.httpStatus = httpStatus;
        this.message = message;
        this.dateTime = dateTime;
    }
}
