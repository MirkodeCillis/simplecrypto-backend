package com.simplecrypto.server.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.data.annotation.CreatedDate;
import javax.persistence.*;
import java.util.Date;

@Entity
public class HistoryWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(name = "value", nullable = false)
    private Float valore;

    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "CET")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", nullable = false, updatable = false)
    private Date date;

    public HistoryWallet(User user, Float valore) {
        this.user = user;
        this.valore = valore;
        this.date = new Date();
    }

    public HistoryWallet() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Float getValore() {
        return valore;
    }

    public void setValore(Float valore) {
        this.valore = valore;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
