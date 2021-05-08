package com.simplecrypto.server.controllers;

import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping(path = "/api/user")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/add")
    public @ResponseBody User addNewUser (
            @RequestBody User user
    ) {
        return userService.save(user);
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.getAll();
    }

}
