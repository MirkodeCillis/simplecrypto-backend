package com.simplecrypto.server.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CurrencyAPI {

    @JsonProperty("symbol")
    private String symbol;

    @JsonProperty("price")
    private Float price;

    public CurrencyAPI(String symbol, Float price) {
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

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }
}
