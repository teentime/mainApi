package kr.teentime.mainApi.controller;

import kr.teentime.mainApi.util.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {

        return Result.error("bad data request", 400);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {

        ex.printStackTrace();
        return Result.internalError();
    }
}
