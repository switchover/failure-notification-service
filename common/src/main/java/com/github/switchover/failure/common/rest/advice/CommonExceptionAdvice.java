package com.github.switchover.failure.common.rest.advice;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class CommonExceptionAdvice {
    private final Logger logger = LoggerFactory.getLogger(CommonExceptionAdvice.class);

    @Value("${isDetailExceptionResponse:false}")
    private boolean isDetailExceptionResponse;

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<HttpErrorInfo> notFoundException(ResourceNotFoundException ex) {
        // w/o body
        //return ResponseEntity.notFound().build();

        // w/ body
        HttpErrorInfo errorInfo = new HttpErrorInfo(404, ex.getMessage());

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorInfo);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<HttpErrorInfo> alreadyExist(ResourceAlreadyExistsException ex) {
        HttpErrorInfo errorInfo = new HttpErrorInfo(400, ex.getMessage());

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errorInfo);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> exception(Exception ex) {

        logger.error("Occurred Exception", ex);

        ObjectMapper mapper = new ObjectMapper();
        String respJsonString = "{}";

        Object errorInfo = null;
        if (isDetailExceptionResponse) {
            errorInfo = new ErrorInfo(ex);
        } else {
            errorInfo = new HttpErrorInfo(500, "A server error has occurred. Please contact administrator.");
        }

        try {
            respJsonString = mapper.writeValueAsString(errorInfo);
        } catch (JsonProcessingException jex) {
            logger.error("JsonProcessingException", jex);
        }

        return ResponseEntity.internalServerError().body(respJsonString);
    }

    private static class ErrorInfo {
        public final String className;
        public final String exMessage;

        public ErrorInfo(Exception ex) {
            this.className = ex.getClass().getName();
            this.exMessage = ex.getLocalizedMessage();
        }
    }

    /*
    // 기본 에러 응답
    {
        "timestamp": "2022-03-20T12:50:13.910+00:00",
        "status": 400,
        "error": "Bad Request",
        "message": "",
        "path": "/v1/groups/"
    }
    */
    private static class HttpErrorInfo {
        public final int errorCode;
        public final String errorMessage;

        public HttpErrorInfo(int errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }
    }
}
