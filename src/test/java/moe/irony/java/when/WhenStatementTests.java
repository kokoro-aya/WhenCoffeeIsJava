package moe.irony.java.when;

import moe.irony.java.when.base.When;
import moe.irony.java.when.cases.nat.Nat;
import moe.irony.java.when.cases.nat.O;
import moe.irony.java.when.cases.nat.S;
import moe.irony.java.when.cases.shapes.Circle;
import moe.irony.java.when.cases.shapes.Rectangle;
import moe.irony.java.when.cases.shapes.Shape;
import moe.irony.java.when.cases.shapes.Square;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class WhenStatementTests {

  private final PrintStream standardOut = System.out;
  private final ByteArrayOutputStream outputStreamCaptor = new ByteArrayOutputStream();

  @BeforeEach
  public void initialize() {
    System.setOut(new PrintStream(outputStreamCaptor));
  }

  @AfterEach
  public void tearDown() {
    System.setOut(standardOut);
  }

  @Test
  public void testWhenStatementForOne() {
    // Routine

    new When<Nat>(new S(new S(new O())))
            .is(S.class, (S s) -> {
              System.out.println(s.prev());
            })
            .is(O.class, (O o) -> {
              System.out.println(o.next());
            })
            .execute();

    // Output

    assertEquals("S(O)",
            outputStreamCaptor.toString().trim());
  }

  @Test
  public void testWhenStatementForCustomZero() {
    // Routine

    new When<Nat>(new O())
            .is(S.class, (S s) -> {
              System.out.println("S(?)");
            })
            .is(O.class, (O o) -> {
              System.out.println("? O");
            })
            .execute();

    // Output

    assertEquals("? O",
            outputStreamCaptor.toString().trim());
  }

  @Test
  public void testWhenStatementForRectangle() {
    // Routine

    new When<Shape>(new Rectangle())
            .is(Circle.class, (Circle circle) -> {
              System.out.println("Circle");
            })
            .is(Square.class, (Square square) -> {
              System.out.println("Square");
            })
            .otherwise((Shape shape) -> {
              System.out.println("Shape");
            })
            .execute();

    // Output

    assertEquals("Shape",
            outputStreamCaptor.toString().trim());
  }
}
