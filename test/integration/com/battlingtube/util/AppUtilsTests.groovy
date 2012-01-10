package com.battlingtube.util

import com.battlingtube.domain.ImageKey
import org.codehaus.groovy.grails.commons.ConfigurationHolder

class AppUtilsTests extends GroovyTestCase {

    public void setUp() {
        ConfigurationHolder.config.bt.testdate.on = true

        Calendar cal = new GregorianCalendar()
        cal.set Calendar.YEAR, 2007
        cal.set Calendar.MONTH, Calendar.SEPTEMBER
        cal.set Calendar.DAY_OF_MONTH, 23
        AppUtils.currentDate = cal.time
    }

    void testGetCurrentDate() {

        Date date = AppUtils.getCurrentDate();

        Calendar cal = new GregorianCalendar()
        cal.time = date
        assertEquals 2007, cal.get(Calendar.YEAR)
    }

    void testAdvanceDateNumberDays() {
        AppUtils.advanceDateNumberDays 2

        Calendar cal = new GregorianCalendar()
        cal.time = AppUtils.currentDate
        assertEquals 25, cal.get(Calendar.DAY_OF_MONTH)
    }

    void testSubtractDate() {
        long threeDays = 1000 * 60 * 60 * 24 * 3
        Date d0 = new Date()
        Calendar c1 = GregorianCalendar.getInstance()
        c1.setTime(d0)
        Calendar c2 = GregorianCalendar.getInstance()
        c2.setTimeInMillis(d0.getTime() + threeDays)
        Date result = AppUtils.subtractDate(c2.time, c1.time)
        assertEquals(threeDays, result.time)

    }

}