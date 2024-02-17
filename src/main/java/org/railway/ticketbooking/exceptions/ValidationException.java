package org.railway.ticketbooking.exceptions;

public class ValidationException
    extends Exception {
  public ValidationException() {
    super();
  }

  public ValidationException(String ex) {
    super(ex);
  }
}
