package com.dronCryptoman.BinanceApiBot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CurrencyCourseDTO {

    private String symbol;
    private Double price;
    private Timestamp currencyPriceDateTime;
}
