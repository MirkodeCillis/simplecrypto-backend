package com.simplecrypto.server.service;

import com.simplecrypto.server.domains.Cryptocurrency;
import com.simplecrypto.server.repository.CryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("CryptoService")
public class CryptoService {

    @Autowired
    CryptoRepository cryptoRepository;

    public Iterable<Cryptocurrency> findAll() {
        return cryptoRepository.findAll();
    }

    public Cryptocurrency save(Cryptocurrency currency) {
        return cryptoRepository.save(currency);
    }
}
