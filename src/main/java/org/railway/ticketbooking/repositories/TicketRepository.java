package org.railway.ticketbooking.repositories;

import org.railway.ticketbooking.models.*;
import org.railway.ticketbooking.models.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TicketRepository {

  static HashMap<java.lang.Integer, Ticket> dbTickets = new HashMap<>();

  public Ticket addTicket(List<Seat> seatsAvailable, double totalFare, User user, ScheduledTrain schTrain,
      String from, String to, List<Seat> seat, double refundAmount, TicketStatus ticketStatus) {
    int id = TicketIdGenerator.getInstance().incrementId();
    Ticket ticket = new Ticket(id, totalFare, user, schTrain, from, to, seat, refundAmount, ticketStatus);
    dbTickets.put(id, ticket);
    return ticket;
  }

  public List<Ticket> getTicketByUserId(int userId) {
    List<Ticket> ticket = new ArrayList<>();
    ticket =
        dbTickets.entrySet().stream().filter(ticketEntry -> ticketEntry.getValue().user().getId() == userId)
            .map(Map.Entry::getValue).collect(Collectors.toList());

    return ticket;
  }

  public Ticket updateTicket(int ticketId, Ticket ticket) {
    dbTickets.put(ticketId, ticket);
    return ticket;
  }

  public Ticket getTicketById(int ticketId) {
    return dbTickets.get(ticketId);
  }
}
