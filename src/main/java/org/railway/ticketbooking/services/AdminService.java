package org.railway.ticketbooking.services;

import org.railway.ticketbooking.dtos.response.ReceiptResponseDto;
import org.railway.ticketbooking.dtos.response.TrainSectionSeatsDto;
import org.railway.ticketbooking.dtos.response.UserResponseDto;
import org.railway.ticketbooking.models.*;
import org.railway.ticketbooking.repositories.ScheduledTrainRepository;
import org.railway.ticketbooking.repositories.TicketRepository;
import org.railway.ticketbooking.repositories.TrainRepository;
import org.railway.ticketbooking.repositories.UserRepository;

import java.lang.Integer;
import java.util.*;

public class AdminService {
  UserRepository userRepo;
  TicketRepository ticketRepo;
  TrainRepository trainRepo;
  ScheduledTrainRepository schTrainRepo;

  public AdminService(UserRepository userRepo, TicketRepository ticketRepo, TrainRepository trainRepo,
      ScheduledTrainRepository schTrainRepo) {
    this.userRepo = userRepo;
    this.ticketRepo = ticketRepo;
    this.trainRepo = trainRepo;
    this.schTrainRepo = schTrainRepo;
  }

  public AdminService(ScheduledTrainRepository schTrainRepo) {
    this.schTrainRepo = schTrainRepo;
  }

  /*
   * This method gives all the seats from a scheduled train.
   */
  public List<Section> getAllTrainSeats(int trainId) {
    ScheduledTrain schTrain = schTrainRepo.getTrainById(trainId);
    if (schTrain != null && schTrain.getTrain() != null) {
      return schTrain.getTrain().getSections();
    }
    return null;
  }

  /*
   * This method gets all the seats from a given train ssection of all users.
   */
  public HashMap<Integer, TrainSectionSeatsDto> getAllTrainSeats(int trainId, SectionType sectionType) {
    List<Section> sections = getAllTrainSeats(trainId);
    // List<TrainSectionSeatsDto> trainSectionSeatsDtos;
    HashMap<Integer, TrainSectionSeatsDto> trainSectionSeatsDtos = new HashMap<>();
    for (Section sec : sections) {
      if (sec.getSectionType() == sectionType) {
        for (Map.Entry<Integer, Seat> entry : sec.getSeats().entrySet()) {
          TrainSectionSeatsDto trainSecDto = null;
          UserResponseDto userResponseDto = null;
          if (!entry.getValue().isAvailable()) {
            if (!trainSectionSeatsDtos.containsKey(entry.getValue().getUser())) {
              userResponseDto = UserService.getUserResponseDto(userRepo.getById(entry.getValue().getUser()));
              List<Seat> userSeats = new ArrayList<>();
              userSeats.add(entry.getValue());
              trainSecDto = new TrainSectionSeatsDto(userSeats, userResponseDto, sec.getSectionType());
              trainSectionSeatsDtos.put(entry.getValue().getUser(), trainSecDto);
            }
            else {
              TrainSectionSeatsDto trainSectionSeatsDto =
                  trainSectionSeatsDtos.get(entry.getValue().getUser());
              trainSectionSeatsDto.seats().add(entry.getValue());
            }
          }
        }
      }
    }
    return trainSectionSeatsDtos;
  }

  public List<ReceiptResponseDto> deleteUserFromTrain(int userId, int trainId) {
    List<ReceiptResponseDto> updateReceipt =
        ReservationServiceImpl.getInstance().deleteUserFromTrain(userId, trainId);
    return updateReceipt;
  }

  public List<ReceiptResponseDto> updateUserSeat(int userId, int trainId, int seatId, SectionType section,
      int newSeatId, int ticketId) {
    ReservationServiceImpl.getInstance().updateUserSeatOnTrain(userId, trainId, seatId, section, newSeatId,
        ticketId);
    return null;
  }
}
