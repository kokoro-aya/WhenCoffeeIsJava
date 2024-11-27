package moe.irony.java.when;

import moe.irony.java.when.base.Match;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantMatchExpressionTests {
  @Test
  public void testSwitchSevenForSix() {
    // Theory
    String expected = "7";

    // Fact
    String actual = new Match<Integer, String>(6)
            .is(2, i -> "two")
            .is(3, i -> "three")
            .otherwise(i -> String.valueOf((i + 1)))
            .execute();

    assertEquals(expected, actual);
  }

  @Test
  public void testSwitchLiteralSix() {
    // Theory
    String expected = "six";

    // Fact
    String actual = new Match<Integer, String>(6)
            .is(8, i -> "eight")
            .is(7, i -> "seven")
            .is(6, i -> "six")
            .execute();

    assertEquals(expected, actual);
  }
}

