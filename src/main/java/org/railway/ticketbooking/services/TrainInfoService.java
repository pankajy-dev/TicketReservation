package org.railway.ticketbooking.services;

import org.railway.ticketbooking.models.ScheduledTrain;
import org.railway.ticketbooking.repositories.ScheduledTrainRepository;

import java.util.List;
import java.util.stream.Collectors;

public class TrainInfoService {
  ScheduledTrainRepository schTrainRepo;

  public TrainInfoService(ScheduledTrainRepository schTrainRepo) {
    this.schTrainRepo = schTrainRepo;
  }

  public List<ScheduledTrain> getAllTrainsByDate(String date) {
    List<ScheduledTrain> allTrains = schTrainRepo.getAllTrains();
    if (allTrains != null && allTrains.size() > 0) {
      List<ScheduledTrain> allTrainsByDate =
          allTrains.stream().filter(sch -> sch.getDate().equals(date)).collect(Collectors.toList());
      return allTrainsByDate;
    }
    return null;
  }

  public List<ScheduledTrain> getAllTrainsById(int id) {
    List<ScheduledTrain> allTrains = schTrainRepo.getAllTrains();
    if (allTrains != null && allTrains.size() > 0) {
      List<ScheduledTrain> allTrainsById =
          allTrains.stream().filter(sch -> sch.getId() == (id)).collect(Collectors.toList());
      return allTrainsById;
    }
    return null;
  }
}
