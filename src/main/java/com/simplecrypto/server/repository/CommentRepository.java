package com.simplecrypto.server.repository;

import com.simplecrypto.server.domains.Comment;
import org.springframework.data.repository.CrudRepository;

public interface CommentRepository extends CrudRepository<Comment, Integer> {
}
