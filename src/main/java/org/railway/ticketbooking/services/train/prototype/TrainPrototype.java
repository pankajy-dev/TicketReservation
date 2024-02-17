package org.railway.ticketbooking.services.train.prototype;

import org.railway.ticketbooking.models.*;
import org.railway.ticketbooking.repositories.ScheduleTrainIdGenerator;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/*
 * This provides prototye for a Train that will be scheduled.
 */
public class TrainPrototype {

  public void createTrainPrototype() {
    Map<Integer, Seat> seatsA = new ConcurrentHashMap<>();
    for (int i = 1; i <= 50; i++ ) {
      Seat seat = new Seat(i, true, 20);
      seatsA.put(i, seat);
    }
    Section secA = new Section(seatsA, SectionType.A);
    List<Section> sections = new ArrayList<>();
    sections.add(secA);

    Map<Integer, Seat> seatsB = new ConcurrentHashMap<>();
    for (int i = 1; i <= 50; i++ ) {
      Seat seat = new Seat(i, true, 15);
      seatsB.put(i, seat);
    }
    Section secB = new Section(seatsB, SectionType.B);
    sections.add(secB);

    Train train = new Train(ScheduleTrainIdGenerator.getInstance().incrementId(),
        TrainName.LondonFrance_Express, sections);

    TrainRegistry.getInstance().register(train.getTrainName(), train);

    train = new Train(ScheduleTrainIdGenerator.getInstance().incrementId(), TrainName.LondonAmsterdam_Express,
        sections);
    TrainRegistry.getInstance().register(train.getTrainName(), train);
  }
}
