package com.simplecrypto.server.controllers;

import com.simplecrypto.server.domains.Cryptocurrency;
import com.simplecrypto.server.domains.Investment;
import com.simplecrypto.server.domains.User;
import com.simplecrypto.server.models.InvestmentModel;
import com.simplecrypto.server.service.CryptoService;
import com.simplecrypto.server.service.InvestmentService;
import com.simplecrypto.server.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.zip.DataFormatException;

@Controller
@RequestMapping(path = "/api/crypto")
public class CryptoCtrl {

    @Autowired
    CryptoService cryptoService;

    @Autowired
    InvestmentService investmentService;

    @Autowired
    UserService userService;

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

    @PostMapping(path = "/buy")
    public ResponseEntity<?> buyCrypto(@RequestBody InvestmentModel investmentModel) {
        try {
            if (investmentModel.getCrypto_id() == null || investmentModel.getUser_id() == null)
                return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

            Cryptocurrency cryptocurrency = cryptoService.findById(investmentModel.getCrypto_id());
            User user = userService.findById(investmentModel.getUser_id());

            Investment prevInvestment = investmentService.findByCryptoAndUser(
                    cryptocurrency,
                    user
            );

            investmentService.payInEuro(investmentModel);

            if (prevInvestment == null) {
                Investment investment = new Investment(user, cryptocurrency, investmentModel.getImporto());
                return new ResponseEntity<>(investmentService.save(investment), HttpStatus.OK);
            }

            prevInvestment.setImporto(prevInvestment.getImporto() + Math.abs(investmentModel.getImporto()));
            return new ResponseEntity<>(investmentService.save(prevInvestment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping(path = "/sell")
    public ResponseEntity<?> sellCrypto(@RequestBody InvestmentModel investmentModel) {
        try {
            if (investmentModel.getCrypto_id() == null || investmentModel.getUser_id() == null)
                return new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);

            Cryptocurrency cryptocurrency = cryptoService.findById(investmentModel.getCrypto_id());
            User user = userService.findById(investmentModel.getUser_id());

            Investment prevInvestment = investmentService.findByCryptoAndUser(
                    cryptocurrency,
                    user
            );

            if (prevInvestment == null) {
                return new ResponseEntity<>("Can't sell asset you don't own", HttpStatus.CONFLICT);
            }

            investmentModel.setImporto((float) (investmentModel.getImporto() * 0.99));

            if (prevInvestment.getImporto() < investmentModel.getImporto())
                return new ResponseEntity<>("Can't sell more asset than you own", HttpStatus.CONFLICT);

            // new value of asset quantity
            prevInvestment.setImporto(prevInvestment.getImporto() - Math.abs(investmentModel.getImporto()));

            //update in Euro

            investmentModel.setCrypto_id(7);
            investmentModel.setImporto(investmentModel.getImporto() * cryptocurrency.getValore());
            buyCrypto(investmentModel);

            return new ResponseEntity<>(investmentService.save(prevInvestment), HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity("Something went wrong", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
