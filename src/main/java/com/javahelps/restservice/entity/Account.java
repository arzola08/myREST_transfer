package com.javahelps.restservice.entity;

import javax.persistence.*;

import javax.transaction.InvalidTransactionException;
import java.util.Calendar;


@Entity
@Table(name = "account" )
public class Account {

    @Id @Column(length = 14) private String accountId;
    private float balance ;
    @Column(length = 8) private String currency = "EUR" ;
     //private String date = new SimpleDateFormat ("yyyy-mm-dd").format(new Date(System.currentTimeMillis()));
     private String date = String.valueOf (Calendar.getInstance().getTime ());


/*    public Account(String accountId, float balance) {
        this.accountId = accountId;
        this.balance = balance;
    }*/

    public String getAccountId() {
        return accountId;
    }

    public void setAccountId(String accountId) {
        this.accountId = accountId;
    }

    public float getBalance() {
        return balance;
    }

    public void setBalance(float balance) {
        this.balance = balance;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    @Override
    public String toString() {
        return "Account{" +
                "accountId='" + accountId + '\'' +
                ", balance=" + balance +
                ", currency='" + currency + '\'' +
                ", date='" + date + '\'' +
                '}';
    }

    public synchronized void withdrawing (float amount) throws InvalidTransactionException {
        /*if the balance after withdrawing is over 0, get it..if not, invalid transfer*/
        if (this.balance < amount) {
            throw new InvalidTransactionException ();
        }else{
            setBalance (this.balance - amount);
        }
    }

    // https://stackoverflow.com/questions/29280857/java-synchronisation-atomically-moving-money-across-account-pairs

    // getting and adding money to the account
    public synchronized void adding (float amount) {
        setBalance ( this.balance + amount);
    }




}
