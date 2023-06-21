package com.furkan.utility;

import com.furkan.exception.AuthException;
import com.furkan.exception.EErrorType;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class RegisterExceptionControl {

    public void ControllerControl(String password, String rePassword, int age, String email,String name){
        if (password.isEmpty()) throw new AuthException(EErrorType.PASSWORD_NOT_NULL);
       // if (rePassword.trim()!=password.trim()) throw new AuthException(EErrorType.PASSWORD_NOT_MATCH);
        if (age<18) throw new AuthException(EErrorType.BAD_REQUEST_ERROR);
        if (email.isEmpty()) throw new AuthException(EErrorType.EMAİL_NOT_NULL);
        if (name.isEmpty()) throw new AuthException(EErrorType.NAME_NOT_NULL);
    }
}
