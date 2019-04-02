package com.example.demo.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.Locale;

public class HelperDate {

    public static Date convertDate(String date) {

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM d HH:mm:ss zz yyyy", Locale.ENGLISH);
        LocalDateTime localDateTime = LocalDateTime.parse(date, formatter);

        return java.sql.Timestamp.valueOf(localDateTime);
    }
}
