package com.simplecrypto.server.domains;

import javax.persistence.*;
import org.hibernate.validator.constraints.NotEmpty;
import java.util.HashSet;
import java.util.Set;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty
    @Column(length = 30, nullable = false)
    private String username;

    @NotEmpty
    @Column(length = 320, unique = true, nullable = false)
    private String email;

    @NotEmpty
    @Column(length = 60, nullable = false)
    private String password;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="user", fetch = FetchType.EAGER)
    private Set<Investment> investments = new HashSet<>();

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="user", fetch = FetchType.EAGER)
    private Set<HistoryWallet> historyWallet = new HashSet<>();

    public User(String username, String email, String password) {
        this.username = username;
        this.email = email;
        this.password = password;
    }

    public User() {
        this.username = null;
        this.email = null;
        this.password = null;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Investment> getInvestments() {
        return investments;
    }

    public void setInvestments(Set<Investment> investments) {
        this.investments = investments;
    }

    public Set<HistoryWallet> getHistoryWallet() {
        return historyWallet;
    }

    public void setHistoryWallet(Set<HistoryWallet> historyWallet) {
        this.historyWallet = historyWallet;
    }
}