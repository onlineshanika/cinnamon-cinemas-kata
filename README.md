# cinnamon-cinemas-kata


The "Cinnamon Cinemas Kata" project is a simple Java application that demonstrates how to create a booking system for a cinema.

### Getting Started

To run the project locally on your computer, you will need to have Java Development Kit (JDK) installed. You can download JDK from the official website at https://www.oracle.com/java/technologies/javase-downloads.html.

Once you have JDK installed, you can clone the project repository from GitHub by running the following command in your terminal or command prompt:

```
git clone https://github.com/onlineshanika/cinnamon-cinemas-kata.git
```
Next, navigate to the project directory by running the following command:
```
cd cinnamon-cinemas-kata
```
### Building and Running the Project

To build and run the project, you can use an Integrated Development Environment (IDE) such as Eclipse, IntelliJ IDEA, or NetBeans. Open the project in your IDE and run the main class CinnamonCinemasApplication.
Alternatively, you can build and run the project from the command line using the following commands:
```
./mvnw clean install 
```

### Usage
1. CSV file import: Users can import a CSV file containing seat reservation data. The application will automatically reserve the specified seats and send confirmation emails to the specified email addresses.

2. Auto seat reservation: Users can enable auto seat reservation for a specific movie, date, and time. The application will automatically reserve the best available seats for the specified showing.

3. Command line interface: Users can reserve seats via a command line interface. They can specify the movie, date, time, and number of seats they wish to reserve, and the application will reserve the best available seats and send a confirmation email.

```
Theatre,movie_name,date,time,seat_number

```
Each row represents a single seat reservation. The movie_name field should match the name of the movie in the application,
and the date and time fields should match the date and time of the showing.
The seat_number field should be the seat number (e.g. "A#3 C#4 B#2")

### Contributing
If you would like to contribute to the project, please fork the repository and create a pull request with your changes. We welcome any contributions, including bug fixes, new features, and improvements to the documentation.


### License
This project is licensed under the MIT License - see the LICENSE file for details.








