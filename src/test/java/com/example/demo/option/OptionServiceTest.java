package com.example.demo.option;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

public class OptionServiceTest {
    @Mock
    private OptionRepository optionRepository;

    @InjectMocks
    private OptionService optionService;

    public OptionServiceTest() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllOptions() {
        // Arrange
        List<Option> expectedOptions = new ArrayList<>();
        expectedOptions.add(new Option("AAPL", 1, 10.5f, 0.3f));
        expectedOptions.add(new Option("GOOG", 1, 10.5f, 0.3f));

        when(optionRepository.findAll()).thenReturn(expectedOptions);

        // Act
        List<Option> actualOptions = optionService.getAllOptions();

        // Assert
        assertEquals(expectedOptions, actualOptions);
    }

    @Test
    void testGetOption() {
        // Arrange
        String symbol = "AAPL-30";
        String[] parts = symbol.split("-");
        String expectedSymbol = parts[0];
        Integer expectedTimeToMaturity = Integer.parseInt(parts[1]);
        List<Option> expectedOptions = new ArrayList<>();
        expectedOptions.add(new Option("AAPL", 1, 10.5f, 0.3f));

        when(optionRepository.findAllBySymbolAndTimeToMaturity(expectedSymbol, expectedTimeToMaturity))
                .thenReturn(expectedOptions);

        // Act
        Option actualOption = optionService.getOption(symbol);

        // Assert
        assertEquals(expectedOptions.get(0), actualOption);
    }
}