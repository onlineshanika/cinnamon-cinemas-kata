package com.returners.kata.cinnamon;

import com.returners.kata.cinnamon.util.Constants;
import com.returners.kata.cinnamon.util.PaymentMethod;
import com.returners.kata.cinnamon.util.Utilities;

public class Payment {

    private double amountPaid;
    private String paymentID;
    private PaymentMethod method;
    private int status;
    private Booking booking;
    private Transaction transaction;

    public double getAmountPaid() {
        return amountPaid;
    }

    public void setAmountPaid(double amountPaid) {
        this.amountPaid = amountPaid;
    }

    public String getPaymentID() {
        return paymentID;
    }

    public void setPaymentID(String paymentID) {
        this.paymentID = paymentID;
    }

    public PaymentMethod getMethod() {
        return method;
    }

    public void setMethod(PaymentMethod method) {
        this.method = method;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public Transaction getTransaction() {
        return transaction;
    }

    public void setTransaction(Transaction transaction) {
        this.transaction = transaction;
    }


    public Payment makePayment(Booking booking, PaymentMethod paymentMethod) {
        Payment payment = new Payment();

        TransactionManager manager = new TransactionManager();
        Transaction transaction = manager.createTransaction(booking);

        payment.setTransaction(transaction);
        payment.setBooking(booking);
        payment.setMethod(paymentMethod);
        payment.setAmountPaid(transaction.getAmount());
        payment.setStatus(transaction.getPaymentStatus());
        payment.setPaymentID(generatePaymentID());
        return payment;
    }

    private String generatePaymentID(){
        return Utilities.randomIdGenerator();
    }
}


