package com.example.demo.portfolio;

import java.io.IOException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.portfolio.Portfolio.PortfolioResponse;

@RestController
@RequestMapping("/api/portfolio")
class PortfolioController {

    private PortfolioService portfolioService;

    public PortfolioController(PortfolioService portfolioService) {
        this.portfolioService = portfolioService;
    }

    @RequestMapping("")
    public PortfolioResponse getPortfolioSummary() throws IOException {
        return portfolioService.getPortfolioResponse();
    }
}