package com.sp.ecomm.config.aop;

import com.sp.ecomm.exception.B2CException;
import com.sp.ecomm.utils.B2cEcommerceApplicationConstants;
import com.sp.ecomm.utils.B2cEcommerceApplicationUtil;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class B2cEcommerceApplicationLogger {
    /**
     * The ilogger.
     */
    private ILogger ilogger;

    @Around(value = "execution(* com.ecommerce.controller.*.*.*(..))||" +
            "execution(* com.ecommerce.service.*.*.*(..))||" +
            "execution(* com.ecommerce.repository.*.*.*(..))"
    )
    public Object logB2cEcommerceApplication(ProceedingJoinPoint jointPoint) throws Throwable {
        Object obj = null;
        try {

            ilogger.info(B2cEcommerceApplicationUtil.appendMessage(B2cEcommerceApplicationConstants.ENTERING_TO, jointPoint.getSignature().toShortString()), B2cEcommerceApplicationConstants.BLANK_STRING);

            long start = System.currentTimeMillis();
            obj = jointPoint.proceed();
            long elapsedTime = System.currentTimeMillis() - start;
            ilogger.info(B2cEcommerceApplicationUtil.appendMessage(B2cEcommerceApplicationConstants.EXITING_FROM, jointPoint.getSignature().toShortString(), B2cEcommerceApplicationConstants.TRANSACTION_ID_SUCCESSFULL_MESSAGE, " And Method execution time: ", String.valueOf(elapsedTime), " milliseconds."), B2cEcommerceApplicationConstants.BLANK_STRING);

            return obj;
        } catch (B2CException e) {
            ilogger.error(B2cEcommerceApplicationUtil.appendMessage(B2cEcommerceApplicationConstants.EXCEPTION_CAME_ON, jointPoint.getSignature().toShortString()
                    , B2cEcommerceApplicationConstants.WITH_ERROR_CODE, e.logErrorContent(e), B2cEcommerceApplicationConstants.FOR_INPUT
                    , Arrays.toString(jointPoint.getArgs())));
            throw e;
        } catch (Exception e) {
            ilogger.error(B2cEcommerceApplicationUtil.appendMessage(B2cEcommerceApplicationConstants.EXCEPTION_ARGS, jointPoint.getSignature().toShortString()
                    , B2cEcommerceApplicationConstants.WITH_ERROR, B2cEcommerceApplicationUtil.traceErrorContent(e, B2cEcommerceApplicationConstants.BLANK_STRING), B2cEcommerceApplicationConstants.FOR_INPUT
                    , Arrays.toString(jointPoint.getArgs())));
            throw e;
        }
    }
}
