package org.railway.ticketbooking.dtos.response;

import org.railway.ticketbooking.models.Seat;
import org.railway.ticketbooking.models.SectionType;

import java.util.List;

public record TrainSectionSeatsDto(List<Seat> seats, UserResponseDto user, SectionType sectionType) {}
