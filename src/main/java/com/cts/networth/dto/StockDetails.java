package com.cts.networth.dto;

import lombok.Builder;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Builder
public class StockDetails {
    private int stockId;
    private String stockName;
    private double stockValue;

    public StockDetails(){}

    public StockDetails(int stockId, String stockName, double stockValue) {
        this.stockId = stockId;
        this.stockName = stockName;
        this.stockValue = stockValue;
    }

    public int getStockId() {
        return stockId;
    }

    public void setStockId(int stockId) {
        this.stockId = stockId;
    }

    public String getStockName() {
        return stockName;
    }

    public void setStockName(String stockName) {
        this.stockName = stockName;
    }

    public double getStockValue() {
        return stockValue;
    }

    public void setStockValue(double stockValue) {
        this.stockValue = stockValue;
    }

}
