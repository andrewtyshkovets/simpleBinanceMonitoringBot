package com.dronCryptoman.BinanceApiBot.service;

import com.dronCryptoman.BinanceApiBot.gateway.BinanceGateway;
import com.dronCryptoman.BinanceApiBot.mapper.CurrencyCourseMapper;
import com.dronCryptoman.BinanceApiBot.model.CurrencyCourse;
import com.dronCryptoman.BinanceApiBot.model.CurrencyCourseDTO;
import com.dronCryptoman.BinanceApiBot.model.SymbolEnum;
import com.dronCryptoman.BinanceApiBot.repository.CurrencyCourseRepository;
import com.dronCryptoman.BinanceApiBot.util.MailSenderUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.http.HttpResponse;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class CurrencyCourseService {
    private final CurrencyCourseRepository currencyCourseRepository;
    private final BinanceGateway binanceGateway;
    private final MailSenderUtil mailSenderUtil;
    private final CurrencyCourseMapper currencyCourseMapper;

    @Value("${owner-email}")
    private String email;

    @Autowired
    public CurrencyCourseService(CurrencyCourseRepository currencyCourseRepository, BinanceGateway binanceGateway, MailSenderUtil mailSenderUtil, CurrencyCourseMapper currencyCourseMapper) {
        this.currencyCourseRepository = currencyCourseRepository;
        this.binanceGateway = binanceGateway;
        this.mailSenderUtil = mailSenderUtil;
        this.currencyCourseMapper = currencyCourseMapper;
    }

    public CurrencyCourse save(CurrencyCourse currencyCourse) {
        return currencyCourseRepository.save(currencyCourse);
    }

    public CurrencyCourse getCurrencyCourseForNow() {
        CurrencyCourse currencyCourse = new CurrencyCourse();
        try {
            HttpResponse response = binanceGateway.getCurrencyPrice();
            currencyCourse = (CurrencyCourse) response.body();
            currencyCourse = this.save(currencyCourse);
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
        log.info("Current price for " + currencyCourse.getSymbol() + " is " + currencyCourse.getPrice());
        return currencyCourse;
    }

    @Scheduled(cron = "0 0/5 * * * ?")
    private void getScheduledCourse() {
        this.monitorCourse(this.getCurrencyCourseForNow());
    }

    public void monitorCourse(CurrencyCourse currencyCourse) {
        log.info("Current price for " + currencyCourse.getSymbol() + " is " + currencyCourse.getPrice());
        if (currencyCourse.getPrice() < 0.25 || currencyCourse.getPrice() > 0.35) {
            try {
                mailSenderUtil.sendMessage(email, currencyCourse.getSymbol(), currencyCourse.getPrice());
                wait(6000000);
            } catch (IOException | MessagingException | InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public List<CurrencyCourseDTO> getStatistic() {
        return currencyCourseRepository.findAllBySymbol(SymbolEnum.DOGEUSDT.toString()).stream()
                .map(currencyCourseMapper::toCurrencyCourseDTO).collect(Collectors.toList());
    }
}
