package com.sp.ecomm.config.aop;

import com.sp.ecomm.config.aop.impl.FileLogger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class LogAppender {

	@Autowired
	private FileLogger fileLogger;

	/**
	 * Based on property value it will return the instance of file or console
	 * 
	 * @return
	 */
	public ILogger getLoggerInstance() {
		return fileLogger;
	}
}