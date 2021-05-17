package com.simplecrypto.server.domains;

import com.fasterxml.jackson.annotation.JsonFormat;
import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @NotEmpty
    @Column(nullable = false)
    private String message;

    @JsonFormat(pattern = "dd/MM/yyyy HH:mm", timezone = "CET")
    @CreatedDate
    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "published_at", nullable = false, updatable = false)
    private Date publishedAt;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="post", fetch = FetchType.LAZY)
    private Set<Comment> comments = new HashSet<>();

    public Post(User user, String message) {
        this.user = user;
        this.message = message;
        this.publishedAt = new Date();
    }

    public Post() {

    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Date getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Date publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Set<Comment> getComments() {
        return comments;
    }

    public void setComments(Set<Comment> comments) {
        this.comments = comments;
    }
}
