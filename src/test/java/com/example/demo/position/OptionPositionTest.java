package com.example.demo.position;

import org.junit.jupiter.api.Test;

import com.example.demo.option.Option;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;

public class OptionPositionTest {
    @Test
    void testCalculateMarketValue() {
        Option spyOption = spy(new Option("TSLA", 1, 40.0f, 0.2f));
        doReturn(42.0).when(spyOption).getUnderlyingPrice();

        OptionPosition optionPosition = new OptionPosition(spyOption, 100, "C", "TSLA-1-NOV-2020-400-C");
        optionPosition.calculateMarketValue();
        assertEquals(482, optionPosition.getMarketValue(), 0.01);
        assertEquals(4.82, optionPosition.getPrice(), 0.01);
    }

    @Test
    void testCalculateTheoreticalOptionPriceCall() {
        Option spyOption = spy(new Option("TSLA", 1, 40.0f, 0.2f));
        doReturn(42.0).when(spyOption).getUnderlyingPrice();

        OptionPosition optionPosition = new OptionPosition(spyOption, 100, "C", "TSLA-1-NOV-2020-400-C");
        double theoreticalPrice = optionPosition.calculateTheoreticalOptionPrice();

        // Add assertions to verify the correctness of the calculated theoretical price
        assertEquals(4.82, theoreticalPrice, 0.01);
    }

    @Test
    void testCalculateTheoreticalOptionPricePut() {
        Option spyOption = spy(new Option("TSLA", 1, 40.0f, 0.2f));
        doReturn(42.0).when(spyOption).getUnderlyingPrice();

        OptionPosition optionPosition = new OptionPosition(spyOption, 100, "P", "TSLA-1-NOV-2020-400-C");
        double theoreticalPrice = optionPosition.calculateTheoreticalOptionPrice();

        // Add assertions to verify the correctness of the calculated theoretical price
        assertEquals(2.02, theoreticalPrice, 0.01);
    }
}