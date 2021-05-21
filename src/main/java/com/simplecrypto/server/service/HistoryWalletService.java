package com.simplecrypto.server.service;

import com.simplecrypto.server.domains.HistoryWallet;
import com.simplecrypto.server.repository.HistoryWalletRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Transactional
@Service("HistoryWalletService")
public class HistoryWalletService {

    @Autowired
    HistoryWalletRepository historyWalletRepository;

    public HistoryWallet save(HistoryWallet historyWallet) {
        return historyWalletRepository.save(historyWallet);
    }

    public Integer deleteOlderThan(Date date) {
        return historyWalletRepository.deleteAllByDateLessThan(date);
    }
}
