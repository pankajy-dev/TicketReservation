package com.date.adder;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DateAdderImplTest {

  @Test
  public void testAddDays() {
    DateAdderImpl date = new DateAdderImpl();

    Date dateCalc = date.addDaysLessThan31(2, new Date(2024, 01, 01));
    assertNotNull(dateCalc);
    assertEquals(3, dateCalc.dayOfMonth);
  }

}