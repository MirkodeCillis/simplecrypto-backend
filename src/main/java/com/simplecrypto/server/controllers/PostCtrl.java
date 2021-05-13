package com.simplecrypto.server.controllers;

import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.exception.UserNotFoundException;
import com.simplecrypto.server.models.PostModel;
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
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping(path = "/api/post")
public class PostCtrl {

    @Autowired
    UserService userService;

    @Autowired
    PostService postService;

    @PostMapping(path = "/submit")
    public @ResponseBody ResponseEntity<?> submitPost(
            @RequestBody PostModel post
    ) {
        try {
            User user = userService.findById(post.getUserId()).get();
            if (user == null) throw new UserNotFoundException("User not found.");

            if (post.getMessage().length() > 255) throw new DataIntegrityViolationException("message too long");

            postService.save(post);
            return new ResponseEntity<>("Success", HttpStatus.OK);
        } catch (UserNotFoundException | DataIntegrityViolationException e) {
            return new ResponseEntity<>("User not found", HttpStatus.BAD_REQUEST);
        }
    }

}
