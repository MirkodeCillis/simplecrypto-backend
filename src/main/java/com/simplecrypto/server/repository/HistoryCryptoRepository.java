package com.simplecrypto.server.repository;

import com.simplecrypto.server.domains.HistoryCrypto;
import org.springframework.data.repository.CrudRepository;
import java.util.Date;

public interface HistoryCryptoRepository extends CrudRepository<HistoryCrypto, Integer> {

    int deleteAllByDateLessThan(Date date);

}
