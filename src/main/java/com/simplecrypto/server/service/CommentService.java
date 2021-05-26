package com.simplecrypto.server.service;

import com.simplecrypto.server.domains.Comment;
import com.simplecrypto.server.models.CommentModel;
import com.simplecrypto.server.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("CommentService")
public class CommentService {

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    public Comment save(CommentModel commentModel) {
        Comment comment = new Comment(
                userService.findById(commentModel.getUserId()),
                postService.getById(commentModel.getPostId()).get(),
                commentModel.getMessage()
        );
        return commentRepository.save(comment);
    }
}
