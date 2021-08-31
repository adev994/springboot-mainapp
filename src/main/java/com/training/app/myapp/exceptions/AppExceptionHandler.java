package com.training.app.myapp.exceptions;


import com.training.app.myapp.responses.ErrorMessage;
import com.training.app.myapp.responses.UserResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice(basePackages ="com.training.app.myapp")
public class AppExceptionHandler {

    @ExceptionHandler(value ={UserException.class})
    public ResponseEntity<Object> userException(UserException ex, WebRequest webRequest){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value =Exception.class)
    public ResponseEntity<Object> othersExceptions(Exception ex, WebRequest webRequest){
        ErrorMessage errorMessage=new ErrorMessage(new Date(),ex.getMessage());
        return new ResponseEntity<>(errorMessage, new HttpHeaders(),HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value =MethodArgumentNotValidException.class)
    public ResponseEntity<Object> userValidationRequestException(MethodArgumentNotValidException ex, WebRequest webRequest){

        Map<String,String> errors = new HashMap<>();

        ex.getBindingResult().getFieldErrors().forEach(error ->
                errors.put(error.getField(), error.getDefaultMessage()));

        return new ResponseEntity<>(errors, new HttpHeaders(),HttpStatus.BAD_REQUEST);
    }
}
