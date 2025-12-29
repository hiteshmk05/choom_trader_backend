package com.bittraderbackend.bittrader.scheduler;

import com.bittraderbackend.bittrader.services.MarketDataService;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class MarketScheduler {

    private final MarketDataService marketDataService;

    public MarketScheduler(MarketDataService marketDataService) {
        this.marketDataService = marketDataService;
    }

    // Runs every 10 seconds (defined in application.properties)
    @Scheduled(fixedRateString = "${app.scheduler.rate}")
    public void fetchMarketData() {
        String symbol = "AAPL"; // Let's track Apple for now
        Double price = marketDataService.getPrice(symbol);

        System.out.println("--------------------------------------");
        System.out.println("Time: " + java.time.LocalTime.now());
        System.out.println("Symbol: " + symbol);
        System.out.println("Current Price: $" + price);
        System.out.println("--------------------------------------");
    }
}