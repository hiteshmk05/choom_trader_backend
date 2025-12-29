package com.bittraderbackend.bittrader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; // <--- Add this

@SpringBootApplication
@EnableScheduling // <--- And this
public class BitTraderApplication {

    public static void main(String[] args) {
        SpringApplication.run(BitTraderApplication.class, args);
    }

}