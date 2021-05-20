package com.simplecrypto.server.domains;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Cryptocurrency {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @Column(length = 30, nullable = false, unique = true)
    private String nome;

    @Column(length = 10, nullable = false, unique = true)
    private String codice;

    @Column(name = "value")
    private Float valore;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="cryptocurrency", fetch = FetchType.LAZY)
    @OrderBy("date ASC")
    private Set<HystoryCrypto> hystory = new HashSet<>();

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

    public Set<HystoryCrypto> getHystory() {
        return hystory;
    }

    public void setHystory(Set<HystoryCrypto> hystory) {
        this.hystory = hystory;
    }
}
