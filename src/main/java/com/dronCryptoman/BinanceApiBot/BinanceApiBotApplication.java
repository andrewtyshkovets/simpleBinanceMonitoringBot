package com.dronCryptoman.BinanceApiBot;

import com.dronCryptoman.BinanceApiBot.service.CurrencyCourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.scheduling.annotation.EnableScheduling;

@EnableScheduling
@EnableCaching
@SpringBootApplication
public class BinanceApiBotApplication {

    @Autowired
    static CurrencyCourseService currencyCourseService;

    public static void main(String[] args) {
        SpringApplication.run(BinanceApiBotApplication.class, args);

    }
}
