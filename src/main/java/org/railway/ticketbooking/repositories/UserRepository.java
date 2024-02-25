package org.railway.ticketbooking.repositories;

import org.railway.ticketbooking.models.User;
import org.railway.ticketbooking.models.UserType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class UserRepository {

  static HashMap<java.lang.Integer, User> dbUsers = new HashMap<>();

  public UserRepository() {}

  public static void getDefaultUsers() {
    User user = new User(UserIdGenerator.getInstance().incrementId(), "Admin1", "", "admin@email.com",
        "pa$$word@123", UserType.ADMIN);
    dbUsers.put(user.getId(), user);
    user = new User(UserIdGenerator.getInstance().incrementId(), "pankaj", "yadav", "yadavpankaj28@gmail.com",
        "pankajyadav", UserType.USER);
    dbUsers.put(user.getId(), user);
    user = new User(UserIdGenerator.getInstance().incrementId(), "john", "doe", "johndoe@email.com",
        "johndoe", UserType.USER);
    dbUsers.put(user.getId(), user);
  }

  public User getUserByEmail(String email) {
    User userFound = null;
    for (User user : dbUsers.values()) {
      if (user.getEmail() != null && email != null && user.getEmail().equals(email)) {
        userFound = user;
      }
    }
    return userFound;
  }

  public List<User> getAllUsers() {
    List<User> users = new ArrayList<>();
    for (User user : dbUsers.values()) {
      if (user != null && user.getType().equals(UserType.USER)) {
        users.add(user);
      }
    }
    return users;
  }

  public User addUser(String firstName, String lastName, String email) {
    User user = new User(UserIdGenerator.getInstance().incrementId(), firstName, lastName, email,
        firstName + lastName, UserType.USER);
    dbUsers.put(user.getId(), user);
    return user;
  }

  public User getById(int userID) {
    if (dbUsers.containsKey(userID)) {
      return dbUsers.get(userID);
    }
    return null;
  }
}
