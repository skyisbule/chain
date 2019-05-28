package com.github.skyisbule.chain.interceptor;

import com.github.skyisbule.chain.domain.ExceptionEnty;
import com.github.skyisbule.chain.exception.ExceptionMessage;
import com.github.skyisbule.chain.exception.GlobalException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;


@RestControllerAdvice
public class GlobalExceptionHandler {

    private HashMap<String,String> message = ExceptionMessage.message;

    @ExceptionHandler
    public ExceptionEnty jsonErrorHandle(GlobalException exception){
        String errCode = exception.getMessage();
        if (message.containsKey(errCode))
            return new ExceptionEnty(Integer.parseInt(errCode), ExceptionMessage.message.get(errCode));
        return new ExceptionEnty(0,"未知错误");
    }

}
