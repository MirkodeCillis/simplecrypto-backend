package com.simplecrypto.server.controllers;

import com.simplecrypto.server.domains.Post;
import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.exception.UserNotFoundException;
import com.simplecrypto.server.models.PostModel;
import com.simplecrypto.server.service.PostService;
import com.simplecrypto.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    @GetMapping(path = "/getlist")
    public ResponseEntity<?> getAllPosts(
            @PageableDefault(value = 15, sort = "publishedAt", direction = Sort.Direction.DESC) Pageable pageable
    ) {
        try {
            Page<Post> posts = postService.getAll(pageable);
            return new ResponseEntity<>(posts, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/get")
    public ResponseEntity<?> getPost(@RequestParam Integer id) {
        try {
            Post post = postService.getById(id).get();
            return new ResponseEntity<>(post, HttpStatus.OK);
        } catch (Exception e) {
            e.printStackTrace();
            return new ResponseEntity<>("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
