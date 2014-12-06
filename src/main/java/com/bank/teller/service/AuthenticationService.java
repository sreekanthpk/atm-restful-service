package com.bank.teller.service;

import com.bank.teller.model.Account;

public interface AuthenticationService {

    String generateToken(Account account);

    boolean verifyToken(long accountNumber, String token);

}
