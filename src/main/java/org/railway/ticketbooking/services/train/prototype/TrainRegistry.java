package org.railway.ticketbooking.services.train.prototype;

import org.railway.ticketbooking.models.Train;
import org.railway.ticketbooking.models.TrainName;

import java.util.HashMap;
import java.util.Map;

/*
 * Registry of Default trains to be used by ScheduledTrain
 */
public class TrainRegistry {

  private static final TrainRegistry INSTANCE = new TrainRegistry();

  private TrainRegistry() {}

  public static TrainRegistry getInstance() {
    return INSTANCE;
  }

  private static Map<TrainName, Train> trainRegistery = new HashMap<>();

  public void register(TrainName key, Train train) {
    trainRegistery.put(key, train);
  }

  public Train get(TrainName key) {
    return trainRegistery.get(key);
  }
}
