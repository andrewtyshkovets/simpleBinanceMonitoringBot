package com.dronCryptoman.BinanceApiBot.repository;

import com.dronCryptoman.BinanceApiBot.model.CurrencyCourse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CurrencyCourseRepository extends JpaRepository<CurrencyCourse, Integer> {

    List<CurrencyCourse> findAllBySymbol(String symbol);
}
