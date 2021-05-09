package com.simplecrypto.server.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.Date;

@Entity
public class HystoryCrypto {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "crypto_id")
    private Cryptocurrency cryptocurrency;

    @NotEmpty
    private Float valore;

    @JsonFormat(pattern = "dd-MM-yyyy", timezone = "CET")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", insertable = true, updatable = false)
    private Date date;

    public HystoryCrypto(Cryptocurrency cryptocurrency, Float valore) {
        this.cryptocurrency = cryptocurrency;
        this.valore = valore;
    }

    public HystoryCrypto() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cryptocurrency getCryptocurrency() {
        return cryptocurrency;
    }

    public void setCryptocurrency(Cryptocurrency cryptocurrency) {
        this.cryptocurrency = cryptocurrency;
    }

    public Float getValore() {
        return valore;
    }

    public void setValore(Float valore) {
        this.valore = valore;
    }
}
