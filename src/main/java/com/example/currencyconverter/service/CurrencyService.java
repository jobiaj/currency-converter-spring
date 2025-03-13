package com.example.currencyconverter.service;

import com.example.currencyconverter.exception.CurrencyNotFoundException;
import com.example.currencyconverter.model.ExchangeRateResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Service
public class CurrencyService {
    private final RestTemplate restTemplate;
    private final String API_URL = "https://api.exchangeratesapi.io/latest?base=";

    public CurrencyService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public Map<String, Double> getExchangeRates(String baseCurrency) {
        String url = API_URL + baseCurrency;
        ResponseEntity<ExchangeRateResponse> response = restTemplate.getForEntity(url, ExchangeRateResponse.class);
        return response.getBody().getRates();
    }

    public double convertCurrency(String from, String to, double amount) {
        Map<String, Double> rates = getExchangeRates(from);
        if (!rates.containsKey(to)) {
            throw new CurrencyNotFoundException("Invalid currency: " + to);
        }
        return amount * rates.get(to);
    }
}
