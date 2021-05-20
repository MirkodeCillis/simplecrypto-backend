package com.simplecrypto.server.utils;

import com.simplecrypto.server.domains.Cryptocurrency;
import com.simplecrypto.server.domains.HystoryCrypto;
import com.simplecrypto.server.models.CurrencyAPI;
import com.simplecrypto.server.service.CryptoService;
import com.simplecrypto.server.service.HystoryCryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.client.RestTemplate;

@Controller
public class Utils {

    @Value("${external.api.currencies}")
    String uriCurrencies;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    CryptoService cryptoService;

    @Autowired
    HystoryCryptoService hystoryCryptoService;

    public void getCurrentValues() {

        RestTemplate restTemplate = new RestTemplate();

        Iterable<Cryptocurrency> currencies = cryptoService.findAll();

        // update values in cryptocurrency table
        currencies.forEach(currency -> {
            CurrencyAPI currentValue = restTemplate.getForObject(uriCurrencies + currency.getCodice(), CurrencyAPI.class);

            currency.setValore(currentValue.getPrice());
            cryptoService.save(currency);

            // add entries in hystory_crypto
            HystoryCrypto hcEntry = new HystoryCrypto(currency, currency.getValore());
            hystoryCryptoService.save(hcEntry);
        });

    }
}
