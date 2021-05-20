package com.simplecrypto.server.service;

import com.simplecrypto.server.domains.HistoryCrypto;
import com.simplecrypto.server.repository.HistoryCryptoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("HistoryCryptoService")
public class HistoryCryptoService {

    @Autowired
    HistoryCryptoRepository historyCryptoRepository;

    public HistoryCrypto save(HistoryCrypto historyCrypto) {
        return historyCryptoRepository.save(historyCrypto);
    }
}
