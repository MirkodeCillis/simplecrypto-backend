package com.simplecrypto.server.models;

public class InvestmentModel {

    private Integer user_id;

    private Integer crypto_id;

    private Float importo;

    public InvestmentModel(Integer user_id, Integer crypto_id, Float importo) {
        this.user_id = user_id;
        this.crypto_id = crypto_id;
        this.importo = importo;
    }

    public Integer getUser_id() {
        return user_id;
    }

    public void setUser_id(Integer user_id) {
        this.user_id = user_id;
    }

    public Integer getCrypto_id() {
        return crypto_id;
    }

    public void setCrypto_id(Integer crypto_id) {
        this.crypto_id = crypto_id;
    }

    public Float getImporto() {
        return importo;
    }

    public void setImporto(Float importo) {
        this.importo = importo;
    }
}
