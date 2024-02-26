package com.example.demo.option;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;

import com.example.demo.stock.Stock;
import com.example.demo.stock.StockPriceService;

import lombok.Data;

@Data
@Entity
@Table(name = "OPTIONS")
@IdClass(OptionId.class)
public class Option {
    @Id
    private String symbol;
    @Id
    private Integer timeToMaturity;
    private Float strikePrice;
    private Float volatility;

    public Option(String symbol, Integer timeToMaturity, Float strikePrice, Float volatility) {
        this.symbol = symbol;
        this.timeToMaturity = timeToMaturity;
        this.strikePrice = strikePrice;
        this.volatility = volatility;
    }

    public Option() {
    }

    public double getUnderlyingPrice() {
        Stock stock = StockPriceService.getStockSymbol(symbol);
        return stock.getPrice();
    }

}