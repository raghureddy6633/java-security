package com.security.spring_security.controller;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;

public class Test {

    public static void main(String[] args) {
       // String inputTimestamp = "2026-03-25 17:14:16";
        String inputTimestamp = String.valueOf(LocalDateTime.now().atOffset(ZoneOffset.UTC));

        System.out.println("Original 1: " + inputTimestamp +" time :"+LocalDateTime.now());
        // Formatter for input
//        DateTimeFormatter inputFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//
//        // Parse input string to LocalDateTime
//        LocalDateTime localDateTime = LocalDateTime.parse(inputTimestamp, inputFormatter);
//
//        // Convert to ISO 8601 format with 'Z' (UTC)
//        String iso8601Z = localDateTime.atOffset(ZoneOffset.UTC)
//                .format(DateTimeFormatter.ISO_INSTANT);
//
//        // Print result
//        System.out.println("Original: " + inputTimestamp);
//        System.out.println("ISO 8601 with Z: " + iso8601Z);
    }
}
