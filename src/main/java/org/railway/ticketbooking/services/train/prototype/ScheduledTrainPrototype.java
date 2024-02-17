package org.railway.ticketbooking.services.train.prototype;

import org.railway.ticketbooking.models.ScheduledTrain;
import org.railway.ticketbooking.models.Train;
import org.railway.ticketbooking.models.TrainName;

import java.text.SimpleDateFormat;
import java.util.Date;

/*
 * ProtoTypes for Scheduled Train to schedule trains for every day.
 */
public class ScheduledTrainPrototype {

  public void createScheduledPrototype() {
    Train train = TrainRegistry.getInstance().get(TrainName.LondonFrance_Express);
    Train cloneTrain = null;
    if (train != null) {
      cloneTrain = train.clone();
      int id = ScheduledTrainRegistry.getInstance().getRegistrySize();
      if (id == 0) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("ddMMyy");
        String formattedDate = formatter.format(date);

        ScheduledTrain scheduledTrain =
            new ScheduledTrain(id++ , cloneTrain, "London", "France", "11:30", "19:00", formattedDate);
        ScheduledTrainRegistry.getInstance().register(cloneTrain.getTrainName().toString(), scheduledTrain);

        train = TrainRegistry.getInstance().get(TrainName.LondonAmsterdam_Express);
        if (train != null) {
          cloneTrain = train.clone();
          scheduledTrain =
              new ScheduledTrain(id++ , cloneTrain, "France", "London", "21:30", "9:00", formattedDate);
          ScheduledTrainRegistry.getInstance().register(cloneTrain.getTrainName().toString(), scheduledTrain);
        }
      }
    }
  }
}
