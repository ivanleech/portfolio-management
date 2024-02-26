package com.example.demo.option;

import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class OptionService {
    private final OptionRepository optionRepository;

    public OptionService(OptionRepository optionRepository) {
        this.optionRepository = optionRepository;
    }

    public List<Option> getAllOptions() {
        return optionRepository.findAll();
    }

    public Option getOption(String symbol) {
        String[] parts = symbol.split("-");
        String symbol_stripped =  parts[0];
        Integer timeToMaturity = Integer.parseInt(parts[1]);
        List<Option> options = optionRepository.findAllBySymbolAndTimeToMaturity(symbol_stripped, timeToMaturity);
        return options.isEmpty() ? null : options.get(0);
    }
}