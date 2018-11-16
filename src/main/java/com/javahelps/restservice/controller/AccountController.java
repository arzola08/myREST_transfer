package com.javahelps.restservice.controller;

import com.javahelps.restservice.entity.Account;
import com.javahelps.restservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;

@RestController
@RequestMapping(path = "/accounts")
public class AccountController {

    @Autowired
    private AccountRepository repository;

    @GetMapping
    public Iterable<Account> findAll() { //getting all accounts
        return repository.findAll();
    }

    @GetMapping(path = "/{accountId}") //getting account given an accountId
    public Account find(@PathVariable("accountId") String accountId) {
        return repository.findOne(accountId);
    }

    @PostMapping(consumes = "application/json")  //creating a new account
    public Account create(@RequestBody Account account) {
        return repository.save(account);
    }

    @DeleteMapping(path = "/{accountId}") //deleting an account given an accountId
    public void delete(@PathVariable("accountId") String accountId) {
        repository.delete(accountId);
    }

    @PutMapping(consumes = "application/json")  //updating balance
    public Account update( @RequestBody Account account) throws AccountNotFoundException {

        if (repository.exists(account.getAccountId ())) {
            account.setBalance (account.getBalance ());
            return repository.save(account);
        } else {
            throw new AccountNotFoundException ();
        }
    }

}