package com.simplecrypto.server.service;

import com.simplecrypto.server.domains.HystoryCrypto;
import com.simplecrypto.server.repository.HystoryCryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("HystoryCryptoService")
public class HystoryCryptoService {

    @Autowired
    HystoryCryptoRepository hystoryCryptoRepository;

    public HystoryCrypto save(HystoryCrypto hystoryCrypto) {
        return hystoryCryptoRepository.save(hystoryCrypto);
    }
}
