package com.devstaff.farm.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollectorApplication {

    public static final String FULL_DATE_TIME = "yyyy-MM-dd HH:mm:ss";
    public static final String DATE_CREATED = "dateCreated";
    public static final String DATE_TIME = "yyyy-MM-dd HH:mm";
    public static final String PATTERN_STRING_6 = "[\\p{L}\\p{M}\\p{Zs}]+|[a-zA-Z _/-]+('[a-zA-Z _/-])?[a-zA-Z _/-]*";

    public static void main(String[] args) {
        SpringApplication.run(CollectorApplication.class, args);
    }

}
