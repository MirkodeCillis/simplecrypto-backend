package com.simplecrypto.server.domains;

import javax.persistence.*;

@Entity
public class Cryptocurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 30, nullable = false)
    private String nome;

    @Column(length = 5, nullable = false)
    private String codice;

    @Column(name = "value", nullable = false)
    private Float valore;

    public Cryptocurrency(String nome, String codice, Float valore) {
        this.nome = nome;
        this.codice = codice;
        this.valore = valore;
    }

    public Cryptocurrency() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCodice() {
        return codice;
    }

    public void setCodice(String codice) {
        this.codice = codice;
    }

    public Float getValore() {
        return valore;
    }

    public void setValore(Float valore) {
        this.valore = valore;
    }
}
