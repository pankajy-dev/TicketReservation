package org.railway.ticketbooking.repositories;

/*
 * This class generates a sequential id for dummy scheduled DB
 */
public class ScheduleTrainIdGenerator
    implements IdGenerator {

  public static ScheduleTrainIdGenerator trainIdGenerator = new ScheduleTrainIdGenerator();
  public static Integer id = 1;

  private ScheduleTrainIdGenerator() {}

  public static ScheduleTrainIdGenerator getInstance() {
    return trainIdGenerator;
  }

  @Override
  public Integer incrementId() {
    return id++ ;
  }

  @Override
  public Integer decrementId() {
    if (id != 0) {
      return id++ ;
    }
    return id;
  }

  @Override
  public Integer getId() {
    return id;
  }
}
