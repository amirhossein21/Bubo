package com.Bubo.bubo.concern.requestBodyException;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class RequestBodyResponse {
    String message;
    Date date;


    public RequestBodyResponse(String m){
        this.message=m;
        long millis=System.currentTimeMillis();
        this.date=new java.util.Date(millis);

    }
}
