package org.railway.ticketbooking.models;

import java.util.HashMap;
import java.util.Map;

public class Section
    implements Cloneable {
  Map<Integer, Seat> seats;
  SectionType sectionType;

  public Section(Map<Integer, Seat> seats, SectionType sectionType) {
    this.seats = seats;
    this.sectionType = sectionType;
  }

  public Map<Integer, Seat> getSeats() {
    return seats;
  }

  public void setSeats(Map<Integer, Seat> seats) {
    this.seats = seats;
  }

  public SectionType getSectionType() {
    return sectionType;
  }

  public void setSectionType(SectionType sectionType) {
    this.sectionType = sectionType;
  }

  @Override
  public Section clone() {
    try {
      Section clone = (Section) super.clone();

      clone.seats = new HashMap<>();
      for (Map.Entry<Integer, Seat> entry : seats.entrySet()) {
        Integer i = entry.getKey();
        Seat s = entry.getValue();
        clone.seats.put(i, s);
      }
      return clone;
    }
    catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }
}
