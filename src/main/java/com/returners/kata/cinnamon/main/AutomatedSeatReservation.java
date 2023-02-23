package com.returners.kata.cinnamon.main;

import com.returners.kata.cinnamon.*;
import com.returners.kata.cinnamon.util.PaymentMethod;

import java.util.List;
import java.util.Random;

public class AutomatedSeatReservation {

    static MovieTheater movieTheater;
    static String theatreName = "Cinema City";
    static String showTime = "14:00";
    static String movie = "Ant-Man and the Wasp";
    static int noOfRows = 5;
    static char noOfCols = 'C';

    static Theatre cinemaCity = null;

    public static void main(String[] args) {
        System.out.println("------ Starting the automation ------");
        masterdataCreation();
        automatedSeatAllocation();
    }


    private static void masterdataCreation() {
        // setting up masterdata
        //Create MovieTheater
        movieTheater = MovieTheater.getInstance();
        movieTheater.setName("Empire Cinemas - Bishops Stortford");

        //Create Theatre
        cinemaCity = new Theatre(theatreName);

        //Create Movies
        Movie movie1 = new Movie(movie, " Adventure/Action ", 3,
                125, "Ant-Man and the Wasp find themselves exploring the Quantum Realm," +
                " interacting with strange new creatures and embarking on an adventure " +
                "that pushes them beyond the limits of what they thought was possible.");

        // create showtimes and add movies
        if (movieTheater.addTheater(cinemaCity)) {

            Showtime showtime = new Showtime();
            showtime.setMovie(movie1);
            showtime.setStart_time("14:00");
            showtime.setEnd_time("16:00");
            cinemaCity.addShowTimes(showtime);
            cinemaCity.createSeats(noOfRows, noOfCols, showTime);

        }
    }


    private static void automatedSeatAllocation() {
        Random ran = new Random();
        String[] seats = getNoOfTickets(ran);
        while (seats != null) {
            Customer customer = new Customer();
            Booking booking = customer.bookSeats(movie, showTime, theatreName, seats);
            Payment payment = new Payment();
            Payment payment1 = payment.makePayment(booking, PaymentMethod.CARD);

            TransactionManager txnManager = new TransactionManager();
            System.out.println(" Transaction Id : " +txnManager.getTransactions(payment1.getTransaction().getTransactionId()).getTransactionId() +" no of seats allocated " +seats.length);

            seats = getNoOfTickets(ran);
        }

        System.out.println("All Seats are allocated ! " );

    }

    private static String[] getNoOfTickets(Random ran) {
        int bound = 3;

        int noOfSeats = ran.nextInt(bound) + 1;
        List<Seat> seats = cinemaCity.getAllAvailableSeatsByShowtime(showTime);

        if (seats != null && !seats.isEmpty()) {
            if (seats.size() > noOfSeats) {
                List<Seat> seatSubList = seats.subList(0, noOfSeats);
                return getSeats(noOfSeats, seatSubList);
            } else {
                return getSeats(seats.size(), seats);
            }

        }
        return null;
    }


    private static String[] getSeats(int noOfSeats, List<Seat> seats) {
        String[] seatArray = new String[noOfSeats];
        for (int x = 0; x < noOfSeats; x++) {
            Seat seat = seats.get(x);
            seatArray[x] = seat.getRow() + "#" + seat.getNumber();
        }
        return seatArray;
    }
}
