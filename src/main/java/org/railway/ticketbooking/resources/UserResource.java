package org.railway.ticketbooking.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.railway.ticketbooking.dtos.response.ReceiptResponseDto;
import org.railway.ticketbooking.dtos.response.UserResponseDto;
import org.railway.ticketbooking.models.User;
import org.railway.ticketbooking.models.UserType;
import org.railway.ticketbooking.repositories.TicketRepository;
import org.railway.ticketbooking.repositories.UserRepository;
import org.railway.ticketbooking.services.UserService;

import java.net.URI;
import java.util.List;

@Path("/user/")
public class UserResource {
  @GET
  @Path("{userId}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getUser(@PathParam("userId") int userId) {
    UserRepository userRepo = new UserRepository();
    User user = userRepo.getById(userId);
    if (user != null && user.getType() != UserType.ADMIN) {

      UserResponseDto userRespDto = UserService.getUserResponseDto(user);
      return Response.ok().entity(userRespDto).build();
    }
    return Response.noContent().entity("User not found.").build();
  }

  @POST
  @Path("/register/{firstName}/{lastName}/{email}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response registerUser(@PathParam("firstName") String firstName,
      @PathParam("lastName") String lastName, @PathParam("email") String email) {

    UserService userService = new UserService(new UserRepository(), new TicketRepository());
    if (!userService.checkUserExist(email)) {
      User user = userService.registerUser(firstName, lastName, email);
      if (user != null) {
        URI locationUri = URI.create("/railway/user/" + user.getId());
        return Response.created(locationUri).build();
      }
      else {
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Something bad happen: " + email)
            .build();
      }
    }
    else {
      return Response.status(Response.Status.CONFLICT).entity("User already exists for email: " + email)
          .build();
    }
  }

  @GET
  @Path("/{userId}/receipt")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllTrains(@PathParam("userId") int userId) {

    UserRepository userRepository = new UserRepository();
    TicketRepository ticketRepo = new TicketRepository();
    UserService userService = new UserService(userRepository, ticketRepo);
    List<ReceiptResponseDto> respReceiptDto = userService.getUserReceipts(userId);
    if (respReceiptDto != null) {
      return Response.status(Response.Status.OK).entity(respReceiptDto).build();
    }
    return Response.noContent().entity("Receipt not found.").build();
  }
}
