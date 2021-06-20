package com.dronCryptoman.BinanceApiBot.mapper;

import com.dronCryptoman.BinanceApiBot.model.CurrencyCourse;
import com.dronCryptoman.BinanceApiBot.model.CurrencyCourseDTO;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface CurrencyCourseMapper {

    CurrencyCourseDTO toCurrencyCourseDTO(CurrencyCourse currencyCourse);
}
