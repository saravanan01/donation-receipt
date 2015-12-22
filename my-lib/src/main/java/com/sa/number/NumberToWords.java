package com.sa.number;

import java.text.DecimalFormat;

public class NumberToWords {
	public static String getWords(double no) {
		StringBuffer wordsBuf = new StringBuffer(50);
		String strNo = formatDouble(no);
		int decimalIndex = strNo.indexOf(".");

		if (decimalIndex > 0) {
			String wholeNo = strNo.substring(0, decimalIndex);
			String decimalStr = strNo.substring(decimalIndex + 1);
			getWordsForDecimalNumber(wordsBuf, wholeNo, decimalStr);
		} else {
			String wholeNo = strNo;
			getWordsForDecimalNumber(wordsBuf, wholeNo, null);
		}

		return wordsBuf.toString();
	}

	private static void getWordsForDecimalNumber(StringBuffer wordsBuf, String wholeNo, String decimalStr) {
		if (wholeNo != null) {
			long no = Long.parseLong(wholeNo);
//			getWordsForInt(wordsBuf, no, wholeNo.length());
			wordsBuf.append( EnglishNumber.convert(no) ).append(" Rupees");
		}
		if (decimalStr != null) {
			wordsBuf.append(" and ");
			long no = Long.parseLong(decimalStr);
			wordsBuf.append( EnglishNumber.convert(no) ).append(" Paise only");
		}
		wordsBuf.append('.');
	}

//	private static void getWordsForInt(StringBuffer wordsBuf, int no, int noOfDigits) {
//		int tmpNo = no;
//		int[] digits = new int[noOfDigits];
//		int index = 0;
//		while (tmpNo > 0) {
//			digits[(index++)] = (tmpNo % 10);
//			tmpNo /= 10;
//		}
//		for (int i = digits.length - 1; i > 0; i++)
//			;
//	}

	private static DecimalFormat format = new DecimalFormat("####.00");

	public static String formatDouble(double no) {
		String val = "";
		try {
			val = format.format(no);
		} catch (Exception localException) {
		}
		return val;
	}
}
