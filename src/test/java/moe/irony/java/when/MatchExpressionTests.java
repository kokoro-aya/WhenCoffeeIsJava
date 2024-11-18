package moe.irony.java.when;

import moe.irony.java.when.base.Match;
import moe.irony.java.when.nat.Nat;
import moe.irony.java.when.nat.O;
import moe.irony.java.when.nat.S;
import moe.irony.java.when.shapes.Circle;
import moe.irony.java.when.shapes.Shape;
import moe.irony.java.when.shapes.Square;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class MatchExpressionTests {

  private final static String CIRCLE = "Circle";
  private final static String SQUARE = "Square";
  private final static String SHAPE = "Shape";

  @Test
  public void testMatchExpressionForNat() {
    int expected = 6;
    Nat six = new S(new S(new S(new S(new S(new S(new O()))))));
    int actual = TestingUtils.intFromNat(six);

    assertEquals(expected, actual);
  }

  @Test
  public void testMatchExpressionForShape() {
    String actual = new Match<Shape, String>(new Circle())
            .is(Circle.class, (Circle circle) -> CIRCLE)
            .is(Square.class, (Square square) -> SQUARE)
            .otherwise((Shape shape) -> SHAPE)
            .execute();

    assertEquals(CIRCLE, actual);
  }

  @Test
  public void testMatchExpressionForIntLiterals() {
    int actual = new Match<Integer, Integer>(3)
            .is(1, (one) -> one + 1)
            .is(2, (two) -> two * 2)
            .is(3, (three) -> three * three * three)
            .otherwise((num) -> -1)
            .execute();

    assertEquals(27, actual);
  }

  @Test
  public void testMatchExpressionForNullableStrings() {
    Boolean actual = new Match<String, Boolean>(null)
            .isNull(() -> false)
            .otherwise((value) -> true)
            .execute();

    assertEquals(false, actual);
  }
}
