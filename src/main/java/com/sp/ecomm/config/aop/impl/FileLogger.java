package com.sp.ecomm.config.aop.impl;

import com.sp.ecomm.config.aop.ILogger;
import com.sp.ecomm.utils.B2cEcommerceApplicationConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Calendar;
import java.util.TimeZone;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Class FileLogger.
 */
@Component
public class FileLogger implements ILogger {

    /**
     * This method is implemented to mask certain sensitive information based regex
     * patterns before logging.
     *
     * @param msg the msg
     * @return the string
     */
    private static final Pattern SENSITIVE_INFO_PATTERN = Pattern.compile(B2cEcommerceApplicationConstants.CHECK_SENSITIVE_INFO);
    /**
     * The logger.
     */
    private final Logger logger;
    /**
     * The masking required.
     */
    @Value("${com.org.log.masking}")
    private boolean maskingRequired;

    /**
     * Instantiates a new logger.
     */
    public FileLogger() {
        logger = LoggerFactory.getLogger(getClass());
    }

    /**
     * Debug.
     *
     * @param msg           the msg
     * @param corRelationid the cor relationid
     */
    @Override
    public void debug(String msg, String corRelationid) {
        if (maskingRequired)
            msg = maskSensitive(msg);
        String debugLog = getTimestamp().concat(msg);
        this.logger.debug(debugLog);
    }

    /**
     * Info.
     *
     * @param msg           the msg
     * @param corRelationid the cor relationid
     */
    @Override
    public void info(String msg, String corRelationid) {
        if (maskingRequired)
            msg = maskSensitive(msg);
        String infoLog = getTimestamp().concat(msg).concat(corRelationid);
        this.logger.info(infoLog);
    }

    /**
     * Warn.
     *
     * @param msg           the msg
     * @param corRelationid the cor relationid
     */
    @Override
    public void warn(String msg, String corRelationid) {
        if (maskingRequired)
            msg = maskSensitive(msg);
        String warnLog = getTimestamp().concat(msg).concat(corRelationid);
        this.logger.warn(warnLog);
    }

    /**
     * Error.
     *
     * @param msg the msg
     */
    @Override
    public void error(String msg) {
        String errorLog = getTimestamp().concat(msg);
        this.logger.error(errorLog);
    }

    /**
     * Gets the timestamp.
     *
     * @return the timestamp
     */
    public String getTimestamp() {
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        String dateFormat = B2cEcommerceApplicationConstants.DATE_FORMAT_WITH_MS;
        java.text.SimpleDateFormat sdf = new java.text.SimpleDateFormat(dateFormat);
        sdf.setTimeZone(TimeZone.getDefault());
        String currentTime = sdf.format(cal.getTime());
        return currentTime.concat(B2cEcommerceApplicationConstants.HASHTAG_WITH_SPACE);
    }

    /**
     * Logtransaction id.
     *
     * @param transactionId the transaction id
     */
    @Override
    public void logtransactionId(String transactionId) {
        String infoLog = getTimestamp().concat(transactionId);
        this.logger.info(infoLog);
    }

    private String maskSensitive(String msg) {
        Pattern pattern = Pattern.compile(B2cEcommerceApplicationConstants.CHECK_SENSITIVE_INFO);
        Matcher matcher = pattern.matcher(msg);
        String maskingChar = B2cEcommerceApplicationConstants.ASTERISK;
        StringBuilder finalMask;

        while (matcher.find()) {
            String group = matcher.group();
            int groupLen = group.length();
            finalMask = new StringBuilder(maskingChar);
            if (groupLen > 4) {
                for (int i = 0; i <= group.length() - 4; i++) {

                    finalMask.append(maskingChar);
                }
                finalMask.append(group.substring(groupLen - 4));
            }
            msg = msg.replace(group, finalMask);
        }
        return maskEmail(msg);
    }

    /**
     * Mask email.
     *
     * @param msg the msg
     * @return the string
     */
    private String maskEmail(String msg) {
        String masMsg;
        Matcher m = Pattern.compile(B2cEcommerceApplicationConstants.EMAIL_PATTERN).matcher(msg);
        masMsg = m.replaceAll(B2cEcommerceApplicationConstants.EMAIL_MASK);
        return masMsg;
    }
}