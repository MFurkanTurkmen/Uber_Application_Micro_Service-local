package com.furkan.exception;

import lombok.Getter;

@Getter
public class DriverException extends RuntimeException{
    private final EErrorType EErrorType;

    /**
     * Runtime dan miras aldığımız için hata mesajının kendisine iletilmesi gereklidir.
     * @param EErrorType
     */
    public DriverException(EErrorType EErrorType){
        super(EErrorType.getMessage());
        this.EErrorType = EErrorType;
    }

    public DriverException(EErrorType EErrorType, String message){
        super(message);
        this.EErrorType = EErrorType;
    }
}
