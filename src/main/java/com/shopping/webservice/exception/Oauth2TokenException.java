package com.shopping.webservice.exception;

import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@AllArgsConstructor
@ResponseStatus(value = HttpStatus.UNAUTHORIZED)
public class Oauth2TokenException extends RuntimeException{

    public Oauth2TokenException(String message){
        super(message);
    }
}
