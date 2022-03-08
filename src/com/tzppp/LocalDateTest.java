package com.tzppp;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class LocalDateTest {
    public static void main(String[] args) {
        LocalDateTime localDateTime = LocalDateTime.now();
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String format = dateTimeFormatter.format(localDateTime.plusDays(7));
        String format2 = dateTimeFormatter.format(localDateTime.plusDays(15));
        String format3 = dateTimeFormatter.format(localDateTime.plusDays(30));
        System.out.println(format);
        System.out.println(format2);
        System.out.println(format3);
    }
}