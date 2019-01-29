package com.coding.example.controller;

import static java.util.Collections.singletonMap;

import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
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

	@PostMapping
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public void createAccount(@RequestBody Account account) {
        accountsService.addAccount(account);
    }
	
	@DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public ResponseEntity<Account> deleteAccount(@PathVariable Integer id) {
 
        Optional<Account> account = accountsService.findById(id);
        if (account == null) {
            return new ResponseEntity<Account>(HttpStatus.NOT_FOUND);
        }
 
        accountsService.deleteAccount(id);
        return new ResponseEntity<Account>(HttpStatus.OK);
    }
	
}
