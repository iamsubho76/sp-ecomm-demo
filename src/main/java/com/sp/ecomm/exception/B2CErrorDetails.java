package com.sp.ecomm.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class B2CErrorDetails {
    private String message;
    private String traceId;
    private String errorCode;
}