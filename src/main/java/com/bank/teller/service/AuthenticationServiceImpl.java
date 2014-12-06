package com.bank.teller.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.bank.teller.model.Account;
import com.bank.teller.repository.AccountRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private AccountRepository accountRepository;

    @Value("${ignoreAuth}")
    private boolean ignoreAuth;

    Map<Long, String> authDetails = new HashMap<Long, String>();

    public String generateToken(Account account) {
        boolean result = accountRepository.verifyAccount(account);
        if (result == true) {
            String token = UUID.randomUUID().toString();
            authDetails.put(new Long(account.getAccountNumber()), token);
            return token;
        } else {
            return "Invalid Account or PIN";
        }
    }

    @Override
    public boolean verifyToken(long accountNumber, String token) {
        if(ignoreAuth){
            return true;
        }
        String existingToken = authDetails.get(new Long(accountNumber));
       
        if (existingToken!=null && existingToken.equals(token)) {
            authDetails.remove(new Long(accountNumber));
            return true;
        } else{
            return false;
        }
    }

}
