package com.springbootacademy.point_of_sale.advisor;

import com.springbootacademy.point_of_sale.exception.NotFoundException;
import com.springbootacademy.point_of_sale.utill.StandardResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AppWideExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<StandardResponse> handleNotFoundException(NotFoundException e){
        return new ResponseEntity<StandardResponse>(
                new StandardResponse(404,"Not Found",e.getMessage()),
                HttpStatus.OK
        );
    }

}
