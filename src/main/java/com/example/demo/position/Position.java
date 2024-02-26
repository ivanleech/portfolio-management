package com.example.demo.position;

import lombok.Data;

@Data
public class Position {
    private double marketValue;
    private double price;
    private int qty;
    private String symbol;
}