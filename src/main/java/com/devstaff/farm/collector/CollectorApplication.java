package com.devstaff.farm.collector;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.info.License;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@OpenAPIDefinition(
        info = @Info(title = "Farm-Collector API Interview Demo. An Argicultural Innovative Product.", version = "0.0.1",
                contact = @Contact(name = "Davies Tobi Alex",
                        email = "daviestobialex@gmail.com",
                        url = "https://daviestobialex.gitlab.io/website/"),
                license = @License(name = "Apache 2.0",
                        url = "https://www.apache.org/licenses/LICENSE-2.0"),
                description = "Farm-Collector is an agricultural data collection system designed to track and report on the planting and harvesting activities of farms. The system captures detailed information about the types of crops planted, the expected yield, and the actual yield upon harvesting. This data is essential for analyzing farm productivity, optimizing crop selection, and improving agricultural practices."))
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
