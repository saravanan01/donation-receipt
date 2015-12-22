package com.sa.number;

import java.text.DecimalFormat;

public class EnglishNumber
{
  private static String[] TENS_NAMES = { 
    "", 
    "Ten", 
    "Twenty", 
    "Thirty", 
    "Forty", 
    "Fifty", 
    "Sixty", 
    "Seventy", 
    "Eighty", 
    "Ninety" };

  private static String[] NUM_NAMES = { 
    "", 
    "One", 
    "Two", 
    "Three", 
    "Four", 
    "Five", 
    "Six", 
    "Seven", 
    "Eight", 
    "Nine", 
    "Ten", 
    "Eleven", 
    "Twelve", 
     "Thirteen", 
     "Fourteen", 
     "Fifteen", 
     "Sixteen", 
     "Seventeen", 
     "Eighteen", 
     "Nineteen" };

  public static String convert(long no)
  {
    if (no == 0L) {
      return "Zero";
    }

    String sNumber = String.valueOf(no);
    DecimalFormat format = new DecimalFormat("0000000000");
    sNumber = format.format(no);

    int hundreds = Integer.parseInt(sNumber.substring(sNumber.length() - 3));
    int thousands = Integer.parseInt(sNumber.substring(sNumber.length() - 5, sNumber.length() - 3));
    int lacks = Integer.parseInt(sNumber.substring(sNumber.length() - 7, sNumber.length() - 5));
    int crores = Integer.parseInt(sNumber.substring(0, sNumber.length() - 7));
    StringBuffer buffer = new StringBuffer(50);
    switch (crores) {
    case 0:
      buffer.append("");
      break;
    default:
      buffer.append(convertHundres(crores)).append(crores == 1 ? " Crore " : " Crores ");
    }

    switch (lacks) {
    case 0:
      buffer.append("");
      break;
    default:
      buffer.append(convertHundres(lacks)).append(lacks == 1 ? " Lakh " : " Lakhs ");
    }

    switch (thousands) {
    case 0:
      buffer.append("");
      break;
    default:
      buffer.append(convertHundres(thousands)).append(thousands == 1 ? " Thousands " : " Thousands ");
    }

    buffer.append(convertHundres(hundreds));

    return buffer.toString().replaceAll("  ", " ").trim();
  }

  private static String convertHundres(int no) {
    String temp = "";
    if (no < 20) {
      temp = NUM_NAMES[(no % 100)];
      no /= 10;
      return temp;
    }
    temp = NUM_NAMES[(no % 10)];
    no /= 10;

    temp = TENS_NAMES[(no % 10)] + " " + temp;
    no /= 10;

    if (no == 0) {
      return temp;
    }
    return NUM_NAMES[no] + " hundred " + temp;
  }
}
