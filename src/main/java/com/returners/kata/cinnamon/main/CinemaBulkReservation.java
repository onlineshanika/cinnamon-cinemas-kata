package com.returners.kata.cinnamon.main;

import com.returners.kata.cinnamon.*;
import com.returners.kata.cinnamon.util.PaymentMethod;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CinemaBulkReservation {


    private static final String COMMA_DELIMITER = ",";
    public static final String CSV_FILE = "src/main/resources/reservation.csv";
    static MovieTheater movieTheater = null;

    public static void main(String[] args) {

        try {
            masterdataSetup();
            List<List<String>> records = readFile();
            System.out.println(records.size());
            createReservation( records);

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private static List<List<String>> readFile() throws FileNotFoundException {
        List<List<String>> records = new ArrayList<>();
        try (Scanner scanner = new Scanner(new File(CSV_FILE));) {
            while (scanner.hasNextLine()) {
                records.add(getRecordFromLine(scanner.nextLine()));
            }
        }
        return records;
    }

    private static List<String> getRecordFromLine(String line) {
        List<String> values = new ArrayList<String>();
        try (Scanner rowScanner = new Scanner(line)) {
            rowScanner.useDelimiter(COMMA_DELIMITER);
            while (rowScanner.hasNext()) {
                values.add(rowScanner.next());
            }
        }
        return values;
    }


    private static void masterdataSetup() {
        // setting up masterdata
        //Create MovieTheater
        movieTheater = MovieTheater.getInstance();
        movieTheater.setName("Empire Cinemas - Bishops Stortford");

        //Create Theatre
        Theatre cinemaCity = new Theatre("Cinema City");
        Theatre odeon = new Theatre("Odeon");

        movieTheater.addTheater(odeon);

        //Create Movies
        Movie movie1 = new Movie("Ant-Man and the Wasp", " Adventure/Action ", 3,
                125, "Ant-Man and the Wasp find themselves exploring the Quantum Realm," +
                " interacting with strange new creatures and embarking on an adventure " +
                "that pushes them beyond the limits of what they thought was possible.");

        Movie movie2 = new Movie("Avatar: The Way of Water", " Adventure/Action ", 3.5,
                192, "Set more than a decade after the events of the first film, " +
                "'Avatar: The Way of Water' begins to tell the story of the Sully family (Jake, Neytiri, and their kids), " +
                "the trouble that follows them, the lengths they go to keep each other safe," +
                " the battles they fight to stay alive, and the tragedies they endure." +
                " Directed by James Cameron and produced by Cameron and Jon Landau, " +
                "the film stars Zoe Saldana, Sam Worthington, Sigourney Weaver, Stephen Lang, Cliff Curtis, " +
                "Joel David Moore, CCH Pounder, Edie Falco, Jemaine Clement and Kate Winslet.");

        // create showtimes and add movies
        if (movieTheater.addTheater(cinemaCity)) {

            Showtime showtime = new Showtime();
            showtime.setMovie(movie1);
//            showtime.setTheater(cinemaCity);
            showtime.setStart_time("14:00");
            showtime.setEnd_time("16:00");
            cinemaCity.addShowTimes(showtime);
            cinemaCity.createSeats(5, 'D',"14:00");


            Showtime showtime2 = new Showtime();
            showtime2.setMovie(movie1);
//            showtime2.setTheater(cinemaCity);
            showtime2.setStart_time("16:00");
            showtime2.setEnd_time("18:00");
            cinemaCity.addShowTimes(showtime2);
            cinemaCity.createSeats(5, 'D',"10:00");


            Showtime showtime3 = new Showtime();
            showtime3.setMovie(movie2);
//            showtime3.setTheater(cinemaCity);
            showtime3.setStart_time("10:00");
            showtime3.setEnd_time("13:00");
            cinemaCity.addShowTimes(showtime3);

            cinemaCity.createSeats(5, 'D',"10:00");

        }

        if (movieTheater.addTheater(odeon)) {

            Showtime showtime = new Showtime();
            showtime.setMovie(movie1);
//            showtime.setTheater(odeon);
            showtime.setStart_time("14:00");
            showtime.setEnd_time("16:00");
            odeon.addShowTimes(showtime);

            odeon.createSeats(5, 'C',"14:00");

            Showtime showtime2 = new Showtime();
            showtime2.setMovie(movie1);
//            showtime2.setTheater(odeon);
            showtime2.setStart_time("16:00");
            showtime2.setEnd_time("18:00");
            odeon.addShowTimes(showtime2);

            odeon.createSeats(5, 'C',"16:00");


            Showtime showtime3 = new Showtime();
            showtime3.setMovie(movie2);
//            showtime3.setTheater(odeon);
            showtime3.setStart_time("10:00");
            showtime3.setEnd_time("13:00");
            odeon.addShowTimes(showtime3);

            odeon.createSeats(5, 'C',"10:00");

        }

        // create seats

    }


    private static void createReservation(List<List<String>> records) {
        for (List<String> record : records) {

            String theaterName =record.get(0).trim();
            String movie = record.get(1).trim();
            String showTime = record.get(2).trim();

            String[] seats = record.get(3).split(" ");
            Customer customer = new Customer();
            Booking booking = customer.bookSeats(movie, showTime, theaterName, seats);

            Payment payment = new Payment();
            Payment payment1 = payment.makePayment(booking, PaymentMethod.CARD);

            TransactionManager txnManager = new TransactionManager();
            System.out.println(txnManager.getTransactions(payment1.getTransaction().getTransactionId()).getTransactionId());

        }
    }
}
