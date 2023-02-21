package com.returners.kata.cinnamon;

import com.returners.kata.cinnamon.util.PaymentMethod;
import com.returners.kata.cinnamon.util.Statuses;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CustomerBookTicketsTest {


    static MovieTheater movieTheater;
    static Theater theater1;

    @BeforeAll
    static void setup() {
        movieTheater = MovieTheater.getInstance();
        movieTheater.setName("MovieTheater 1");
        theater1 = new Theater("Theater 3D");
        if (movieTheater.addTheater(theater1)) {
            Movie movie1 = new Movie("Ant-Man and the Wasp", " Adventure/Action ", 3,
                    125, "Ant-Man and the Wasp find themselves exploring the Quantum Realm," +
                    " interacting with strange new creatures and embarking on an adventure " +
                    "that pushes them beyond the limits of what they thought was possible.");

            Showtime showtime = new Showtime();
            showtime.setMovie(movie1);
            showtime.setTheater(theater1);
            showtime.setStart_time("14:00");
            showtime.setEnd_time("16:00");
            theater1.addShowTimes(showtime);

            Showtime showtime2 = new Showtime();
            showtime2.setMovie(movie1);
            showtime2.setTheater(theater1);
            showtime2.setStart_time("16:00");
            showtime2.setEnd_time("18:00");
            theater1.addShowTimes(showtime2);

            theater1.createSeats(5, 'C');

        }

    }

    @Test
    public void bookingSingleTicketTest() {
        Customer customer = new Customer();

        String movie = "Ant-Man and the Wasp";
        String showTime = "14:00";
        String theater = "Theater 3D";
        String[] seats = {"A#3"};

        assertEquals(Boolean.TRUE, theater1.isSeatAvailable("A#3"));

        customer.bookSeats(movie, showTime, theater, seats);
        assertEquals(Boolean.FALSE, theater1.isSeatAvailable("A#3"));
    }


    @Test
    public void bookingMultipleTicketTest() {
        Customer customer = new Customer();

        String movie = "Ant-Man and the Wasp";
        String showTime = "14:00";
        String theater = "Theater 3D";
        String[] seats = {"B#1", "B#2", "B#3", "B#4"};

        assertEquals(Boolean.TRUE, theater1.isSeatAvailable("B#3"));

        customer.bookSeats(movie, showTime, theater, seats);
        assertEquals(Boolean.FALSE, theater1.isSeatAvailable("B#4"));
        assertEquals(Boolean.FALSE, theater1.isSeatAvailable("B#2"));
    }


    @Test
    public void makePaymentTest() {

        Customer customer = new Customer();

        String movie = "Ant-Man and the Wasp";
        String showTime = "14:00";
        String theater = "Theater 3D";
        String[] seats = {"C#1", "C#2", "C#3", "C#4"};
        Booking booking = customer.bookSeats(movie, showTime, theater, seats);

        Payment payment = new Payment();
        Payment payment1 = payment.makePayment(booking, PaymentMethod.CARD);

        payment1.getPaymentID();
        TransactionManager txnManager = new TransactionManager();
//        txnManager.getTransactions(payment1.getTransaction().getTransactionId());
        assertNotNull(txnManager.getTransactions(payment1.getTransaction().getTransactionId()));

    }


}
