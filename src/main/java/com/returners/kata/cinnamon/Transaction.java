package com.returners.kata.cinnamon;

import java.util.Date;
import java.util.List;

public class Transaction {

    private String transactionId;
    private Movie movie;
    private List<Seat> seatNumbers;
    private  double amount;
    private  int paymentStatus;
    private Date paymentTimeStamp ;


    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public List<Seat> getSeatNumbers() {
        return seatNumbers;
    }

    public void setSeatNumbers(List<Seat> seatNumbers) {
        this.seatNumbers = seatNumbers;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public int getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(int paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public Date getPaymentTimeStamp() {
        return paymentTimeStamp;
    }

    public void setPaymentTimeStamp(Date paymentTimeStamp) {
        this.paymentTimeStamp = paymentTimeStamp;
    }
}
