package com.bank.teller.service;

public interface TellerService {

	double checkBalance(long accountNumber);

	boolean withdraw(long accountNumber, int amount);

	boolean deposit(long accountNumber, int amount);

}
