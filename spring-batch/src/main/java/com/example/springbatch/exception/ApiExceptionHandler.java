package com.example.springbatch.exception;

import com.example.springbatch.model.payload.ApiException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZoneId;
import java.time.ZonedDateTime;

@ControllerAdvice
public class ApiExceptionHandler {

    /**
     *
     * @param ex
     * @return
     */
    @ExceptionHandler(value = {ApiRequestException.class})
    public ResponseEntity<Object> handlerApiRequestException(ApiRequestException ex){

        HttpStatus badRequest = HttpStatus.BAD_REQUEST;

        ApiException apiException = new ApiException(
                badRequest.value(),
                badRequest,
                ex.getMessage(),
                ZonedDateTime.now(ZoneId.of("GMT+7"))
        );

        return new ResponseEntity<>(apiException, badRequest);
    }

}
