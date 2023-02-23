package com.returners.kata.cinnamon;

import com.returners.kata.cinnamon.util.Constants;
import com.returners.kata.cinnamon.util.Utilities;

import java.util.*;

public class TransactionManager {

    private static Map<String,Transaction> transactionMap = new HashMap<>();



    public Transaction createTransaction(Booking booking) {

        Transaction transaction = new Transaction();

        transaction.setSeatNumbers(booking.getSeats());
        transaction.setMovie(booking.getMovie());
        transaction.setAmount(getTotalAmount(booking.getSeats()));
        transaction.setPaymentStatus(getTransactionStatus() ? Constants.PAYMENT_SUCCESS:Constants.PAYMENT_FAIL);
        transaction.setPaymentTimeStamp(new Date());
        transaction.setTransactionId(generateTransactionID());

        transactionMap.put(transaction.getTransactionId(),transaction);

        return transaction ;

    }
    public Transaction getTransactions(String transactionId) {
        return transactionMap.get(transactionId);
    }



    private boolean getTransactionStatus(){
        return (new Random().nextInt(999999) % 5 != 0 );
    }


   /*
   this method will be modified in future
    */
    private double getTotalAmount(List<Seat> seatNumbers){
        return seatNumbers.size() * Constants.SEAT_PRICE ;
    }

    private String generateTransactionID(){
        return "TXN-"+Utilities.randomIdGenerator();
    }
}
