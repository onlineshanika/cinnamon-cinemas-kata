package com.returners.kata.cinnamon;

public class Seat {

    private char row;
    private int number;
    private int status;

    private double seatPrice;


    public Seat(char row, int number, int status, double seatPrice) {
        this.row = row;
        this.number = number;
        this.status = status;
        this.seatPrice = seatPrice;
    }

    public double getSeatPrice() {
        return seatPrice;
    }

    public void setSeatPrice(double seatPrice) {
        this.seatPrice = seatPrice;
    }

    public char getRow() {
        return row;
    }

    public void setRow(char row) {
        this.row = row;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
