package com.example.demo.position;

import org.apache.commons.math3.distribution.NormalDistribution;

import com.example.demo.option.Option;

public class OptionPosition extends Position {
    private Option option;
    private int qty;
    private String optionType;

    public OptionPosition(Option option, int qty, String optionType, String symbol) {
        this.option = option;
        this.qty = qty;
        this.optionType = optionType;
        calculateMarketValue();
        setQty(qty);
        setSymbol(symbol);
    }

    void calculateMarketValue() {
        double theoreticalPrice = calculateTheoreticalOptionPrice();
        theoreticalPrice = Math.round(theoreticalPrice * 100.00) / 100.00;
        double marketValue = qty * theoreticalPrice;
        marketValue = Math.round(marketValue * 100.00) / 100.00;
        setMarketValue(marketValue);
        setPrice(theoreticalPrice);
    }

    double calculateTheoreticalOptionPrice() {
        double S = option.getUnderlyingPrice();
        double r = 0.02;
        double d1 = (Math.log(S / option.getStrikePrice()) + (r + Math.pow(option.getVolatility(), 2) / 2) * option.getTimeToMaturity()) / (option.getVolatility() * Math.sqrt(option.getTimeToMaturity()));
        double d2 = d1 - option.getVolatility() * Math.sqrt(option.getTimeToMaturity());
        double N_d1 = cumulativeDistributionFunction(d1); 
        double N_d2 = cumulativeDistributionFunction(d2);
        if (optionType == "C") {
            return S * N_d1 - option.getStrikePrice() * Math.exp(-r * option.getTimeToMaturity()) * N_d2;
        } else {
            return option.getStrikePrice() * Math.exp(-r * option.getTimeToMaturity()) * (1 - N_d2) - S * (1 - N_d1);
        }
    }

    private double cumulativeDistributionFunction(double x) {
        NormalDistribution normalDistribution = new NormalDistribution(0, 1);
        return normalDistribution.cumulativeProbability(x);
    }

}