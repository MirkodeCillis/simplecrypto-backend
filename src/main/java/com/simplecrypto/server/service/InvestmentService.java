package com.simplecrypto.server.service;

import com.simplecrypto.server.domains.Cryptocurrency;
import com.simplecrypto.server.domains.Investment;
import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("InvestmentService")
public class InvestmentService {

    @Autowired
    InvestmentRepository investmentRepository;

    public Investment save(Investment investment) {
        return investmentRepository.save(investment);
    }

    public Investment findByCryptoAndUser(Cryptocurrency cryptocurrency, User user) {
        return investmentRepository.findByCryptocurrencyAndUser(cryptocurrency, user);
    }
}
