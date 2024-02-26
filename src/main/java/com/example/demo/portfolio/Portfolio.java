package com.example.demo.portfolio;

import java.util.List;

import lombok.Data;

public class Portfolio {

    @Data
    public static class PortfolioData {
        private String symbol;
        private double price;
        private int qty;
        private double value;

        public PortfolioData(String symbol, double price, int qty) {
            this.symbol = symbol;
            this.price = price;
            this.qty = qty;
            this.value = qty*price;
        }
    }

    @Data
    public static class PortfolioResponse {
        private List<PortfolioData> data;
        private double totalValue;

        public PortfolioResponse(List<PortfolioData> data, double totalValue) {
            this.data = data;
            this.totalValue = totalValue;
        }
    }
}