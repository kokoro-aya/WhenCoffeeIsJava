package moe.irony.java.when;

import moe.irony.java.when.base.Match;
import moe.irony.java.when.cases.nat.Nat;
import moe.irony.java.when.cases.nat.O;
import moe.irony.java.when.cases.nat.S;
import moe.irony.java.when.cases.shapes.Circle;
import moe.irony.java.when.cases.shapes.Shape;
import moe.irony.java.when.cases.shapes.Square;
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
            .get();

    assertEquals(CIRCLE, actual);
  }

  @Test
  public void testMatchExpressionForIntLiterals() {
    int actual = new Match<Integer, Integer>(3)
            .is(1, (one) -> one + 1)
            .is(2, (two) -> two * 2)
            .is(3, (three) -> three * three * three)
            .otherwise((num) -> -1)
            .get();

    assertEquals(27, actual);
  }

  @Test
  public void testMatchExpressionForMultipleInts() {
    String actual = new Match<Integer, String>(6)
            .is(1, (one) -> "ONE")
            .isAmong(new Integer[]{ 2, 4, 6, 8 }, (x) -> "" + (x / 2))
            .is(9, (two) -> "nine")
            .get();

    assertEquals("3", actual);
  }

  @Test
  public void testMatchExpressionForNullableStrings() {
    Boolean actual = new Match<String, Boolean>(null)
            .isNull(() -> false)
            .otherwise((value) -> true)
            .get();

    assertEquals(false, actual);
  }

  @Test
  public void testMatchExpressionForLaterNulls() {
    Boolean actual = new Match<String, Boolean>(null)
            .is("Five", (five) -> true)
            .isNull(() -> false)
            .otherwise((value) -> true)
            .get();

    assertEquals(false, actual);
  }

  @Test
  public void testMatchExpressionWithLiteralCondition() {
    String actual = new Match<Integer, String>(6)
            .is(1, (one) -> "ONE")
            .isAmong(new Integer[]{ 2, 4, 6, 8 }, (x) -> x < 5, (x) -> "" + (x / 2))
            .is(9, (two) -> "nine")
            .otherwise((x) -> "other value")
            .get();

    assertEquals("other value", actual);
  }

  @Test
  public void testMatchExpressionWithNatCondition() {
    Nat two = new S(new S(new O()));
    String actual = new Match<Nat, String>(two)
            .is(O.class, (o) -> "Zero")
            .is(S.class, (s) -> s.prev() instanceof O, (s) -> "Must be one")
            .otherwise((x) -> "Bigger than one")
            .get();

    assertEquals("Bigger than one", actual);
  }

  @Test
  public void testMatchExpressionWithSimpleConditions() {
    String expected = "Negative number";
    String actual = new Match<Integer, String>(-5)
            .when((number) -> number < 0, (n) -> "Negative number")
            .when((number) -> number == 0, (n) -> "Zero")
            .when((number) -> number > 0, (n) -> "Positive number")
            .otherwise((n) -> "Unknown")
            .get();
    assertEquals(expected, actual);
  }
}
