package com.simplecrypto.server.domains;

import org.hibernate.validator.constraints.NotEmpty;
import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @NotEmpty
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "post_id")
    private Post post;

    @NotEmpty
    private String message;

    @OneToMany(cascade = CascadeType.REMOVE, mappedBy="comment", fetch = FetchType.LAZY)
    private Set<LikeComment> likeComments = new HashSet<>();
}
