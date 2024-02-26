package com.example.demo.position;

import com.example.demo.stock.Stock;

public class StockPosition extends Position {
    private Stock stock;
    private int qty;

    public StockPosition(Stock stock, int qty) {
        this.stock = stock;
        this.qty = qty;
        calculateMarketValue();
        setQty(qty);
        setSymbol(stock.getSymbol());

    }

    void calculateMarketValue() {
        double marketValue = qty * stock.getPrice();
        marketValue = Math.round(marketValue * 100.0) / 100.0;
        setMarketValue(marketValue);
        setPrice(stock.getPrice());
    }
}