package com.example.virtuallearn.Exception;

import com.example.virtuallearn.Controller.Response.ResponseWrapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ExceptionHandlers {

    @ExceptionHandler(AlreadyExistException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper handleAlreadyExistException(AlreadyExistException alreadyExistException) {
        return new ResponseWrapper(alreadyExistException.getResult(), null);
    }

    @ExceptionHandler(EnterValidCredentialException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper handleEnterValidCredentialException(EnterValidCredentialException enterValidCredentialException) {
        return new ResponseWrapper(enterValidCredentialException.getResult(), null);
    }

    @ExceptionHandler(CategoryNotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper categoryNotFoundException(CategoryNotFoundException categoryNotFoundException) {
        return new ResponseWrapper(categoryNotFoundException.getResult(), null);
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseBody
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseWrapper notFoundException(NotFoundException notFoundException) {
        return new ResponseWrapper(notFoundException.getResult(), null);
    }
}
