package com.example.demo.stock;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

@Service
public class StockPriceService {

    private final static Map<String, Stock> stocks = new HashMap<>();

    @PostConstruct
    public void initialize() {
        stocks.put("AAPL", new Stock("AAPL", 50.0, 0.5, 0.2));
        stocks.put("TSLA", new Stock("TSLA", 100.0, 0.3, 0.15));

        new Thread(() -> {
            scheduleUpdateTask();
        }).start();
    }

    @Async
    private void scheduleUpdateTask() {
        Random random = new Random();
        while (true) {
            try {
                int randomDelayMillis = (int) (500 + (random.nextDouble() * 1500));
                Thread.sleep(randomDelayMillis);
                double randomDelaySeconds = randomDelayMillis / 1000.00;
                // System.out.println("Working... (Delay: " + randomDelaySeconds + "s)");
                updateStockPrices(randomDelaySeconds);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                System.err.println("Thread interrupted: " + e.getMessage());
            }
        }
    }

    public void updateStockPrices(double randomDelaySeconds) {
        for (String stockName : stocks.keySet()) {
            Stock stock = stocks.get(stockName);
            stock.simulateNextPrice(randomDelaySeconds);
        }
    }

    public Map<String, Stock> getStocks() {
        return stocks;
    }

    public static Stock getStockSymbol(String symbol) {
        return stocks.get(symbol);
    }

}
