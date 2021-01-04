package com.sfac.geniusdirecruit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import javax.persistence.Entity;

@SpringBootApplication
@EntityScan("com.sfac.geniusdirecruit.hsyetem.entity")
public class GeniusDirecruitApplication {

    public static void main(String[] args) {
        SpringApplication.run(GeniusDirecruitApplication.class, args);
    }

}
