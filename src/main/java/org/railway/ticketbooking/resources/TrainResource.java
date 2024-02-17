package org.railway.ticketbooking.resources;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.railway.ticketbooking.models.ScheduledTrain;
import org.railway.ticketbooking.repositories.ScheduledTrainRepository;
import org.railway.ticketbooking.services.TrainInfoService;

import java.time.LocalDate;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

@Path("/trains/")
public class TrainResource {

  @GET
  @Path("{date}")
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllTrains(@PathParam("date") String trainDate) {

    ScheduledTrainRepository schTrain = new ScheduledTrainRepository();
    TrainInfoService trainInfoService = new TrainInfoService(schTrain);
    List<ScheduledTrain> listSchTrain = trainInfoService.getAllTrainsByDate(trainDate);

    if (listSchTrain != null) {
      return Response.status(Response.Status.OK).entity(listSchTrain).build();
    }
    return Response.noContent().entity("Trains not found.").build();
  }

  @GET
  @Produces(MediaType.APPLICATION_JSON)
  public Response getAllTrains(@QueryParam("trainId") int trainId) {

    ScheduledTrainRepository schTrainRepo = new ScheduledTrainRepository();
    TrainInfoService trainInfoService = new TrainInfoService(schTrainRepo);
    List<ScheduledTrain> listSchTrain = trainInfoService.getAllTrainsById(trainId);

    if (listSchTrain != null) {
      return Response.status(Response.Status.OK).entity(listSchTrain).build();
    }
    return Response.noContent().entity("Trains not found.").build();
  }
}