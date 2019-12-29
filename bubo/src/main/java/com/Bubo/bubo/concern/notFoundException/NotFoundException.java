package com.Bubo.bubo.concern.notFoundException;

import java.security.PublicKey;

public class NotFoundException extends RuntimeException {
    public NotFoundException(String m){
        super(m);
    }
}
