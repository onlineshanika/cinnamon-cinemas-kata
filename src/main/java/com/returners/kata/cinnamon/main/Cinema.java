package com.returners.kata.cinnamon.main;

import com.returners.kata.cinnamon.*;
import com.returners.kata.cinnamon.util.PaymentMethod;

import java.util.List;
import java.util.Scanner;

public class Cinema {

    static boolean needMoreSeats = true;

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
            createShowTime(movie1,"14:00","16:00",cinemaCity);
            createShowTime(movie1,"16:00","18:00",cinemaCity);
            createShowTime(movie2,"10:00","13:00",cinemaCity);
        }

        if (movieTheater.addTheater(odeon)) {

            createShowTime(movie1,"14:00","16:00",odeon);
            createShowTime(movie1,"16:00","18:00",odeon);
            createShowTime(movie2,"10:00","13:00",odeon);

        }
        Scanner scanner = new Scanner(System.in);

        do {
            reserveTickets(scanner);
        } while (needMoreSeats);

        scanner.close();
    }

    private static void reserveTickets(Scanner scanner) {
        String theaterName = selectTheatres(scanner);
        String movie = selectMovie(scanner, theaterName);
        String showTime = selectShowtime(scanner, theaterName, movie);
        String seatStr = selectSeats(scanner, theaterName, movie, showTime);

        String[] seats = seatStr.split(" ");
        Customer customer = new Customer();
        Booking booking = customer.bookSeats(movie, showTime, theaterName, seats);

        Payment payment = new Payment();
        Payment payment1 = payment.makePayment(booking, PaymentMethod.CARD);

        TransactionManager txnManager = new TransactionManager();
        System.out.println(" Transaction Id : " +txnManager.getTransactions(payment1.getTransaction().getTransactionId()).getTransactionId() +" no of seats allocated " +seats.length);

        System.out.print(" Do you need more seats (Y/N)?");
        String moreSeats = scanner.nextLine();
        needMoreSeats = moreSeats.equalsIgnoreCase("Y");

    }


    private static String selectTheatres(Scanner scanner) {
        MovieTheater movieTheater = MovieTheater.getInstance();
        List<Theatre> theatres = movieTheater.getTheaters();

        System.out.println("Select a theatre from the list and enter theatre name : ");
        movieTheater.getTheaters().forEach( (theatre) -> {
            System.out.println(theatre.getName());
        });

        return scanner.nextLine();
    }

    private static String selectMovie(Scanner scanner, String theatre) {
        MovieTheater movieTheater = MovieTheater.getInstance();

        System.out.println("Select a movie from the list and enter movie name : ");
        movieTheater.getMoviesForGivenTheatre(theatre).forEach( (movie) -> {
            System.out.println(movie.getTitle());
        });

        return scanner.nextLine();
    }


    private static String selectShowtime(Scanner scanner, String theatre, String movie) {
        MovieTheater movieTheater = MovieTheater.getInstance();

        System.out.println("Select a show time from the list and enter time : ");
        movieTheater.getShowtimes(theatre, movie).forEach( (time) -> {
            System.out.println(time.getStart_time());
        });

        return scanner.nextLine();
    }


    private static String selectSeats(Scanner scanner, String theatreName, String movie, String showtime) {
        MovieTheater movieTheater = MovieTheater.getInstance();
        Theatre theatre = movieTheater.getTheaterByName(theatreName);
        System.out.println("Showing all available seats ");

        theatre.getAllAvailableSeatsByShowtime(showtime).forEach( (seat) -> {
            System.out.print(seat.getRow() + "#" + seat.getNumber() + "  ");
        });

        System.out.print("\n Select seats (if you need multiple seats please separate them with a space ): ");

        return scanner.nextLine();
    }

    private static void createShowTime(Movie movie,String startTime,String endTime,Theatre theatre){
        Showtime showtime = new Showtime();
        showtime.setMovie(movie);
        showtime.setStart_time(startTime);
        showtime.setEnd_time(endTime);
        theatre.addShowTimes(showtime);
        theatre.createSeats(5, 'C',startTime);

    }
}
