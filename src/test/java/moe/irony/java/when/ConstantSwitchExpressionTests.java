package moe.irony.java.when;

import moe.irony.java.when.base.Match;
import moe.irony.java.when.base.Switch;
import moe.irony.java.when.nat.Nat;
import moe.irony.java.when.nat.O;
import moe.irony.java.when.nat.S;
import moe.irony.java.when.shapes.Circle;
import moe.irony.java.when.shapes.Shape;
import moe.irony.java.when.shapes.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ConstantSwitchExpressionTests {
  @Test
  public void testSwitchSevenForSix() {
    // Theory
    String expected = "7";

    // Fact
    String actual = new Switch<Integer, String>(6)
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
    String actual = new Switch<Integer, String>(6)
            .is(8, i -> "eight")
            .is(7, i -> "seven")
            .is(6, i -> "six")
            .execute();

    assertEquals(expected, actual);
  }
}

