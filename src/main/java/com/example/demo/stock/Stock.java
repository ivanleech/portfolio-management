package com.example.demo.stock;

import java.util.Random;

import lombok.Data;

@Data
public class Stock {
    private String symbol;
    private double price;
    private double expectedReturn;
    private double annualizedStdDev;

    public Stock() {
    }

    public Stock(String symbol, double price, double expectedReturn, double annualizedStdDev) {
        this.symbol = symbol;
        this.price = price;
        this.expectedReturn = expectedReturn;
        this.annualizedStdDev = annualizedStdDev;
    }

    public void simulateNextPrice(double deltaTimeInSeconds) {
        Random random = new Random();
        double epsilon = random.nextGaussian() * 100;

        int constant = 3600; // 7257600 does not make sense
        double deltaS = price * ((expectedReturn * deltaTimeInSeconds) / constant +
                (annualizedStdDev * epsilon * Math.sqrt(deltaTimeInSeconds) / constant));
        this.price = Math.round(Math.max(0, price + deltaS) * 100.0) / 100.0;
    }
}
