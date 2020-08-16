package com.mastercard.connectivity.exception;

import com.mastercard.connectivity.util.ApiResullt;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@RestControllerAdvice
public class RestApiExceptionHandler {

    @ExceptionHandler({ServletRequestBindingException.class, HttpMediaTypeException.class, NoHandlerFoundException.class
            , HttpRequestMethodNotSupportedException.class, TypeMismatchException.class, HttpMessageConversionException.class
            , MethodArgumentNotValidException.class, MissingServletRequestPartException.class, AsyncRequestTimeoutException.class
            , BindException.class
            })
    @ResponseStatus(HttpStatus.OK)
    public String handleClientExceptions(Exception exception){
        return ApiResullt.NO.getValue();
    }
}
