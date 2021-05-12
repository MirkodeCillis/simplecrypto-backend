package com.simplecrypto.server.controllers;

import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.exception.DataIncompleteException;
import com.simplecrypto.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping(path = "/api/user")
public class UserCtrl {

    @Autowired
    private UserService userService;

    @PostMapping(path = "/add")
    public @ResponseBody ResponseEntity<User> addNewUser (
            @RequestBody User user
    ) {
        try {
            if (user.getUsername() == null || user.getUsername().equals("")) {
                throw new DataIncompleteException("Data incomplete - username");
            }
            if (user.getEmail() == null || user.getEmail().equals("")) {
                throw new DataIncompleteException("Data incomplete - email");
            }

            return new ResponseEntity<>(userService.save(user), HttpStatus.OK);
        } catch (DuplicateKeyException e) {
            return new ResponseEntity<>(new User(), HttpStatus.CONFLICT);
        }  catch (DataIncompleteException e) {
            return new ResponseEntity<>(new User(), HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping(path = "/all")
    public @ResponseBody Iterable<User> getAllUsers() {
        return userService.getAll();
    }

//    @PostMapping(path = "/login")
//    public String

}
