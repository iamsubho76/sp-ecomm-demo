package com.sp.ecomm.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class B2CResourceNotFoundException extends B2CException {

    private String resourceName;
    private String fieldName;
    private String fieldValue;

    public B2CResourceNotFoundException() {}

    public B2CResourceNotFoundException(int errorCode, String resourceName, String fieldName, String fieldValue, String transactionId) {
        super(errorCode, String.format("%s not found with %s : '%s'", resourceName, fieldName, fieldValue), transactionId);
        this.resourceName = resourceName;
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
