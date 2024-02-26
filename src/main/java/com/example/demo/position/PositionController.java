package com.example.demo.position;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.Map;
import java.util.HashMap;

@RestController
@RequestMapping("/api/position")
public class PositionController {

    private PositionService positionService;

    public PositionController(PositionService positionService, PositionFactory positionFactory) {
        this.positionService = positionService;
    }

    @RequestMapping("")
    public Map<String, Integer> getPosition() throws IOException {
        Map<String, Integer> position = positionService.getPositionFromCSV();
        System.out.println("position: " + position);
        return position;
    }

    @RequestMapping("/details")
    public HashMap<String, Position> getPositionDetails() throws IOException {
        HashMap<String, Position> positionDetails = positionService.getPositionDetails();
        System.out.println("positionDetails: " + positionDetails);
        return positionDetails;
    }

    
}
