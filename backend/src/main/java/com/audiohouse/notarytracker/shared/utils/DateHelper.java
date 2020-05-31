package com.audiohouse.notarytracker.shared.utils;

import java.util.Date;

public class DateHelper {

    private static final String ISO_DATE_FORMAT = "yyyy-MM-dd'T'HH:mm:ss'Z'";

//    public static Date getISODate() {
//        SimpleDateFormat isoFormat = new SimpleDateFormat(ISO_DATE_FORMAT);
//        isoFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
//        try {
//            return isoFormat.parse(new Date().toString());
//        } catch (ParseException e) {
//            throw new ResponseStatusException(
//                    HttpStatus.INTERNAL_SERVER_ERROR, "Could not parse date: " + e.getMessage());
//        }
//    }

    public static Date getDateNow() {
        long millis = System.currentTimeMillis();
        return new java.util.Date(millis);
    }

}
