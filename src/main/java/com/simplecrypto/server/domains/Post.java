package com.simplecrypto.server.domains;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty
    private String message;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="post", fetch = FetchType.LAZY)
    private Set<LikePost> likePosts = new HashSet<>();

    public Post(User user, String message) {
        this.user = user;
        this.message = message;
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

    public Set<LikePost> getLikePosts() {
        return likePosts;
    }

    public void setLikePosts(Set<LikePost> likePosts) {
        this.likePosts = likePosts;
    }
}
