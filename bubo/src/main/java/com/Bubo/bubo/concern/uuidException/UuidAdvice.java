package com.Bubo.bubo.concern.uuidException;

import com.Bubo.bubo.concern.requestBodyException.RequestBodyException;
import com.Bubo.bubo.concern.requestBodyException.RequestBodyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class UuidAdvice {
    @ResponseBody
    @ExceptionHandler(UuidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public UuidResponse handler(UuidException ue) {
        UuidResponse response = new UuidResponse(ue.getMessage());
        return response;
    }
}
