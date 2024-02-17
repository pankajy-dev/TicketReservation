package org.railway.ticketbooking.dtos.request;

import jakarta.ws.rs.QueryParam;
import org.railway.ticketbooking.models.SectionType;

public record TicketUpdateDto(int userId, int trainId, int seatId, int newSeatId, SectionType sectionType,
    int ticketId) {}
