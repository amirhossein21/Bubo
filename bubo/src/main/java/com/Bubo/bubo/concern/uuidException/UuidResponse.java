package com.Bubo.bubo.concern.uuidException;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class UuidResponse {
    String message;
    Date date;


    public UuidResponse(String m){
        this.message=m;
        this.date=new Date();

    }
}
