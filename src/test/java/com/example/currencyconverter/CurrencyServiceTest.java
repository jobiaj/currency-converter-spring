package com.example.currencyconverter;

import com.example.currencyconverter.service.CurrencyService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@SpringBootTest
public class CurrencyServiceTest {
    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private CurrencyService currencyService;

    @Test
    public void testConvertCurrency() {
        Map<String, Double> rates = Map.of("EUR", 0.945);
        assertEquals(94.5, currencyService.convertCurrency("USD", "EUR", 100), 0.01);
    }
}
