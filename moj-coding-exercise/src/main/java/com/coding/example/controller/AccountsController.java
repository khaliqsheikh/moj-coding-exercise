package com.coding.example.controller;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.coding.example.service.AccountsService;
import com.coding.example.model.Account;

@RestController
@RequestMapping("/rest/accounts/json")
public class AccountsController {

	@Autowired
	AccountsService accountsService;
	
	@GetMapping(produces = "application/json")
	public Iterable<Account> getAllAccounts() {
		return accountsService.getAllAccounts();
	}

}
