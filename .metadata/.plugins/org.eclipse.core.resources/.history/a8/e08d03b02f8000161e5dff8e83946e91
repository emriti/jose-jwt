package com.ecomindo.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Collections;

import com.ecomindo.common.dto.ErrorMessage;

public class ExceptionUtil {

	public static String extractExceptionStackTraceMessage2(Exception e) {
		String result = "";
		if (e != null) {
			result += e.getMessage() + "\r\n" + printStackTraceMessage(e);
		}
		return result;
	}

	public static String extractExceptionStackTraceMessage(Exception ex) {

		String msg = "";
		msg += ex.getMessage() + "\n";
		while (ex.getCause() != null) {
			ex = (Exception) ex.getCause();
			msg += ex.getMessage() + "\n";
		}
		return msg;
	}

	public static ErrorMessage getInnerMessage(Exception e) {
		ErrorMessage emsg = new ErrorMessage();
		emsg.setMessage(e.getMessage());
		if (e != null) {
			emsg.setInnerException(getMessage(e.getCause()));
		}
		return emsg;
	}

	public static ErrorMessage getMessage(Throwable e) {
		ErrorMessage emsg = new ErrorMessage();
		emsg.setMessage(e.getMessage());
		if (e.getCause() != null) {
			emsg.setInnerException(getMessage(e.getCause()));
		}
		return emsg;
	}

	public static ErrorMessage getException(Exception e) {
		ErrorMessage emsg = new ErrorMessage();
		// String stackTrace = "";
		emsg.setMessage(e.getMessage());
		if (e != null) {
			emsg.setInnerException(getMessage(e.getCause()));
			emsg.setStackTrace(getStringMessage(e.getCause()));
		}
		return emsg;
	}

	public static ArrayList<String> getStringMessage(Throwable e) {
		ArrayList<String> stackTrace = new ArrayList<String>();
		if (e != null) {
			stackTrace.add(e.getMessage());
			stackTrace.addAll(getStringMessage(e.getCause()));
		}
		Collections.reverse(stackTrace);
		return stackTrace;
	}

	private static String printStackTraceMessage(Exception e) {
		String result = "";
		StringWriter sw = null;
		try {
			sw = new StringWriter();
			e.printStackTrace(new PrintWriter(sw));
			result = sw.toString();
		} finally {
			if (sw != null) {
				try {
					sw.close();
				} catch (Exception e2) {
					e2.printStackTrace();
				}
			}
		}
		return result;
	}

	public static String getStringInnerMessage(Throwable e) {
		String emsg = "";
		if (e != null) {
			if (e.getCause() != null) {
				emsg = getStringInnerMessage(e.getCause());
			} else {
				emsg = e.getMessage();
			}
		}
		return emsg;
	}

}
