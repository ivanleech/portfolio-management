package com.example.demo.position;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class PositionServiceTest {

    @Mock
    private PositionFactory positionFactory;

    @InjectMocks
    private PositionService positionService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        positionService = new PositionService(positionFactory);
    }

    @Test
    void testGetPositionFromCSV() {
        Map<String, Integer> expectedPositions = new HashMap<>();
        expectedPositions.put("Apple", 10);
        expectedPositions.put("Microsoft", 20);
        expectedPositions.put("Google", 15);

        try {
            PositionService positionServiceMock = mock(PositionService.class);
            when(positionServiceMock.getPositionFromCSV()).thenReturn(expectedPositions);

            Map<String, Integer> actualPositions = positionServiceMock.getPositionFromCSV();
            Assertions.assertEquals(expectedPositions, actualPositions);
        } catch (IOException e) {
            Assertions.fail("An exception occurred: " + e.getMessage());
        }
    }

    @Test
    void testGetPositionArr() throws IOException {
        // Arrange
        Map<String, Integer> positions = new HashMap<>();
        positions.put("Apple", 10);
        positions.put("Microsoft", 20);
        positions.put("Google", 15);

        PositionFactory positionFactoryMock = mock(PositionFactory.class);
        when(positionFactoryMock.createPosition(anyString(), anyInt())).thenReturn(new Position());

        PositionService positionServiceSpy = spy(new PositionService(positionFactory));
        doReturn(positions).when(positionServiceSpy).getPositionFromCSV();

        List<Position> actualPositionsArr = positionServiceSpy.getPositionArr();
        List<Position> expectedPositionsArr = new ArrayList<>();
        for (String k : positions.keySet()) {
            expectedPositionsArr.add(new Position());
        }

        Assertions.assertEquals(expectedPositionsArr.size(), actualPositionsArr.size());

    }
}