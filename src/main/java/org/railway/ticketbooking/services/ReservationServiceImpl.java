package org.railway.ticketbooking.services;

import org.railway.ticketbooking.dtos.request.BookTicketDto;
import org.railway.ticketbooking.dtos.response.ReceiptResponseDto;
import org.railway.ticketbooking.exceptions.ValidationException;
import org.railway.ticketbooking.models.*;
import org.railway.ticketbooking.models.User;
import org.railway.ticketbooking.repositories.ScheduledTrainRepository;
import org.railway.ticketbooking.repositories.TicketRepository;
import org.railway.ticketbooking.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ReservationServiceImpl
    implements ReservationService {

  TicketRepository ticketRepo = new TicketRepository();
  ScheduledTrainRepository schTrainRepo = new ScheduledTrainRepository();
  UserRepository userRepository = new UserRepository();

  private static ReservationServiceImpl INSTANCE = new ReservationServiceImpl();

  private ReservationServiceImpl() {}

  public static ReservationServiceImpl getInstance() {
    return INSTANCE;
  }

  @Override
  public synchronized Ticket ReservationService(BookTicketDto bookTicketDto)
      throws ValidationException {
    Ticket ticket = null;
    ScheduledTrain schTrain = schTrainRepo.getTrainById(bookTicketDto.trainId());
    User user = userRepository.getUserByEmail(bookTicketDto.emaiId());

    if (!schTrain.getFrom().equalsIgnoreCase(bookTicketDto.from())
        || !schTrain.getTo().equalsIgnoreCase(bookTicketDto.to())) {
      throw new ValidationException("From or To Destination not found.");
    }
    if (!schTrain.getDate().equals(bookTicketDto.date())) {
      throw new ValidationException("No train available on Given date.");
    }
    if (schTrain != null && user != null) {
      if (schTrain.getTrain() != null && schTrain.getTrain().getSections() != null) {
        for (Section sec : schTrain.getTrain().getSections()) {
          if (sec.getSectionType() == bookTicketDto.sectionType()) {
            Map<Integer, Seat> seats = sec.getSeats();
            List<Seat> seatsAvailable = new ArrayList<>();
            double totalFare = 0;
            for (Seat seat : seats.values()) {
              if (seat.isAvailable() && seatsAvailable.size() < bookTicketDto.seat()) {
                seatsAvailable.add(seat);
                seat.setAvailable(false);
                seat.setUser(user.getId());
                totalFare += seat.getPrice();
              }
              if (seatsAvailable.size() == bookTicketDto.seat()) {
                break;
              }
            }

            if (seatsAvailable.size() == bookTicketDto.seat()) {
              ticket = ticketRepo.addTicket(seatsAvailable, totalFare, user, schTrain, bookTicketDto.from(),
                  bookTicketDto.to(), seatsAvailable, 0, TicketStatus.BOOKED);
            }
            else {
              throw new ValidationException("Seats not available.");
            }
          }
        }
      }
    }
    return ticket;
  }

  /*
   * This method deleted a user from the train.
   */
  @Override
  public synchronized List<ReceiptResponseDto> deleteUserFromTrain(int userId, int trainId) {
    AdminService adminService = new AdminService(schTrainRepo);
    List<Section> sections = adminService.getAllTrainSeats(trainId);
    List<Seat> deletedSeats = new ArrayList<>();
    List<ReceiptResponseDto> receiptRespDto = new ArrayList<>();
    for (Section sec : sections) {
      for (Map.Entry<Integer, Seat> entry : sec.getSeats().entrySet()) {
        if (entry.getValue().getUser() != null && entry.getValue().getUser() == userId) {
          entry.getValue().setAvailable(true);
          entry.getValue().setUser(null);
          deletedSeats.add(entry.getValue());
        }

        List<Ticket> userTickets = ticketRepo.getTicketByUserId(userId);
        for (Ticket tkt : userTickets) {
          if (tkt.status() == TicketStatus.BOOKED) {
            List<Seat> cancelledSeat = new ArrayList<>();
            for (Seat seat : tkt.seat()) {
              Seat canSeat = new Seat(seat.getSeatNumber(), false, seat.getPrice(), seat.getUser());
              cancelledSeat.add(canSeat);
            }
            Ticket ticket = new Ticket(tkt.id(), tkt.totalFare(), tkt.user(), tkt.schTrain(), tkt.from(),
                tkt.to(), cancelledSeat, tkt.totalFare(), TicketStatus.CANCELLED);
            ticketRepo.updateTicket(tkt.id(), ticket);
            receiptRespDto.add(TicketBookingService.getReceiptResponseDto(ticket));
          }
        }
      }
    }
    return receiptRespDto;
  }

  @Override
  public void updateUserSeatOnTrain(int userId, int trainId, int seatId, SectionType section,
      Integer newSeatId, int ticketId) {
    AdminService adminService = new AdminService(schTrainRepo);
    List<Section> sections = adminService.getAllTrainSeats(trainId);
    List<Seat> deletedSeats = new ArrayList<>();
    List<ReceiptResponseDto> receiptRespDto = new ArrayList<>();

    Seat newReservedSeat = null;
    double totalFare = 0;
    for (Section sec : sections) {
      if (sec.getSectionType() == section) {
        for (Map.Entry<Integer, Seat> entry : sec.getSeats().entrySet()) {
          if (entry.getValue().isAvailable()) {
            entry.getValue().setAvailable(false);
            entry.getValue().setUser(userId);
            Ticket tkt = ticketRepo.getTicketById(ticketId);
            if (tkt != null) {

              List<Seat> userSeats = new ArrayList<>();
              userSeats.add(entry.getValue());
              for (Seat seat : tkt.seat()) {
                if (seat.getSeatNumber() != seatId) {
                  userSeats.add(seat);
                }
              }
              Ticket ticket = new Ticket(tkt.id(), tkt.totalFare(), tkt.user(), tkt.schTrain(), tkt.from(),
                  tkt.to(), userSeats, tkt.totalFare(), TicketStatus.BOOKED_UPDATED);
              ticketRepo.updateTicket(tkt.id(), ticket);
              return;
            }
          }
        }
      }
    }
  }
}
