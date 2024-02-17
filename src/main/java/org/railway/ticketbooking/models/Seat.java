package org.railway.ticketbooking.models;

public class Seat {
  int seatNumber;
  boolean isAvailable;
  double price;
  Integer user;

  public Seat(int seatNumber, boolean isAvailable, double price) {
    this.seatNumber = seatNumber;
    this.isAvailable = isAvailable;
    this.price = price;
  }

  public Seat(int seatNumber, boolean isAvailable, double price, Integer user) {
    this(seatNumber, isAvailable, price);
    this.user = user;
  }

  public Integer getUser() {
    return user;
  }

  public void setUser(Integer user) {
    this.user = user;
  }

  public int getSeatNumber() {
    return seatNumber;
  }

  public void setSeatNumber(int seatNumber) {
    this.seatNumber = seatNumber;
  }

  public boolean isAvailable() {
    return isAvailable;
  }

  public void setAvailable(boolean available) {
    isAvailable = available;
  }

  public double getPrice() {
    return price;
  }

  public void setPrice(double price) {
    this.price = price;
  }
}
