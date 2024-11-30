package com.home.expense.tracker.usercases.tenant;

import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Calendar;

public class DateConversionUtils {

    private DateConversionUtils() {
        // Private constructor to prevent instantiation
    }

    /**
     * Converts a LocalDate to a Calendar instance.
     *
     * @param localDate the LocalDate to convert
     * @return the corresponding Calendar instance
     */
    public static Calendar localDateToCalendar(LocalDate localDate) {
        if (localDate == null) {
            throw new IllegalArgumentException("LocalDate cannot be null");
        }

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(java.util.Date.from(localDate.atStartOfDay(ZoneId.systemDefault()).toInstant()));
        return calendar;
    }

    /**
     * Converts a Calendar instance to a LocalDate.
     *
     * @param calendar the Calendar to convert
     * @return the corresponding LocalDate
     */
    public static LocalDate calendarToLocalDate(Calendar calendar) {
        if (calendar == null) {
            throw new IllegalArgumentException("Calendar cannot be null");
        }

        return calendar.toInstant()
                .atZone(ZoneId.systemDefault())
                .toLocalDate();
    }
}

