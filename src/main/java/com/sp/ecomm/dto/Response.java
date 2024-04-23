package com.sp.ecomm.dto;

import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

@Data
public class Response<T> implements Serializable {
    private ResponseStatus status;
    private String message;
    private String uri;
    private String transactionId;
    private LocalDateTime timestamp;
    private transient T data;
}