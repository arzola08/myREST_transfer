package com.javahelps.restservice.controller;

import com.javahelps.restservice.entity.Transfer;
import com.javahelps.restservice.repository.AccountRepository;
import com.javahelps.restservice.repository.TransferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.InvalidTransactionException;

@RestController
@RequestMapping(path = "/transfer")
public class TransferController {

    @Autowired
    private TransferRepository repository;

    @Autowired
    private Operator op;

    @GetMapping
    public Iterable<Transfer> findAll() {
        return repository.findAll();
    } //getting all transfers made

    @GetMapping(path = "/{transferId}")
    public Transfer find(@PathVariable("transferId") String transferId) { // getting a transfer given transfid

        return repository.findOne(Long.valueOf (transferId));
    }

    @DeleteMapping(path = "/{transferId}")      // deleting a transfer given transfId
    public void delete(@PathVariable("transferId") String transferId) {

        repository.delete(Long.valueOf (transferId));
    }

    @PostMapping(consumes = "application/json")    // to perform transfer
    public String doTransfer(@RequestBody Transfer transfer) {

        try {
            //Operator.getInstance ().doTransfer (transfer);  // to perform transfer
            op.doTransfer (transfer);  // to perform transfer
            repository.save (transfer);      // to save record properly in transfer table
            return transfer.toString ();

        } catch (InvalidTransactionException e) {
            e.printStackTrace ( );
            return  "Exception: Overdrawing source account";

        }catch (AccountNotFoundException e){
            e.printStackTrace ( );
            return "Exception: Invalid account";
        }
    }

}
