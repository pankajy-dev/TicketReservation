package org.railway.ticketbooking.services;

import org.junit.jupiter.api.Test;
import org.railway.ticketbooking.dtos.request.BookTicketDto;
import org.railway.ticketbooking.exceptions.ValidationException;
import org.railway.ticketbooking.models.SectionType;

import static org.junit.jupiter.api.Assertions.*;

class TicketBookingServiceTest {

  @Test
  void initiateBooking() {}

  @Test
  void testIsValidPaymentForEmptyPayReference() {
    assertFalse(TicketBookingService.isValidPayment(""));
    assertFalse(TicketBookingService.isValidPayment(null));
    assertTrue(TicketBookingService.isValidPayment("kiohgvldfhvdf"));
  }

  @Test
  void isValidateReservationDetails()
      throws ValidationException {
    BookTicketDto bookTickDto =
        new BookTicketDto("email", "London", "France", "190224", 4, 2, SectionType.A, "kjdgfvkdb");
    assertTrue(TicketBookingService.isValidateReservationDetails(bookTickDto));
  }
}