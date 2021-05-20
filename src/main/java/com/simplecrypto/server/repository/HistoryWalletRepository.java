package com.simplecrypto.server.repository;

import com.simplecrypto.server.domains.HistoryWallet;
import org.springframework.data.repository.CrudRepository;

public interface HistoryWalletRepository extends CrudRepository<HistoryWallet, Integer> {
}
