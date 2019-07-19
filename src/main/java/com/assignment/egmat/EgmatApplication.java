package com.assignment.egmat;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "com.assignment")
@EnableAutoConfiguration(exclude =
        {
                DataSourceAutoConfiguration.class
        })
public class EgmatApplication {

    public static void main(String[] args) {
        SpringApplication.run(EgmatApplication.class, args);
    }

}
