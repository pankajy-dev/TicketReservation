package org.railway.ticketbooking.services;

import org.railway.ticketbooking.dtos.response.ReceiptResponseDto;
import org.railway.ticketbooking.dtos.response.UserResponseDto;
import org.railway.ticketbooking.models.Ticket;
import org.railway.ticketbooking.models.User;
import org.railway.ticketbooking.repositories.TicketRepository;
import org.railway.ticketbooking.repositories.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class UserService {

  UserRepository userRepo;
  TicketRepository ticketRepo;

  public UserService(UserRepository userRepo, TicketRepository ticketRepo) {
    this.userRepo = userRepo;
    this.ticketRepo = ticketRepo;
  }

  public boolean checkUserExist(String email) {
    UserRepository userRepository = new UserRepository();
    User existingUser = userRepo.getUserByEmail(email);
    if (existingUser != null) {
      return true;
    }
    return false;
  }

  public User registerUser(String firstName, String lastName, String email) {
    User user = null;
    user = userRepo.addUser(firstName, lastName, email);
    return user;
  }

  public List<ReceiptResponseDto> getUserReceipts(int userId) {
    User user = null;
    user = userRepo.getById(userId);
    List<Ticket> tickets = new ArrayList<>();
    tickets = ticketRepo.getTicketByUserId(userId);
    List<ReceiptResponseDto> receiptDto = new ArrayList<>();
    for (Ticket ticket : tickets) {
      receiptDto.add(TicketBookingService.getReceiptResponseDto(ticket));
    }
    return receiptDto;
  }

  public static UserResponseDto getUserResponseDto(User user) {
    UserResponseDto userRespDto =
        new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
    return userRespDto;
  }
}
