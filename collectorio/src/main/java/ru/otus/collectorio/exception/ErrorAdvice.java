package ru.otus.collectorio.exception;

import lombok.Data;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class ErrorAdvice {
    @ExceptionHandler(BaseException.class)
    public ResponseEntity<ErrorModel> showError(Exception e){
        ErrorModel errorModel = new ErrorModel();
        errorModel.setErrorCode(e.getMessage());
        errorModel.setStatusHttp(String.valueOf(HttpStatus.EXPECTATION_FAILED));

        return new ResponseEntity<>(errorModel,HttpStatus.EXPECTATION_FAILED);
    }
    @Data
    static class ErrorModel{
        public String errorCode;
        public String statusHttp;
        public LocalDateTime timeStamp = LocalDateTime.now();
    }
}
