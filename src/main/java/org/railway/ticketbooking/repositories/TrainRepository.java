package org.railway.ticketbooking.repositories;

import org.railway.ticketbooking.models.*;
import org.railway.ticketbooking.services.train.prototype.TrainRegistry;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class TrainRepository {

  // static HashMap<Integer, Train> dbTrains = new HashMap<>();

  public TrainRepository() {
    // if (dbTrains.size() == 0) {
    // createTrainRecord();
    // }
  }

  // private void createTrainRecord() {
  // TrainRegistry trainReg = TrainRegistry.getInstance();
  // Train train = trainReg.get(TrainName.LondonFrance_Express);
  // train.setId(TrainIdGenerator.getInstance().trainIdGenerator.incrementId());
  // dbTrains.put(train.getId(), train);
  //
  // train = trainReg.get(TrainName.LondonAmsterdam_Express);
  // train.setId(TrainIdGenerator.getInstance().trainIdGenerator.incrementId());
  // dbTrains.put(train.getId(), train);
  // }
}
