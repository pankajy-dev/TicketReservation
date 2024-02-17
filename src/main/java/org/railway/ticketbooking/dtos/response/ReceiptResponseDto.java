package org.railway.ticketbooking.dtos.response;

import org.railway.ticketbooking.models.Seat;
import org.railway.ticketbooking.models.Ticket;
import org.railway.ticketbooking.models.TicketStatus;

import java.util.List;

public record ReceiptResponseDto(int ticketNo, String from, String to, String user, double pricePaid,
    List<Seat> seats, TicketStatus ticketStatus) {}
