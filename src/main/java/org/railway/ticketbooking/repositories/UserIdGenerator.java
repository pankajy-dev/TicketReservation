package org.railway.ticketbooking.repositories;

/*
 * This class generates a sequential id for dummy User DB
 */
public class UserIdGenerator
    implements IdGenerator {

  public static UserIdGenerator userIdGenerator = new UserIdGenerator();
  public static Integer id = 1;

  private UserIdGenerator() {}

  public static UserIdGenerator getInstance() {
    return userIdGenerator;
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
