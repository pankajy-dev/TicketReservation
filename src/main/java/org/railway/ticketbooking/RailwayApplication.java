package org.railway.ticketbooking;

import jakarta.ws.rs.ApplicationPath;
import jakarta.ws.rs.core.Application;
import org.railway.ticketbooking.resources.*;

import java.util.HashSet;
import java.util.Set;

@ApplicationPath("/railway")
public class RailwayApplication
    extends Application {

  @Override
  public Set<Class<?>> getClasses() {

    Set<Class<?>> classes = new HashSet<>();
    classes.add(AdminResource.class);
    classes.add(TicketResource.class);
    classes.add(UserResource.class);
    classes.add(TrainResource.class);
    classes.add(AuthenticationFilter.class);
    return classes;
  }

}