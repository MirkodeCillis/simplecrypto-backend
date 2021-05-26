package com.simplecrypto.server.utils;

import com.simplecrypto.server.domains.Cryptocurrency;
import com.simplecrypto.server.domains.HistoryWallet;
import com.simplecrypto.server.domains.HistoryCrypto;
import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.models.CurrencyAPI;
import com.simplecrypto.server.service.CryptoService;
import com.simplecrypto.server.service.HistoryCryptoService;
import com.simplecrypto.server.service.HistoryWalletService;
import com.simplecrypto.server.service.UserService;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

import java.util.Calendar;
import java.util.Date;

@Controller
public class Utils {

    @Value("${external.api.currencies}")
    String uriCurrencies;

    @Value("${days.delete.history}")
    short daysDeleteHystory;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CryptoService cryptoService;

    @Autowired
    HistoryCryptoService historyCryptoService;

    @Autowired
    UserService userService;

    @Autowired
    HistoryWalletService historyWalletService;

    @Autowired
    Logger logger;

    public void updateValues() {

        RestTemplate restTemplate = new RestTemplate();

        Iterable<Cryptocurrency> currencies = cryptoService.findAll();

        logger.info("Getting Cryptocurrencies updates...");
        currencies.forEach(currency -> {
            if (currency.getCodice().equalsIgnoreCase("EUR")) return;
            CurrencyAPI currentValue = restTemplate.getForObject(uriCurrencies + currency.getCodice(), CurrencyAPI.class);

            // update values in cryptocurrency table
            currency.setValore(currentValue.getPrice());
            cryptoService.save(currency);

            // add entries in history_crypto
            HistoryCrypto hc = new HistoryCrypto(currency, currency.getValore());
            historyCryptoService.save(hc);
        });
        logger.info("Done.");

        Iterable<User> users = userService.findAll();

        logger.info("Updating Users wallets...");
        users.forEach(user -> {
            final float[] current_wallet = {0};
            user.getInvestments().forEach(investment -> {
                current_wallet[0] += investment.getImporto() * investment.getCryptocurrency().getValore();
            });
            HistoryWallet hw = new HistoryWallet(user, current_wallet[0]);
            historyWalletService.save(hw);
        });
        logger.info("Done.");
    }

    public void deleteOldValues() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -daysDeleteHystory);
        Date date = new Date(cal.getTimeInMillis());

        logger.info("Deleting cryptocurrencies values older than " + daysDeleteHystory + " days...");
        Integer rows = historyCryptoService.deleteOlderThan(date);
        logger.info("Deleted " + rows + " values from hystory crypto.");

        logger.info("Deleting wallet values older than " + daysDeleteHystory + " days...");
        rows = historyWalletService.deleteOlderThan(date);
        logger.info("Deleted " + rows + " values from hystory wallet.");

    }
}
