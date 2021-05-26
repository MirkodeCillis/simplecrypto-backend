package com.simplecrypto.server.repository;

import com.simplecrypto.server.domains.Cryptocurrency;
import com.simplecrypto.server.domains.Investment;
import com.simplecrypto.server.domains.User;
import org.springframework.data.repository.CrudRepository;

public interface InvestmentRepository extends CrudRepository<Investment, Integer> {

    Investment findByCryptocurrencyAndUser(Cryptocurrency cryptocurrency, User user);
}
