package com.example.demo.option;

import java.util.List;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/options")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @RequestMapping("")
    public List<Option> getAllOptions(){
        List<Option> options = optionService.getAllOptions();
        System.out.println("Options: " + options);
        return options;
    }
}