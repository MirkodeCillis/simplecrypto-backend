package com.simplecrypto.server.repository;

import com.simplecrypto.server.domains.HistoryWallet;
import org.springframework.data.repository.CrudRepository;

import java.util.Date;

public interface HistoryWalletRepository extends CrudRepository<HistoryWallet, Integer> {

    Integer deleteAllByDateLessThan(Date date);

}
