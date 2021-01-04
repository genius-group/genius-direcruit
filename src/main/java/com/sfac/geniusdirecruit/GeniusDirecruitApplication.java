package com.sfac.geniusdirecruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.persistence.Entity;

@SpringBootApplication
public class GeniusDirecruitApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeniusDirecruitApplication.class, args);
    }

}
