package kr.teentime.mainApi.controller;

import jakarta.validation.UnexpectedTypeException;
import kr.teentime.mainApi.util.Result;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity handleDataIntegrityViolationException(
            DataIntegrityViolationException ex) {

        return Result.error("bad data request", 400);
    }

    @ExceptionHandler(UsernameNotFoundException.class)
    public ResponseEntity UsernameNotFoundExceptionHandler(
            UsernameNotFoundException ex) {

        return Result.error(ex.getMessage(), 404);
    }

    @ExceptionHandler(UnexpectedTypeException.class)
    public ResponseEntity handleException(UnexpectedTypeException ex) {
        return Result.error(ex.getMessage(), HttpStatus.NOT_FOUND.value());
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity handleException(Exception ex) {

        ex.printStackTrace();
        return Result.internalError();
    }
}
