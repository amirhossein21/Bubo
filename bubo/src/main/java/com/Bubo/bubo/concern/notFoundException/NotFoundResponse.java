package com.Bubo.bubo.concern.notFoundException;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class NotFoundResponse {
    String message;
    Date date;

    public NotFoundResponse(String m){
        this.message=m;
        long millis=System.currentTimeMillis();
        this.date=new java.util.Date(millis);

    }

}
