package com.simplecrypto.server.repository;

import com.simplecrypto.server.domains.Cryptocurrency;
import org.springframework.data.repository.CrudRepository;

public interface CryptoRepository extends CrudRepository<Cryptocurrency, Integer> {

    Cryptocurrency findByCodice(String codice);
}
