package com.simplecrypto.server.utils;

import com.simplecrypto.server.domains.CurrencyAPI;
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

    public void getCurrentValues() {

        final String uri = uriCurrencies;

        RestTemplate restTemplate = new RestTemplate();

        CurrencyAPI result = restTemplate.getForObject(uri + "BTCEUR", CurrencyAPI.class);
    }
}
