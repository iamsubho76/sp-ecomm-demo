package com.sp.ecomm.utils;

import com.sp.ecomm.dto.Response;
import com.sp.ecomm.dto.ResponseStatus;
import org.slf4j.MDC;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;

public class B2cEcommerceApplicationUtil {
    public static String appendMessage(final String... msg) {
        StringBuilder build = new StringBuilder();
        Arrays.stream(msg).filter(message -> message != null)
                .forEach(build::append);
        return build.toString();
    }

    public static String traceErrorContent(Exception e, String transactionID) {
        String stack = Arrays.toString(e.getStackTrace());
        return B2cEcommerceApplicationUtil.appendMessage(stack, " for transaction id = ", transactionID);
    }

    public static void buildResponse(Response<?> response, ResponseStatus responseStatus) {
        response.setStatus(responseStatus);
        response.setUri(MDC.get(B2cEcommerceApplicationConstants.REQ_URI));
        response.setTimestamp(LocalDateTime.now(ZoneId.systemDefault()));
        response.setTransactionId(MDC.get(B2cEcommerceApplicationConstants.DEFAULT_ORG_TID));
    }
}
