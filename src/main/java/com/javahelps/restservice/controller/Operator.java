package com.javahelps.restservice.controller;

import com.javahelps.restservice.entity.Account;
import com.javahelps.restservice.entity.Transfer;
import com.javahelps.restservice.repository.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.InvalidTransactionException;

@Component
public class Operator {

    private static Operator operator = null;
    private Operator (){}

    @Autowired
    private AccountRepository accountRepository;

    //singleton thread safe in order to use same instance
    //https://stackoverflow.com/questions/45294345/what-is-singleton-patternwhy-and-when-should-i-use-it
    public static Operator getInstance(){
        if(operator == null){
                synchronized (Operator.class){
                        if (operator == null){
                            operator = new Operator ();
                        }
                }
        }
        return operator;
    }

    public void doTransfer (Transfer transfer) throws InvalidTransactionException, AccountNotFoundException {
/*  taking both accounts from transfer dto and taking into account good format
*  take money from source
*  deposit money on destination
* if something happens return money to source
* */
        Account source = accountRepository.findOne(transfer.getSourceId ());
        Account destination = accountRepository.findOne (transfer.getDestinationId ());

        if(source != null && destination != null ) {

                try {
                    source.withdrawing (transfer.getAmount ());
                    destination.adding (transfer.getAmount ());

                } catch (InvalidTransactionException e) {

                    source.adding (transfer.getAmount());
                    e.printStackTrace ( );
                    throw new InvalidTransactionException ();
                }

        }else{
            throw new AccountNotFoundException ();
        }
    }

}
