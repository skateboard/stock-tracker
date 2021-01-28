package me.brennan.stocktracker.util;

import java.util.Calendar;
import java.util.TimeZone;

/**
 * @author Brennan
 * @since 1/27/2021
 **/
public class MarketUtil {
    private static Calendar CALENDAR = Calendar.getInstance();

    static {
        CALENDAR.setTimeZone(TimeZone.getTimeZone("America/New_York"));
    }

    public static boolean isOpen() {
        return CALENDAR.get(Calendar.AM_PM) == Calendar.AM && CALENDAR.get(Calendar.HOUR_OF_DAY) > 6;
    }

}
