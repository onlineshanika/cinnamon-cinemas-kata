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
            createShowTime(movie1,"14:00","16:00",cinemaCity);
            createShowTime(movie1,"16:00","18:00",cinemaCity);
            createShowTime(movie1,"16:00","18:00",cinemaCity);
            createShowTime(movie2,"10:00","13:00",cinemaCity);

        }

        if (movieTheater.addTheater(odeon)) {
            createShowTime(movie1,"14:00","16:00",odeon);
            createShowTime(movie1,"16:00","18:00",odeon);
            createShowTime(movie2,"10:00","13:00",odeon);
        }

        // create seats

    }


    private static void createShowTime(Movie movie,String startTime,String endTime,Theatre theatre){
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setStart_time(startTime);
        showtime.setEnd_time(endTime);
        theatre.addShowTimes(showtime);
        theatre.createSeats(5, 'C',startTime);

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
            System.out.println(" Transaction Id : " +txnManager.getTransactions(payment1.getTransaction().getTransactionId()).getTransactionId() +" no of seats allocated " +seats.length);

        }
    }
}
