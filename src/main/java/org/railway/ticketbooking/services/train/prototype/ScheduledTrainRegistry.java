package org.railway.ticketbooking.services.train.prototype;

import org.railway.ticketbooking.models.ScheduledTrain;

import java.util.HashMap;
import java.util.Map;

/*
 * Registry of Default Scheduled Trains
 */
public class ScheduledTrainRegistry {

  private static final ScheduledTrainRegistry INSTANCE = new ScheduledTrainRegistry();

  private ScheduledTrainRegistry() {}

  public static ScheduledTrainRegistry getInstance() {
    return INSTANCE;
  }

  private Map<String, ScheduledTrain> scheduledTrainRegistery = new HashMap<>();

  public void register(String key, ScheduledTrain scheduledTrain) {
    scheduledTrainRegistery.put(key, scheduledTrain);
  }

  public ScheduledTrain get(String key) {
    return scheduledTrainRegistery.get(key);
  }

  public int getRegistrySize() {
    return scheduledTrainRegistery.size();
  }

}
