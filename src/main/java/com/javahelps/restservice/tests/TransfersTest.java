package com.javahelps.restservice.tests;

import com.javahelps.restservice.controller.AccountController;
import com.javahelps.restservice.controller.Operator;
import com.javahelps.restservice.controller.TransferController;
import com.javahelps.restservice.entity.Account;
import com.javahelps.restservice.entity.Transfer;
import com.javahelps.restservice.repository.AccountRepository;
import com.javahelps.restservice.repository.TransferRepository;
import junit.framework.TestCase;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import javax.security.auth.login.AccountNotFoundException;
import javax.transaction.InvalidTransactionException;


//@RunWith(SpringJUnit4ClassRunner.class )
@RunWith(JUnit4.class )
//@RunWith(SpringRunner.class )

@SpringBootTest
public class TransfersTest {

    @Mock
    private TransferRepository repository;
    //@Mock
    @Autowired
    private AccountRepository accountRepository;
    @Mock
    private Operator op;


    @Test
    public void basicAccountsTransfer() throws InvalidTransactionException, AccountNotFoundException {

        //-- given
        AccountController a = new AccountController ();
        TransferController t = new TransferController ();
        //  Account accB = new Account ("b",0f);
       Account accA = new Account ();
       accA.setAccountId ("a");
       accA.setBalance (80f);

       Account accB = new Account ();
       accA.setAccountId ("b");
       accA.setBalance (20f);

       // Transfer transferA = new Transfer("a","b",10f);
       Transfer transferA = new Transfer();
       transferA.setSourceId ("a");
       transferA.setDestinationId ("b");
       transferA.setAmount (20f);

        // -- when
        accountRepository.save (accA);
        accountRepository.save (accB);


         //---- then
     //   TestCase.assertEquals (accA, a.create (accA));
     //   TestCase.assertEquals (accB, a.create (accB));
          TestCase.assertEquals (toString ().contains ("success"),t.doTransfer (transferA) );
    }


    // to test concurrency properly...

}
