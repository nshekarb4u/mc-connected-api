package com.mastercard.connected.exception;

import com.mastercard.connected.util.ApiResullt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.TypeMismatchException;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageConversionException;
import org.springframework.validation.BindException;
import org.springframework.web.HttpMediaTypeException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.context.request.async.AsyncRequestTimeoutException;
import org.springframework.web.multipart.support.MissingServletRequestPartException;
import org.springframework.web.servlet.NoHandlerFoundException;

//@EnableWebMvc
@RestControllerAdvice
public class RestApiExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(RestApiExceptionHandler.class);

    @ExceptionHandler({ServletRequestBindingException.class, HttpMediaTypeException.class, NoHandlerFoundException.class
            , HttpRequestMethodNotSupportedException.class, TypeMismatchException.class, HttpMessageConversionException.class
            , MethodArgumentNotValidException.class, MissingServletRequestPartException.class, AsyncRequestTimeoutException.class
            , BindException.class
            })
    @ResponseStatus(HttpStatus.OK)
    public String handleClientExceptions(Exception exception){
        logger.error("Unexpected input is supplied,exceptionNameResolved={}", exception.getMessage());
        return ApiResullt.NO.getValue();
    }

    @ExceptionHandler(value = {Exception.class})
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    protected String handleOtherAll(Exception exception, WebRequest request) {
        logger.error("Other unexpected exception encountered, message={}", exception.getMessage());
        String responseMsg = "Unexpected Error at server side. Please contact API Team if it repeats.";
        return responseMsg;
    }
}
