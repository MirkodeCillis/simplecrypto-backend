package com.simplecrypto.server.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyAPI {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("price")
    private Double price;


    public CurrencyAPI(String symbol, Double price) {
        this.symbol = symbol;
        this.price = price;
    }

    public CurrencyAPI() {

    }

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
