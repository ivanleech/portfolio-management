package com.example.demo.position;

import com.example.demo.stock.Stock;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class StockPositionTest {
    private StockPosition stockPosition;
    private Stock stock;

    @BeforeEach
    void setUp() {
        stock = new Stock("AAPL", 50.0, 0.5, 0.2);
        stockPosition = new StockPosition(stock, 10000);
    }

    @Test
    void testCalculateMarketValue() {
        stockPosition.calculateMarketValue();
        double expectedMarketValue = 10000 * 50.0;
        expectedMarketValue = Math.round(expectedMarketValue * 100.0) / 100.0;
        Assertions.assertEquals(expectedMarketValue, stockPosition.getMarketValue());
        Assertions.assertEquals(stock.getPrice(), stockPosition.getPrice());
    }
}