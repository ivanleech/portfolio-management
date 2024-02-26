package com.example.demo.position;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;
import org.springframework.beans.factory.annotation.Value;

@Service
public class PositionService {

    public PositionFactory positionFactory;

    @Value("${positions.csv}")
    private String positionsCsv;

    public PositionService(PositionFactory positionFactory ) {
        this.positionFactory = positionFactory;
    }

    public Map<String, Integer> getPositionFromCSV() throws IOException {
        Map<String, Integer> positions = new HashMap<>();
        try (CSVReader csvReader = new CSVReader(new FileReader(positionsCsv))) {
            String[] line;
            csvReader.readNext();
            while ((line = csvReader.readNext()) != null) {
                positions.put(line[0], Integer.parseInt(line[1]));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return positions;
    }

    public List<Position> getPositionArr() throws IOException{
        List<Position> positionsArr = new ArrayList<>();
        Map<String, Integer> positions = getPositionFromCSV();
        for (Map.Entry<String, Integer> entry : positions.entrySet()) {
            Position position = positionFactory.createPosition(entry.getKey(), entry.getValue());
            positionsArr.add(position);
        }
        return positionsArr;
    }

    public HashMap<String, Position> getPositionDetails() throws IOException {
        HashMap<String, Position> positionDetails = new HashMap<>();
        Map<String, Integer> positions = getPositionFromCSV();

        for (Map.Entry<String, Integer> entry : positions.entrySet()) {
            String symbol = entry.getKey();
            Integer positionSize = entry.getValue();
            Position position = positionFactory.createPosition(symbol, positionSize);
            positionDetails.put(symbol, position);
        }
        return positionDetails;
    }

}
