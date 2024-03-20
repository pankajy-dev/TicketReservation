package com.date.adder;

import java.sql.Array;
import java.util.Arrays;
import java.util.List;

public class DateAdderImpl
    implements DateAdder {

  Date date;

  public DateAdderImpl() {}

  public DateAdderImpl(Date date) {
    this.date = date;
  }

  @Override
  public Date addDays(Integer numOfDays) {
    int year = date.year;
    int month = date.month;
    int day = date.dayOfMonth;

    List<Integer> thirtyDays = Arrays.asList(1, 3, 5, 7, 8, 10, 12);
    List<Integer> thirtyOneDays = Arrays.asList(4, 6, 9, 11);
    Integer feb = 2;

    int newDay = 0;
    if (numOfDays != null) {
      newDay = day + numOfDays;
    }
    Date newDate = null;
    if (month > 0 && thirtyDays.contains(month)) {
      if (newDay < 30) {
        return addDaysLessThan30(newDay, date);
      }
    }

    if (month > 0 && thirtyOneDays.contains(month)) {
      if (newDay < 31) {
        return addDaysLessThan31(newDay, date);
      }
    }

    if (month > 0 && month == feb) {
      if (newDay < 29) {
        return addDaystoFeb(newDay, date);
      }
    }
    return newDate;
  }

  protected Date addDaysLessThan30(int days, Date date) {
    date = new Date(date.year, date.month, days);
    return date;
  }

  protected Date addDaysLessThan31(int days, Date date) {
    int newDay = 0;
    if (date.dayOfMonth != 0) {
      newDay = date.dayOfMonth + days;
    }
    date = new Date(date.year, date.month, newDay);
    return date;
  }

  protected Date addDaystoFeb(int days, Date date) {
    date = new Date(date.year, date.month, days);
    return date;
  }
}
