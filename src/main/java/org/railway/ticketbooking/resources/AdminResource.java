package org.railway.ticketbooking.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.railway.ticketbooking.dtos.request.TicketUpdateDto;
import org.railway.ticketbooking.dtos.response.ReceiptResponseDto;
import org.railway.ticketbooking.dtos.response.TrainSectionSeatsDto;
import org.railway.ticketbooking.dtos.response.UserResponseDto;
import org.railway.ticketbooking.models.SectionType;
import org.railway.ticketbooking.models.User;
import org.railway.ticketbooking.repositories.ScheduledTrainRepository;
import org.railway.ticketbooking.repositories.TicketRepository;
import org.railway.ticketbooking.repositories.TrainRepository;
import org.railway.ticketbooking.repositories.UserRepository;
import org.railway.ticketbooking.services.AdminService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/*
 * This Resource is for performing Admin operations.
 */
@Path("/admin/")
public class AdminResource {

  @GET
  @Path("users")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUser() {
    UserRepository userRepo = new UserRepository();
    List<User> users = userRepo.getAllUsers();
    List<UserResponseDto> userDto = new ArrayList<>();
    if (users != null && users.size() > 0) {
      for (User u : users) {
        UserResponseDto userRespDto =
            new UserResponseDto(u.getId(), u.getFirstName(), u.getLastName(), u.getEmail());
        userDto.add(userRespDto);
      }
      return Response.ok().entity(userDto).build();
    }
    return Response.noContent().entity("User not found.").build();
  }

  @GET
  @Path("users/{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUserById(@PathParam("userId") int userId) {
    UserRepository userRepo = new UserRepository();
    User user = userRepo.getById(userId);
    if (user != null) {
      UserResponseDto userRespDto =
          new UserResponseDto(user.getId(), user.getFirstName(), user.getLastName(), user.getEmail());
      return Response.ok().entity(userRespDto).build();
    }
    return Response.noContent().entity("User not found.").build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUsersAndSeatsInSection(@QueryParam("trainId") int trainId,
      @QueryParam("section") SectionType sectionType) {
    ScheduledTrainRepository schTrainRepo = new ScheduledTrainRepository();
    AdminService adminSevice = new AdminService(new UserRepository(), new TicketRepository(),
        new TrainRepository(), new ScheduledTrainRepository());
    HashMap<java.lang.Integer, TrainSectionSeatsDto> trainSectionSeatsDtos =
        adminSevice.getAllTrainSeats(trainId, sectionType);
    if (trainSectionSeatsDtos != null && trainSectionSeatsDtos.size() > 0) {
      return Response.ok().entity(trainSectionSeatsDtos).build();
    }
    return Response.noContent().entity("Not found.").build();
  }

  @DELETE
  @Produces(MediaType.APPLICATION_JSON)
  public Response deleteUserFromTrain(@QueryParam("userId") int userId, @QueryParam("trainId") int trainId) {
    ScheduledTrainRepository schTrainRepo = new ScheduledTrainRepository();
    AdminService adminSevice = new AdminService(schTrainRepo);
    List<ReceiptResponseDto> deletedSeats = adminSevice.deleteUserFromTrain(userId, trainId);
    if (deletedSeats != null && deletedSeats.size() > 0) {
      return Response.ok().entity(deletedSeats).build();
    }
    return Response.noContent().entity("Not found.").build();
  }

  @Path("/updateSeat/")
  @PUT
  @Produces(MediaType.APPLICATION_JSON)
  @Consumes(MediaType.APPLICATION_JSON)
  public Response updateUserSeatOnTrain(TicketUpdateDto ticketUpdateDto) {
    ScheduledTrainRepository schTrainRepo = new ScheduledTrainRepository();
    AdminService adminSevice = new AdminService(schTrainRepo);
    List<ReceiptResponseDto> deletedSeats = adminSevice.updateUserSeat(ticketUpdateDto.userId(),
        ticketUpdateDto.trainId(), ticketUpdateDto.seatId(), ticketUpdateDto.sectionType(),
        ticketUpdateDto.newSeatId(), ticketUpdateDto.ticketId());
    if (deletedSeats != null && deletedSeats.size() > 0) {
      return Response.ok().entity(deletedSeats).build();
    }
    return Response.noContent().entity("Not found.").build();
  }
}
