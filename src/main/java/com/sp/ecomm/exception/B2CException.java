package com.sp.ecomm.exception;

import com.sp.ecomm.utils.B2cEcommerceApplicationUtil;

public class B2CException extends RuntimeException {
	private static final long serialVersionUID = 1L;

	private int errorCode;
	private String errorMsg;
	private String transactionID;

	public B2CException() {
	}

	public B2CException(int errorCode, String exceptionMessage, String transactionId) {
		super(exceptionMessage);
		this.errorCode = errorCode;
		this.errorMsg = exceptionMessage;
		this.transactionID = transactionId;
	}

	public int getErrorCode() {
		return errorCode;
	}

	public String getErrorMsg() {
		return errorMsg;
	}

	public String getTransactionID() {
		return transactionID;
	}

	/**
	 *
	 * @param e
	 * @return
	 */
	public String logErrorContent(B2CException e) {
		return B2cEcommerceApplicationUtil.appendMessage("Error code:: ", String.valueOf(e.getErrorCode()),
				" and error message is:: ",
				e.getErrorMsg(),
				" for transaction id:: ",
				e.getTransactionID());
	}
}
