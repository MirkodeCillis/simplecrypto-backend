package com.simplecrypto.server.repository;

import com.simplecrypto.server.domains.HistoryCrypto;
import org.springframework.data.repository.CrudRepository;

public interface HistoryCryptoRepository extends CrudRepository<HistoryCrypto, Integer> {
}
