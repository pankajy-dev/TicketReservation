package org.railway.ticketbooking.models;

import java.util.ArrayList;
import java.util.Date;

public class ScheduledTrain
    implements Cloneable {

  int id;
  Train train;
  String from;
  String to;
  String startTime;
  String endTime;;
  /*
   * date in ddmmyy format
   */
  String date;

  public ScheduledTrain(int id, Train train, String from, String to, String startTime, String endTime,
      String date) {
    this.id = id;
    this.train = train;
    this.from = from;
    this.to = to;
    this.startTime = startTime;
    this.endTime = endTime;
    this.date = date;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Train getTrain() {
    return train;
  }

  public void setTrain(Train train) {
    this.train = train;
  }

  public String getFrom() {
    return from;
  }

  public void setFrom(String from) {
    this.from = from;
  }

  public String getTo() {
    return to;
  }

  public void setTo(String to) {
    this.to = to;
  }

  public String getStartTime() {
    return startTime;
  }

  public void setStartTime(String startTime) {
    this.startTime = startTime;
  }

  public String getEndTime() {
    return endTime;
  }

  public void setEndTime(String endTime) {
    this.endTime = endTime;
  }

  public String getDate() {
    return date;
  }

  public void setDate(String date) {
    this.date = date;
  }

  @Override
  public ScheduledTrain clone() {
    try {
      // Super.clone is used for shallow copy
      ScheduledTrain clone = (ScheduledTrain) super.clone();
      clone.train = (Train) train.clone();
      return clone;
    }
    catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

}
