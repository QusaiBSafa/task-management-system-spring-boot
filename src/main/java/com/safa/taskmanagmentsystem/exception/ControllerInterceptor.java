package com.safa.taskmanagmentsystem.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class ControllerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(ControllerInterceptor.class);

    private HttpServletRequest request;

    public ControllerInterceptor(HttpServletRequest request) {
        this.request = request;
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    protected ResponseEntity<?> badRequestException(BadRequestException e) {
        return  ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(createError(e, e.getMessage(), HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(ForbiddenRequestException.class)
    protected ResponseEntity forbiddenRequestException(ForbiddenRequestException e) {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body(createError(e, e.getMessage(), HttpStatus.FORBIDDEN.value()));
    }

    @ExceptionHandler(MissingServletRequestParameterException.class)
    protected ResponseEntity missingServletRequestParameterException(MissingServletRequestParameterException e) {
        return ResponseEntity
                .badRequest()
                .body(createError(e, e.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity handleMethodArgumentNotValid(MethodArgumentNotValidException e) {
        //Get all errors
        List<String> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(x -> x.getObjectName() + "." + x.getField() + " " + x.getDefaultMessage())
                .sorted()
                .collect(Collectors.toList());

        return ResponseEntity
                .badRequest()
                .body(createError(e, String.join(",", errors)));
    }



    private BaseErrorModel createError(Exception exception, String errorMessage) {
        return createError(exception, errorMessage, HttpStatus.BAD_REQUEST.value());
    }

    private BaseErrorModel createError(Exception exception, String errorMessage, Integer status) {
        LOGGER.error("Error {} with message : {}", exception.getClass().getSimpleName(), errorMessage);
        return new BaseErrorModel(status, errorMessage, request.getContextPath() + request.getServletPath());
    }

}
