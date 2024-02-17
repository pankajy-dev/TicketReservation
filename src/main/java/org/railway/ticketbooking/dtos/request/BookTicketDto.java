package org.railway.ticketbooking.dtos.request;

import org.railway.ticketbooking.models.SectionType;

public record BookTicketDto(String emaiId, String from, String to, String date, int seat, int trainId,
    SectionType sectionType, String payment_reference_no) {}
