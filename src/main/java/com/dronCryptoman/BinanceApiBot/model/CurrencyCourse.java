package com.dronCryptoman.BinanceApiBot.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "currency_course")
@Table(name = "CURRENCY_COURSE")
public class CurrencyCourse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "CURRENCY_COURSE_ID")
    private Integer currencyCourseId;

    @Column(name = "CURRENCY_NAME")
    private String symbol;

    @Column(name = "CURRENCY_PRICE")
    private Double price;

    @Column(name = "CURRENCY_PRICE_DATE_TIME")
    @CreationTimestamp
    private Timestamp currencyPriceDateTime;
}
