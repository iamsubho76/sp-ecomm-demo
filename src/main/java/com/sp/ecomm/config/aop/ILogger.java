package com.sp.ecomm.config.aop;

import org.springframework.stereotype.Service;

@Service
public interface ILogger {

	/**
	 * Debug.
	 *
	 * @param msg the msg
	 */
	public void debug(final String msg,final String corRelationid);

	/**
	 * Info.
	 *
	 * @param msg the msg
	 */
	public void info(final String msg,final String corRelationid);

	/**
	 * Error.
	 *
	 * @param msg the msg
	 */
	public void error(final String msg);
	
	
	/**
	 * Logtransaction id.
	 *
	 * @param transactionId the transaction id
	 */
	public void logtransactionId(final String transactionId);

    void warn(final String msg,final String corRelationid);
}