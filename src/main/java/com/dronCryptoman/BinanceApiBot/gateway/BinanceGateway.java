package com.dronCryptoman.BinanceApiBot.gateway;

import com.dronCryptoman.BinanceApiBot.model.CurrencyCourse;
import com.dronCryptoman.BinanceApiBot.model.SymbolEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

@Slf4j
@Component
public class BinanceGateway {

    @Value("${base-endpoint}")
    private String basicUrl;

    private static final String PRICE_TICKER = "/api/v3/ticker/price";

    public BinanceGateway() {
    }

    public HttpResponse getCurrencyPrice() throws IOException, InterruptedException {
        HttpClient client = HttpClient.newHttpClient();
        log.info("Sending request to URI " + basicUrl + PRICE_TICKER + "?symbol=" + SymbolEnum.DOGEUSDT.toString());
        HttpRequest request = HttpRequest
                .newBuilder(URI.create(basicUrl + PRICE_TICKER + "?symbol=" + SymbolEnum.DOGEUSDT.toString()))
                .GET()
                .header("accept", "application/json")
                .build();
        return client.send(request, new JsonBodyHandler(CurrencyCourse.class));
    }


}
