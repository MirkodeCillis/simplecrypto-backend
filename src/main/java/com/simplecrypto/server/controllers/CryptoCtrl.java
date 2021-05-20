package com.simplecrypto.server.controllers;

import com.simplecrypto.server.domains.Cryptocurrency;
import com.simplecrypto.server.service.CryptoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import java.util.zip.DataFormatException;

@Controller
@RequestMapping(path = "/api/crypto")
public class CryptoCtrl {

    @Autowired
    CryptoService cryptoService;

    @GetMapping(path = "/prices")
    public ResponseEntity<?> getAllPrices() {
        try {
            return new ResponseEntity<>(cryptoService.findAll(), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping(path = "/prices/{codice}")
    public ResponseEntity<?> getCryptoPrices(
            @PathVariable String codice
    ) {
        try {
            Cryptocurrency c = cryptoService.findByCodice(codice);

            if (c == null) throw new DataFormatException();

            return new ResponseEntity<>(c, HttpStatus.OK);
        } catch (DataFormatException de) {
            return new ResponseEntity<>("Code not valid", HttpStatus.NOT_FOUND);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
