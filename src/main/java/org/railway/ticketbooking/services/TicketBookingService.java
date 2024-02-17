package org.railway.ticketbooking.services;

import org.railway.ticketbooking.dtos.request.BookTicketDto;
import org.railway.ticketbooking.dtos.response.ReceiptResponseDto;
import org.railway.ticketbooking.exceptions.ValidationException;
import org.railway.ticketbooking.models.Ticket;

public class TicketBookingService {
  ReservationService bookTicket;

  public TicketBookingService() {
    this.bookTicket = ReservationServiceImpl.getInstance();
  }

  public ReceiptResponseDto initiateBooking(BookTicketDto bookTicketDto)
      throws ValidationException {
    if (isValidateReservationDetails(bookTicketDto) && isValidPayment(bookTicketDto.payment_reference_no())) {
      Ticket ticket = bookTicket.ReservationService(bookTicketDto);
      return getReceiptResponseDto(ticket);
    }
    return null;
  }

  /*
   * This method checks if payment ref no is valid
   */
  public static boolean isValidPayment(String paymentRefNo) {
    if (paymentRefNo != null && !paymentRefNo.isBlank()) {
      return true;
    }
    return false;
  }

  public static boolean isValidateReservationDetails(BookTicketDto bookTicketDto)
      throws ValidationException {
    if (bookTicketDto != null) {
      if (bookTicketDto.emaiId() == null) {
        throw new ValidationException("User not found.");
      }
      if (bookTicketDto.from() == null) {
        throw new ValidationException("No train available on Given source location.");
      }
      if (bookTicketDto.to() == null) {
        throw new ValidationException("No train available on Given destination location.");
      }
      if (bookTicketDto.seat() == 0 || bookTicketDto.seat() > 10) {
        throw new ValidationException("Invalid seat numbers.");
      }
      if (bookTicketDto.trainId() == 0) throw new ValidationException("Train Not Found.");
      if (bookTicketDto.seat() == 0 || bookTicketDto.seat() > 10) {
        throw new ValidationException("Invalid seat numbers.");
      }
      if (bookTicketDto.sectionType() == null) {
        throw new ValidationException("Train section not passed.");
      }
    }
    else {
      throw new ValidationException("Passed Object is Null..");
    }
    return true;
  }

  public static ReceiptResponseDto getReceiptResponseDto(Ticket ticket) {
    ReceiptResponseDto receiptDto = null;
    if (ticket != null) {
      receiptDto = new ReceiptResponseDto(ticket.id(), ticket.from(), ticket.to(),
          ticket.user().getFirstName() + " " + ticket.user().getLastName(), ticket.totalFare(), ticket.seat(),
          ticket.status());
    }
    return receiptDto;
  }

}
