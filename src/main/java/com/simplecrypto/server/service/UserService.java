package com.simplecrypto.server.service;

import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("TemplateService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User save(User user) {
        userRepository.save(user);
        return user;
    }

    public Iterable<User> getAll() {
        return userRepository.findAll();
    }
}
