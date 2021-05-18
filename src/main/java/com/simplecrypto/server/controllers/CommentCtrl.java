package com.simplecrypto.server.controllers;

import com.simplecrypto.server.domains.Comment;
import com.simplecrypto.server.domains.Post;
import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.exception.UserNotFoundException;
import com.simplecrypto.server.models.CommentModel;
import com.simplecrypto.server.service.CommentService;
import com.simplecrypto.server.service.PostService;
import com.simplecrypto.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(path = "/api/comment")
public class CommentCtrl {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @Autowired
    CommentService commentService;

    @PostMapping(path = "/submit")
    public ResponseEntity<?> submitComment(
            @RequestBody CommentModel comment
    ) {
        try {
            User user = userService.findById(comment.getUserId()).get();
            if (user == null) throw new UserNotFoundException("User not found.");

            Post post = postService.getById(comment.getPostId()).get();
            if (post == null) throw new DataIntegrityViolationException("Post not found.");

            if (comment.getMessage().length() > 255) throw new DataIntegrityViolationException("message too long");

            commentService.save(comment);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (UserNotFoundException | DataIntegrityViolationException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
    }
}
