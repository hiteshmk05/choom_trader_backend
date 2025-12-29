package com.bittraderbackend.bittrader.services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

@Slf4j
@Service
public class MarketDataService {

    private final RestClient restClient;
    private final String apiKey;
    private final String baseUrl;

    public MarketDataService(@Value("${finnhub.base.url}") String baseUrl,
                             @Value("${finnhub.api.key}") String apiKey,
                             RestClient.Builder builder) {
        this.restClient = builder.baseUrl(baseUrl).build();
        this.apiKey = apiKey;
        this.baseUrl = baseUrl;
    }

    public Double getPrice(String symbol) {
        try {
            // Calls: https://finnhub.io/api/v1/quote?symbol=AAPL&token=XYZ
            FinnhubResponse response = restClient.get()
                    .uri(uriBuilder -> uriBuilder
                            .path("/quote")
                            .queryParam("symbol", symbol)
                            .queryParam("token", apiKey)
                            .build())
                    .retrieve()
                    .body(FinnhubResponse.class);

            return response != null ? response.c() : null;
        } catch (Exception e) {
            System.err.println("Error fetching price: " + e.getMessage());
            return null;
        }
    }

    /**
     * Helper record to map JSON response.
     * Finnhub format: {"c": 150.00, "d": 2.1, ...}
     */
    record FinnhubResponse(Double c) {}

}


