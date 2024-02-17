package org.railway.ticketbooking.models;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Train
    implements Cloneable {
  int id;
  TrainName trainName;
  List<Section> sections;

  public Train(int id, TrainName trainName, List<Section> section) {
    this.id = id;
    this.trainName = trainName;
    this.sections = section;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public TrainName getTrainName() {
    return trainName;
  }

  public void setTrainName(TrainName trainName) {
    this.trainName = trainName;
  }

  public List<Section> getSections() {
    return sections;
  }

  public void setSections(List<Section> sections) {
    this.sections = sections;
  }

  @Override
  public Train clone() {
    try {
      // Super.clone is used for shallow copy
      Train clone = (Train) super.clone();

      clone.sections = new ArrayList<>();
      for (Section sec : sections) {
        clone.sections.add(sec.clone());
      }
      return clone;
    }
    catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

}
