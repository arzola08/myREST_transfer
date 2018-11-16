package com.javahelps.restservice.entity;

import javax.persistence.*;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

@Entity
@Table(name = "transfers" )
public class Transfer {

    @Id
    @GeneratedValue
    @Column(name = "transfer_id", nullable = false)
    private Long transferId;

    @Column(length = 14,nullable = false) private String sourceId ;
    @Column(length = 14,nullable = false)  private String destinationId ;
    @Column(nullable = false, columnDefinition = "FLOAT(18) UNSIGNED") private float amount ;
    @Column(length = 8) private String currency = "EUR" ;
    private String date = String.valueOf (Calendar.getInstance().getTime ());

    //nice to have constraints to avoid having same account as source and dest


/*    public Transfer(String sourceId, String destinationId, float amount) {
        this.sourceId = sourceId;
        this.destinationId = destinationId;
        this.amount = amount;
    }*/

    public Long getTransferId() { return transferId; }

    public void setTransferId(Long transferId) { this.transferId = transferId; }

    public String getSourceId() {
        return sourceId;
    }

    public void setSourceId(String sourceId) {
        this.sourceId = sourceId;
    }

    public String getDestinationId() {
        return destinationId;
    }

    public void setDestinationId(String destinationId) {
        this.destinationId = destinationId;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
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
        return "Transfer done succesfully{" +
                "transferId=" + transferId +
                ", sourceId='" + sourceId + '\'' +
                ", destinationId='" + destinationId + '\'' +
                ", amount=" + amount +
                ", currency='" + currency + '\'' +
                ", date='" + date + '\'' +
                '}';
    }
}