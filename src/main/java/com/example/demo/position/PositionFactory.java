package com.example.demo.position;

import org.springframework.stereotype.Component;

import com.example.demo.option.Option;
import com.example.demo.option.OptionService;
import com.example.demo.stock.Stock;
import com.example.demo.stock.StockPriceService;

@Component
public class PositionFactory {

    private OptionService optionService;

    public PositionFactory(OptionService optionService) {
        this.optionService = optionService;
    }

    public Position createPosition(String symbol, int qty) {
        if (symbol.endsWith("-C")) {
            return createCallOption(symbol, qty);
        } else if (symbol.endsWith("-P")) {
            return createPutOption(symbol, qty);
        } else {
            return createStock(symbol, qty);
        }
    }

    private OptionPosition createCallOption(String symbol, int qty) {
        Option option = optionService.getOption(symbol);
        return new OptionPosition(option, qty, "C", symbol);
    }

    private OptionPosition createPutOption(String symbol, int qty) {
        Option option = optionService.getOption(symbol);
        return new OptionPosition(option, qty, "P", symbol);
    }

    private StockPosition createStock(String symbol, int qty) {
        Stock stock = StockPriceService.getStockSymbol(symbol);
        return new StockPosition(stock, qty);
    }
}
