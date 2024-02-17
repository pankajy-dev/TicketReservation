package org.railway.ticketbooking.models;

import java.util.List;

public record Ticket(int id, double totalFare, User user, ScheduledTrain schTrain, String from, String to,
    List<Seat> seat, double refundAmount, TicketStatus status) {}
