package com.simplecrypto.server.service;

import com.simplecrypto.server.domains.Post;
import com.simplecrypto.server.models.PostModel;
import com.simplecrypto.server.repository.PostRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Transactional
@Service("PostService")
public class PostService {

    @Autowired
    UserService userService;

    @Autowired
    PostRepository postRepository;

    public Post save(PostModel postModel) {

        Post post = new Post(
                userService.findById(postModel.getUserId()),
                postModel.getMessage());

        return postRepository.save(post);
    }

    public Page<Post> getAll(Pageable pageable) {
        return postRepository.findAll(pageable);
    }

    public Optional<Post> getById(Integer id) {
        return postRepository.findById(id);
    }
}
