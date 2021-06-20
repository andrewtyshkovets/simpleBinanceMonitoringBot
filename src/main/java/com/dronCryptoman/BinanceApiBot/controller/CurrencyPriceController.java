package com.dronCryptoman.BinanceApiBot.controller;

import com.dronCryptoman.BinanceApiBot.model.CurrencyCourse;
import com.dronCryptoman.BinanceApiBot.model.CurrencyCourseDTO;
import com.dronCryptoman.BinanceApiBot.service.CurrencyCourseService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class CurrencyPriceController {
    private final CurrencyCourseService currencyCourseService;


    public CurrencyPriceController(CurrencyCourseService currencyCourseService) {
        this.currencyCourseService = currencyCourseService;
    }

    @GetMapping("/getCurrencyCourse")
    public ResponseEntity<CurrencyCourse> getCurrencyCourse() {
        return ResponseEntity.ok(currencyCourseService.getCurrencyCourseForNow());
    }

    @GetMapping("/getStatistic")
    public ResponseEntity<List<CurrencyCourseDTO>> getStatistic() {
        return ResponseEntity.ok(currencyCourseService.getStatistic());
    }
}
