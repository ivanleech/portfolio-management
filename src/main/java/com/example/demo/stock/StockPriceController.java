package com.example.demo.stock;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/stocks")
public class StockPriceController {

    private Map<String, Stock> stocks;

    public StockPriceController(StockPriceService stockPriceService) {
        this.stocks = stockPriceService.getStocks();
    }

    @GetMapping("/{stockName}")
    public Stock getStock(@PathVariable String stockName) {
        System.out.println("Stock" + stocks.get(stockName));
        return stocks.get(stockName);
    }


    @GetMapping("")
    public List<Stock> getStocks() {
        System.out.println("Stocks" + stocks.values());
        return new ArrayList<>(stocks.values());
    }
}
