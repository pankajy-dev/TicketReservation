package org.railway.ticketbooking.services;

import org.railway.ticketbooking.dtos.request.BookTicketDto;
import org.railway.ticketbooking.dtos.response.ReceiptResponseDto;
import org.railway.ticketbooking.exceptions.ValidationException;
import org.railway.ticketbooking.models.Seat;
import org.railway.ticketbooking.models.SectionType;
import org.railway.ticketbooking.models.Ticket;

import java.util.List;

/*
 * This interface provides method for tasks related to Tickets
 */
public interface ReservationService {

  Ticket ReservationService(BookTicketDto bookTicketDto)
      throws ValidationException;

  List<ReceiptResponseDto> deleteUserFromTrain(int userId, int trainId);

  public void updateUserSeatOnTrain(int userId, int trainId, int seatId, SectionType section,
      Integer newSeatId, int ticketId);
}
