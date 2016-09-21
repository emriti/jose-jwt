package com.ecomindo.common.exception;

import org.apache.log4j.Logger;

public class DAOException extends RuntimeException {
	private static final long serialVersionUID = -8852593974738250673L;
	private static Logger log = Logger.getLogger(DAOException.class);

	public DAOException(Exception ex) {
		super(ExceptionUtil.extractExceptionStackTraceMessage(ex));
		log.error(ExceptionUtil.extractExceptionStackTraceMessage(ex));
	}

	public DAOException(String message) {
		super(message);
		log.error(message);
	}

	public DAOException(Logger logger, Exception ex) {
		super(ex);
		log = (logger == null ? log : logger);
		log.error(ExceptionUtil.extractExceptionStackTraceMessage(ex));
	}

	public DAOException(Logger logger, String message) {
		super(message);
		log = (logger == null ? log : logger);
		log.error(message);
	}

	public DAOException(Logger logger, Throwable cause) {
		super(cause);
		log = (logger == null ? log : logger);
		log.error(cause.getMessage());
	}

	public DAOException(Logger logger, String message, Throwable cause) {
		super(message, cause);
		log = (logger == null ? log : logger);
		log.error(message + ", detail:" + cause.getMessage());
	}

	public DAOException(Logger logger, String message, Exception ex) {
		super(message, ex);
		log = (logger == null ? log : logger);
		log.error(message + ", detail:" + ExceptionUtil.extractExceptionStackTraceMessage(ex));
	}
}
