package com.bank.teller.repository;

import com.bank.teller.model.Account;

public interface AccountRepository {

	double getBalance(long accountNumber);

	boolean withdraw(long accountNumber, int amount);

	boolean deposit(long accountNumber, int amount);

	boolean updateAccountTrnsaction(long accountNumber, int amount);

    boolean verifyAccount(Account account);


}
