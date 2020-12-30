package me.stevensheaves.custom.utils;

import java.time.*;

public class TimeUtilities {

    /**
     * Utility method for checking if two time periods overlap.
     * Can check any <code>Comparable</code> time class.
     * If times without dates are checked, the method assumes the same date.
     * All Time parameters must be of the same type.
     *
     * @param startTime1 The time object which represents the start time of time period 1. Can be any Comparable time object
     * @param endTime1 The time object which represents the end time of time period 1. Can be any Comparable time object
     * @param startTime2 The time object which represents the start time of time period 2. Can be any Comparable time object
     * @param endTime2 The time object which represents the end time of time period 2. Can be any Comparable time object
     * @param canBeAdjacent boolean value which determines whether or not a time period can end when another begins or begin when another ends. If true is passed in, method
     *                      disregards times that start at the same time another ends, or end at the same time another begins.
     * @param <T> Type of all time parameters. Must extend <code>Comparable</code>
     * @return Returns <code>true</code> if an overlap is found, and <code>false</code> if there is no overlap found.
     */
    public <T extends Comparable<T> > boolean checkForOverlap(T startTime1, T endTime1, T startTime2, T endTime2, boolean canBeAdjacent) {
        if ((startTime2.compareTo(startTime1) > 0) && (startTime2.compareTo(endTime1) < 0)) return true;
        if ((endTime2.compareTo(startTime1) > 0) && (endTime2.compareTo(endTime1) < 0)) return true;
        if ((startTime2.compareTo(startTime1) > 0) && (endTime2.compareTo(endTime1) < 0)) return true;
        if ((startTime2.compareTo(endTime1) < 0) && (endTime2.compareTo(endTime1) > 0 )) return true;
        if (startTime2.equals(startTime1)) return true;
        if (endTime2.equals(endTime1)) return true;
        if(!canBeAdjacent) {
            if (startTime2.equals(endTime1)) return true;
            return endTime2.equals(startTime1);
        }

        return false;
    }

    public ZonedDateTime ZonedDateTimeBuilder(LocalDate datePart, String hour, String minute) {
        LocalDate datePartBuilt = datePart;
        LocalTime timePartBuilt = LocalTime.parse(hour + ":" + minute);
        LocalDateTime localDateTime = LocalDateTime.of(datePartBuilt, timePartBuilt);
        ZonedDateTime zonedDateTime = localDateTime.atZone(ZoneId.systemDefault());
        return zonedDateTime;
    }
}