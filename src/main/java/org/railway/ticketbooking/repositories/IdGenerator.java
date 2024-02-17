package org.railway.ticketbooking.repositories;

public interface IdGenerator {

  Integer incrementId();

  Integer decrementId();

  Integer getId();
}
