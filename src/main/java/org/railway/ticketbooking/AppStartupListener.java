package org.railway.ticketbooking;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;
import org.railway.ticketbooking.repositories.ScheduledTrainRepository;
import org.railway.ticketbooking.repositories.UserRepository;
import org.railway.ticketbooking.services.train.prototype.ScheduledTrainPrototype;
import org.railway.ticketbooking.services.train.prototype.TrainPrototype;

@WebListener
public class AppStartupListener
    implements ServletContextListener {

  /*
   * This is run once during server startup to load Default Trains, Admin user, Users
   */
  @Override
  public void contextInitialized(ServletContextEvent sce) {
    System.out.println("Server startup: executing initialization tasks...");
    TrainPrototype trainProto = new TrainPrototype();
    trainProto.createTrainPrototype();
    ScheduledTrainPrototype schTrainProto = new ScheduledTrainPrototype();
    schTrainProto.createScheduledPrototype();
    new UserRepository().getDefaultUsers();
    new ScheduledTrainRepository().scheduleDefaultTrains();
  }
}
