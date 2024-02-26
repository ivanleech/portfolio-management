package com.example.demo.portfolio;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import com.example.demo.option.Option;
import com.example.demo.portfolio.Portfolio.PortfolioResponse;
import com.example.demo.position.OptionPosition;
import com.example.demo.position.Position;
import com.example.demo.position.PositionService;
import com.example.demo.stock.Stock;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import com.example.demo.position.StockPosition;

public class PortfolioServiceTest {

    private PortfolioService portfolioService;

    @BeforeEach
    public void setUp() {
        portfolioService = new PortfolioService(null);
    }

    @Test
    public void testAddPosition() {
        Position position = new Position();
        portfolioService.addPosition(position);
        assertTrue(portfolioService.positions.contains(position));
    }

    @Test
    public void testAddPositions() throws IOException {
        List<Position> positionsArr = new ArrayList<>();
        Position position = new Position();
        positionsArr.add(position);
        PositionService positionService = mock(PositionService.class);
        when(positionService.getPositionArr()).thenReturn(positionsArr);
        portfolioService = new PortfolioService(positionService);
        portfolioService.addPositions();
        assertTrue(portfolioService.positions.contains(position));
    }

    @Test
    public void testGetPortfolioResponse() throws IOException {
        // Setup stock and option position
        Stock stock = new Stock("AAPL", 105, 0.3, 0.4);
        StockPosition position1 = new StockPosition(stock, 88);
        Stock stock2 = new Stock("AAPL", 106, 0.3, 0.4);
        StockPosition position2 = new StockPosition(stock2, 88);
        Option spyOption = spy(new Option("GOOG", 1, 10.5f, 0.3f));
        doReturn(10.5).when(spyOption).getUnderlyingPrice();
        OptionPosition position3 = new OptionPosition(spyOption, 5, "C", "GOOG-1-2020-C");
        List<Position> positionsArr = Arrays.asList(position1, position2, position3);

        // Form the portfolio response
        PositionService positionService = mock(PositionService.class);
        when(positionService.getPositionArr()).thenReturn(positionsArr);
        portfolioService = new PortfolioService(positionService);

        PortfolioResponse portfolioResponse = portfolioService.getPortfolioResponse();

        double expectedTotalValue = 105 * 88 + 106 * 88 + 5 * position3.getPrice();
        assertEquals(expectedTotalValue, portfolioResponse.getTotalValue(), 0.01);
    }
}