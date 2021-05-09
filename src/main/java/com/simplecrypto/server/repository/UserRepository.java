package com.simplecrypto.server.repository;

import com.simplecrypto.server.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Integer> {
    User findByEmail(String email);
}
