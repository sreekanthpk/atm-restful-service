package com.bank.teller.rest.controller;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.bank.teller.model.Account;
import com.bank.teller.service.AuthenticationService;
import com.bank.teller.service.TellerService;

@Controller
@RequestMapping("/teller")
public class TellerServiceController {

    @Autowired
    TellerService tellerService;

    @Autowired
    AuthenticationService authenticationService;

    Map<Long, String> authDetails = new HashMap<Long, String>();

    @RequestMapping(value = "/gettoken", method = RequestMethod.POST, consumes=MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String authorize(@RequestBody @Valid Account account) {
        return authenticationService.generateToken(account);
    }

    @RequestMapping("/withdraw/{token}/{accountnumber}/{amount}")
    public @ResponseBody String withdraw(@PathVariable(value = "token") String token, @PathVariable(value = "accountnumber") long accountNumber, @PathVariable(value = "amount") int amount) {
        try {
            if (authenticationService.verifyToken(accountNumber, token)) {
                if (tellerService.withdraw(accountNumber, amount)) {
                    return "Success";
                } else {
                    return "Error occured while updating your account";
                }
            } else {
                return "Invalid Token";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occured while updating your account";
        }
    }

    @RequestMapping("/checkbalance/{token}/{accountnumber}")
    public @ResponseBody double checkBalance(@PathVariable(value = "token") String token, @PathVariable(value = "accountnumber") long accountNumber) {
        try {
            if (authenticationService.verifyToken(accountNumber, token)) {
                return tellerService.checkBalance(accountNumber);
            } else {
                return 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
            return 0.0;
        }
    }

    @RequestMapping("/deposit/{token}/{accountnumber}/{amount}")
    public @ResponseBody String deposit(@PathVariable(value = "token") String token, @PathVariable(value = "accountnumber") long accountNumber, @PathVariable(value = "amount") int amount) {
        try {
            if (authenticationService.verifyToken(accountNumber, token)) {
                if (tellerService.deposit(accountNumber, amount)) {
                    return "Success";
                } else {
                    return "Error occured while updating your account";
                }
            } else {
                return "Invalid Token";
            }
        } catch (Exception e) {
            e.printStackTrace();
            return "Error occured while updating your account";
        }
    }

}
