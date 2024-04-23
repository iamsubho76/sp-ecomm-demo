package com.sp.ecomm.exception.handler;

import com.sp.ecomm.exception.B2CErrorDetails;
import com.sp.ecomm.exception.B2CResourceNotFoundException;
import com.sp.ecomm.utils.B2cEcommerceApplicationConstants;
import com.sp.ecomm.exception.B2CException;
import lombok.extern.slf4j.Slf4j;
import org.apache.tomcat.util.buf.StringUtils;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
@Slf4j
public class B2CGlobalExceptionHandler {


    @ExceptionHandler(B2CResourceNotFoundException.class)
    public ResponseEntity<B2CErrorDetails> handleResourceNotFoundException(B2CResourceNotFoundException exception) {
        log.error("ResourceNotFoundException occurred", exception);
        B2CErrorDetails B2CErrorDetails = new B2CErrorDetails(
                exception.getMessage(),
                MDC.get(B2cEcommerceApplicationConstants.TRANSACTION_ID),
                "RESOURCE_NOT_FOUND"
        );
        return new ResponseEntity<>(B2CErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(B2CException.class)
    public ResponseEntity<B2CErrorDetails> handleApplicationException(B2CResourceNotFoundException exception) {
        B2CErrorDetails B2CErrorDetails = new B2CErrorDetails(
                exception.getMessage(),
                MDC.get(B2cEcommerceApplicationConstants.DEFAULT_ORG_TID),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(B2CErrorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<B2CErrorDetails> handleValidationErrors(MethodArgumentNotValidException ex) {
        List<String> errors = ex.getBindingResult().getFieldErrors()
                .stream().map(FieldError::getDefaultMessage).collect(Collectors.toList());
        B2CErrorDetails B2CErrorDetails = new B2CErrorDetails(
                StringUtils.join(errors, ','),
                MDC.get(B2cEcommerceApplicationConstants.DEFAULT_ORG_TID),
                "BAD_REQUEST");
        return new ResponseEntity<>(B2CErrorDetails, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<B2CErrorDetails> handleGlobalException(Exception exception) {

        B2CErrorDetails B2CErrorDetails = new B2CErrorDetails(
                exception.getMessage(),
                MDC.get(B2cEcommerceApplicationConstants.DEFAULT_ORG_TID),
                "INTERNAL_SERVER_ERROR"
        );
        return new ResponseEntity<>(B2CErrorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}