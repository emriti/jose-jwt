package com.ecomindo.common.util;

import java.util.ArrayList;

public class TextParserUtil {

	public static ArrayList<String> textParser(String rawData) {

		char[] raw = rawData.toCharArray();
		ArrayList<String> result = new ArrayList<>();

		try {
			// bool inField = true;
			// denotes whether we are handling a string
			Boolean inString = false;
			// hold text buffer
			String buffer = "";
			// denotes whether previous char is a quote
			Boolean foundQuote = false;

			for (char c : raw) {
				// if (c == ' ' && !inString) {
				// // do nothing
				// } else
				if (c == '\"' && !inString) {

					if (foundQuote) {
						// post cond: previous char is a quote.
						// because current char is also a quote, then add quote
						// to
						// buffer
						buffer += '\"';
						// denote that previous char is no longer a quote
						foundQuote = false;
					} else if (buffer != "") {
						// post cond: previous char is not quote. current char
						// is
						// quote, but buffer contain something
						// this is invalid (eg ==> "123",456"789","123")
						// return null if invalid format
						return null;
					}

					// denote that we are handling a string
					inString = true;
				} else if (c == '\"' && inString) {
					// post cond: found a quote while handling a string
					// denote that we are no longer handling a string
					inString = false;
					// denote that we found quote, in case of next char is also
					// a
					// quote
					foundQuote = true;
				} else if (c == ',' && !inString) {
					// post cond: found comma and we are not handling a string
					// it means the comma is a separator
					// add current buffer as new result
					result.add(buffer);
					// reset buffer
					buffer = "";
					// denote that previous char is not a quote
					// this is important, because when we met a string end, then
					// foundQuote flag = true
					foundQuote = false;
				} else {
					// post cond:
					// add any char to buffer
					buffer = buffer + c;
				}
			}
			// add last buffer
			result.add(buffer);
		} catch (Exception e) {

		}
		return result;
	}

}
