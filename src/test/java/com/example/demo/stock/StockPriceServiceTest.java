package com.example.demo.stock;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

public class StockPriceServiceTest {
    private StockPriceService stockPriceService;
    private Map<String, Stock> stocks;

    @BeforeEach
    void setUp() {
        stocks = new HashMap<>();
        stocks.put("AAPL", new Stock("AAPL", 50.0, 0.5, 0.2));
        stocks.put("TSLA", new Stock("TSLA", 100.0, 0.3, 0.15));
        stockPriceService = new StockPriceService();
        stockPriceService.initialize();
    }

    @Test
    void testGetStockSymbol() {
        Stock stock = StockPriceService.getStockSymbol("AAPL");
        Assertions.assertNotNull(stock);
        Assertions.assertEquals("AAPL", stock.getSymbol());
        Assertions.assertEquals(50.0, stock.getPrice());
    }

    @Test
    void testGetStocks() {
        Map<String, Stock> result = stockPriceService.getStocks();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(stocks, result);
    }

    @Test
    void testInitialize() {
        stockPriceService.initialize();
        Map<String, Stock> result = stockPriceService.getStocks();
        Assertions.assertNotNull(result);
    }

    @Test
    void testUpdateStockPrices() {
        stockPriceService.updateStockPrices(0);
        Map<String, Stock> result = stockPriceService.getStocks();
        Assertions.assertNotNull(result);
        Assertions.assertEquals(stocks, result);
    }
}