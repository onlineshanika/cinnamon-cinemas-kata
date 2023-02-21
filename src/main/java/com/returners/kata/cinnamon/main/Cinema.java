package com.returners.kata.cinnamon.main;

import com.returners.kata.cinnamon.*;
import com.returners.kata.cinnamon.util.PaymentMethod;

import java.util.List;
import java.util.Scanner;

public class Cinema {

    public static void main(String[] args) {

        // setting up masterdata
        //Create MovieTheater
        MovieTheater movieTheater = MovieTheater.getInstance();
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
            showtime.setTheater(cinemaCity);
            showtime.setStart_time("14:00");
            showtime.setEnd_time("16:00");
            cinemaCity.addShowTimes(showtime);

            Showtime showtime2 = new Showtime();
            showtime2.setMovie(movie1);
            showtime2.setTheater(cinemaCity);
            showtime2.setStart_time("16:00");
            showtime2.setEnd_time("18:00");
            cinemaCity.addShowTimes(showtime2);

            Showtime showtime3 = new Showtime();
            showtime3.setMovie(movie2);
            showtime3.setTheater(cinemaCity);
            showtime3.setStart_time("10:00");
            showtime3.setEnd_time("13:00");
            cinemaCity.addShowTimes(showtime3);
        }

        if (movieTheater.addTheater(odeon)) {

            Showtime showtime = new Showtime();
            showtime.setMovie(movie1);
            showtime.setTheater(odeon);
            showtime.setStart_time("14:00");
            showtime.setEnd_time("16:00");
            odeon.addShowTimes(showtime);

            Showtime showtime2 = new Showtime();
            showtime2.setMovie(movie1);
            showtime2.setTheater(odeon);
            showtime2.setStart_time("16:00");
            showtime2.setEnd_time("18:00");
            odeon.addShowTimes(showtime2);

            Showtime showtime3 = new Showtime();
            showtime3.setMovie(movie2);
            showtime3.setTheater(odeon);
            showtime3.setStart_time("10:00");
            showtime3.setEnd_time("13:00");
            odeon.addShowTimes(showtime3);

        }

        // create seats
        cinemaCity.createSeats(5, 'D');
        odeon.createSeats(5, 'C');


//----------------------------------------------------------------------------------------------------------------------

        Scanner scanner = new Scanner(System.in);

        String theater = selectTheatres(scanner);
        String movie = selectMovie(scanner, theater);
        String showTime = selectShowtime(scanner, theater, movie);
        String seat = selectSeats( scanner,  theater,  movie,  showTime);

//        String movie = "Ant-Man and the Wasp";
//        String showTime = "14:00";
//        String theater = "Cinema City";
        String[] seats = {seat};

        if (cinemaCity.isSeatAvailable(seat)) {
            Customer customer = new Customer();
            Booking booking = customer.bookSeats(movie, showTime, theater, seats);

            Payment payment = new Payment();
            Payment payment1 = payment.makePayment(booking, PaymentMethod.CARD);

            TransactionManager txnManager = new TransactionManager();
            System.out.println(txnManager.getTransactions(payment1.getTransaction().getTransactionId()).getTransactionId());

        } else {
            // seat unavailable
        }


        scanner.close();
    }

    private static String selectTheatres(Scanner scanner) {
        MovieTheater movieTheater = MovieTheater.getInstance();
        List<Theatre> theatres = movieTheater.getTheaters();

        System.out.println("Select a theatre from the list and enter theatre name : ");
        for (Theatre t : theatres) {
            System.out.println(t.getName());
        }
        return scanner.nextLine();
    }

    private static String selectMovie(Scanner scanner, String theatre) {
        MovieTheater movieTheater = MovieTheater.getInstance();
        List<Movie> movies = movieTheater.getMoviesForGivenTheatre(theatre);

        System.out.println("Select a movie from the list and enter movie name : ");
        for (Movie m : movies) {
            System.out.println(m.getTitle());
        }
        return scanner.nextLine();
    }


    private static String selectShowtime(Scanner scanner, String theatre, String movie) {
        MovieTheater movieTheater = MovieTheater.getInstance();
        List<Showtime> showtimes = movieTheater.getShowtimes(theatre, movie);

        System.out.println("Select a show time from the list and enter time : ");
        for (Showtime time : showtimes) {
            System.out.println(time.getStart_time());
        }
        return scanner.nextLine();
    }


    private static String selectSeats(Scanner scanner, String theatreName, String movie, String showtime) {
        MovieTheater movieTheater = MovieTheater.getInstance();
        Theatre theatre = movieTheater.getTheaterByName(theatreName);
        System.out.println("Showing all available seats ");
        for (Seat seat : theatre.getAllAvailableSeats()) {
            System.out.print (seat.getRow()+"#"+seat.getNumber()+"  ");
        }
        System.out.print("\n Select a show time from the list and enter time : ");

        return scanner.nextLine();
    }

}
