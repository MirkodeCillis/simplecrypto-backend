package com.simplecrypto.server.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;

@Entity
public class HistoryWallet {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty
    private Float valore;

    @NotEmpty
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm:ss", timezone = "CET")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "date", insertable = true, updatable = false)
    private Date date;

    public HistoryWallet(User user, Float valore) {
        this.user = user;
        this.valore = valore;
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
}
