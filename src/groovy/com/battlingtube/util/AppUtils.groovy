package com.battlingtube.util

import java.text.DateFormat
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class AppUtils {

  private static Date currentDate;

  public static Date getCurrentDate() {
    ConfigurationHolder config = ConfigurationHolder
    boolean useTestDate = ((String) ConfigurationHolder.config.bt.testdate.on).toBoolean().booleanValue();
    if (useTestDate) {
      if (currentDate == null) {
        String testDate = ConfigurationHolder.config.bt.testdate.value

        currentDate = initializeDate(testDate)
      }
      return currentDate
    }
    else {
      return new Date();
    }

  }

  public static String stripInvalidText(String text) {
    if(text.toLowerCase().contains("<script")) {
      return "";
    }
    text = text.substring(0, Math.min(text.size(), 100))
    return text.replaceAll("<", "[").
    replaceAll(">","]")
  }

  public static void advanceDateNumberDays(int days) {
    Calendar cal = new GregorianCalendar()
    cal.time = currentDate
    cal.add Calendar.DAY_OF_YEAR, days
    currentDate = cal.time + 10 // slighty more than a day
  }

  private static Date initializeDate(String testDate) {
    currentDate = getDateFromString(testDate)
    return currentDate
  }

  public static Date getDateFromString(String testDate) {
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT)
    df.parse(testDate);
    Date date = df.calendar.time
    return date
  }

  public static setCurrentDate(Date date) {
    currentDate = date
  }

  public static setCurrentDateFromString(String dateString) {

    currentDate = initializeDate(dateString)
  }

  public static String getCurrentDateAsString() {
    DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT)
    return df.format(getCurrentDate())
  }


  public static Date subtractDate(Date date, Date date2) {
    return new Date(date.time - date2.time)
  }

  public static boolean isInTestMode() {
    boolean loadTestData = ((String) ConfigurationHolder.config.bt.loadTestData).toBoolean().booleanValue()
    return loadTestData
  }

}