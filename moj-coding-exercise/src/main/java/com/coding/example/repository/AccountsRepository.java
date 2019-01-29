package com.coding.example.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.coding.example.model.Account;

@Repository
public interface AccountsRepository extends CrudRepository<Account, Integer>{

}
