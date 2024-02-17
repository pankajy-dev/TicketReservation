package org.railway.ticketbooking.repositories;

/*
 * This class generates a sequential id for dummy Ticket DB
 */
public class TicketIdGenerator
    implements IdGenerator {
  public static TicketIdGenerator trainIdGenerator = new TicketIdGenerator();
  public static Integer id = 1;

  private TicketIdGenerator() {}

  public static TicketIdGenerator getInstance() {
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
