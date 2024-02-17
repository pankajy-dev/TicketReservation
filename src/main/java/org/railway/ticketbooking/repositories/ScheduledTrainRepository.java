package org.railway.ticketbooking.repositories;

import org.railway.ticketbooking.models.*;
import org.railway.ticketbooking.services.train.prototype.ScheduledTrainPrototype;
import org.railway.ticketbooking.services.train.prototype.ScheduledTrainRegistry;
import org.railway.ticketbooking.services.train.prototype.TrainRegistry;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class ScheduledTrainRepository {
  static HashMap<Integer, ScheduledTrain> dbScheduledTrains = new HashMap<>();
  private static int scheduledId = 1;

  public void scheduleDefaultTrains() {
    if (dbScheduledTrains.size() == 0) {
      ScheduledTrainRegistry schTrainReg = ScheduledTrainRegistry.getInstance();
      LocalDate date = LocalDate.now();

      for (int i = 0; i < 5; i++ ) {
        LocalDate currentDate = date.plusDays(i);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyy");
        String formattedDate = currentDate.format(formatter);

        ScheduledTrain schTrain = schTrainReg.get(TrainName.LondonFrance_Express.toString());
        ScheduledTrain clonedTrain = null;
        if (schTrain != null) {
          clonedTrain = schTrain.clone();
        }
        int id = ScheduleTrainIdGenerator.getInstance().incrementId();
        clonedTrain.setDate(formattedDate);
        clonedTrain.setId(id);
        dbScheduledTrains.put(id, clonedTrain);
        clonedTrain = null;

        schTrain = schTrainReg.get(TrainName.LondonAmsterdam_Express.toString());
        if (schTrain != null) {
          clonedTrain = schTrain.clone();
        }
        id = ScheduleTrainIdGenerator.getInstance().incrementId();
        clonedTrain.setDate(formattedDate);
        clonedTrain.setId(id);
        dbScheduledTrains.put(id, clonedTrain);
      }
    }
  }

  public List<ScheduledTrain> getAllTrains() {
    List<ScheduledTrain> allSchTrains = new ArrayList<>();
    for (ScheduledTrain schTrain : dbScheduledTrains.values()) {
      allSchTrains.add(schTrain);
    }
    return allSchTrains;
  }

  public ScheduledTrain getTrainById(int id) {
    return dbScheduledTrains.get(id);
  }
}
