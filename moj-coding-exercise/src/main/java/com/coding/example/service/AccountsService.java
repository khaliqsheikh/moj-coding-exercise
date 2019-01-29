package com.coding.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.coding.example.model.Account;
import com.coding.example.repository.AccountsRepository;

@Service
public class AccountsService {

	@Autowired
	private AccountsRepository accountsRepository;
	
	public Iterable<Account> getAllAccounts() {
		return accountsRepository.findAll();
	}

	public void addAccount(Account account) {
		accountsRepository.save(account);
	}

}
