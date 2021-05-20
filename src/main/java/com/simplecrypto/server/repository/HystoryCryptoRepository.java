package com.simplecrypto.server.repository;

import com.simplecrypto.server.domains.HystoryCrypto;
import org.springframework.data.repository.CrudRepository;

public interface HystoryCryptoRepository extends CrudRepository<HystoryCrypto, Integer> {
}
