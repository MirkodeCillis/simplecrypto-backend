package com.simplecrypto.server.service;

import com.simplecrypto.server.domains.Cryptocurrency;
import com.simplecrypto.server.domains.Investment;
import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.models.InvestmentModel;
import com.simplecrypto.server.repository.InvestmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("InvestmentService")
public class InvestmentService {

    @Autowired
    InvestmentRepository investmentRepository;

    @Autowired
    CryptoService cryptoService;

    @Autowired
    UserService userService;

    public Investment save(Investment investment) {
        return investmentRepository.save(investment);
    }

    public Investment findByCryptoAndUser(Cryptocurrency cryptocurrency, User user) {
        return investmentRepository.findByCryptocurrencyAndUser(cryptocurrency, user);
    }

    public Float payInEuro(InvestmentModel investmentModel) {
        Cryptocurrency cryptocurrency = cryptoService.findById(investmentModel.getCrypto_id());
        User user = userService.findById(investmentModel.getUser_id());
        Cryptocurrency euro = cryptoService.findById(7);
        Investment euroDeposit = findByCryptoAndUser(euro, user);

        Float importo = investmentModel.getImporto() * cryptocurrency.getValore();
        Float remainingEuro = euroDeposit.getImporto() - importo;

        if (remainingEuro < 0)
            euroDeposit.setImporto((float) 0);
        else
            euroDeposit.setImporto(remainingEuro);
        save(euroDeposit);
        return Math.abs(remainingEuro);

    }
}
