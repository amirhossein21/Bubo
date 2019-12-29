package com.Bubo.bubo.concern.requestBodyException;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class RequestBodyAdvice {

    @ResponseBody
    @ExceptionHandler(RequestBodyException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public RequestBodyResponse handler(RequestBodyException rbe) {
        RequestBodyResponse response = new RequestBodyResponse(rbe.getMessage());
        return response;
    }
}
