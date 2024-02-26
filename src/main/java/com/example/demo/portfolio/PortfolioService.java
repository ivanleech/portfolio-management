package com.example.demo.portfolio;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.example.demo.portfolio.Portfolio.PortfolioData;
import com.example.demo.portfolio.Portfolio.PortfolioResponse;
import com.example.demo.position.Position;
import com.example.demo.position.PositionService;

@Component
public class PortfolioService {

    private PositionService positionService;

    public PortfolioService(PositionService positionService) {
        this.positionService = positionService;
    }

    public List<Position> positions = new ArrayList<>();

    public void addPosition(Position position) {
        positions.add(position);
    }

    public void addPositions() throws IOException {
        positions = new ArrayList<>();
        List<Position> positionsArr = positionService.getPositionArr();
        for (Position position : positionsArr) {
            addPosition(position);
        }
    }

    public PortfolioResponse getPortfolioResponse() throws IOException {
        addPositions();
        List<PortfolioData> portfolioDataList = new ArrayList<>();
        for (Position position : positions) {
            portfolioDataList.add(new PortfolioData(position.getSymbol(), position.getPrice(), position.getQty()));
        }
        double totalValue = portfolioDataList.stream()
                .mapToDouble(stockData -> stockData.getPrice() * stockData.getQty())
                .sum();

        return new PortfolioResponse(portfolioDataList, totalValue);
    }

}
