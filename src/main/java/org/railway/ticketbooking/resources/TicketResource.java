package org.railway.ticketbooking.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.railway.ticketbooking.dtos.request.BookTicketDto;
import org.railway.ticketbooking.dtos.response.ReceiptResponseDto;
import org.railway.ticketbooking.exceptions.ValidationException;
import org.railway.ticketbooking.repositories.ScheduledTrainRepository;
import org.railway.ticketbooking.repositories.TicketRepository;
import org.railway.ticketbooking.repositories.UserRepository;
import org.railway.ticketbooking.services.ReservationServiceImpl;
import org.railway.ticketbooking.services.TicketBookingService;

@Path("/ticket/")
public class TicketResource {

  @POST
  @Path("/reservation/")
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response bookTicket(BookTicketDto bookTicketDto) {
    UserRepository userRepo = new UserRepository();
    ScheduledTrainRepository schTrainRepo = new ScheduledTrainRepository();
    TicketRepository ticketRepo = new TicketRepository();
    TicketBookingService ticketBookingService = new TicketBookingService();
    try {
      ReceiptResponseDto responseDto = ticketBookingService.initiateBooking(bookTicketDto);
      if (responseDto != null) {
        return Response.status(Response.Status.CREATED).entity(responseDto).build();
      }
    }
    catch (ValidationException e) {
      return Response.status(Response.Status.OK)
          .entity("Validation failed. Reservation could not be completed.").build();
    }
    return Response.status(Response.Status.OK).entity("Reservation could not be completed.").build();
  }
}
