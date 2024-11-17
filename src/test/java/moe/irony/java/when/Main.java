package moe.irony.java.when;

import moe.irony.java.when.base.Match;
import moe.irony.java.when.base.When;
import moe.irony.java.when.nat.Nat;
import moe.irony.java.when.nat.O;
import moe.irony.java.when.nat.S;
import moe.irony.java.when.shapes.Circle;
import moe.irony.java.when.shapes.Rectangle;
import moe.irony.java.when.shapes.Shape;
import moe.irony.java.when.shapes.Square;

public class Main {

    public static int intFromNat(Nat nat) {
      return new Match<Nat, Integer>(nat)
              .is(O.class, (O o) -> 0)
              .is(S.class, (S s) -> 1 + intFromNat(s.prev()))
              .execute();
    }

    public static void main(String[] args) {

        // When Statements

        new When<Nat>(new S(new S(new O())))
                .is(S.class, (S s) -> {
                  System.out.println(s.prev());
                })
                .is(O.class, (O o) -> {
                  System.out.println(o.next());
                })
                .execute();


        new When<Nat>(new O())
                .is(S.class, (S s) -> {
                  System.out.println("S(?)");
                })
                .is(O.class, (O o) -> {
                  System.out.println("? O");
                })
                .execute();

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

        System.out.println();

        // Match Expressions

        Nat six = new S(new S(new S(new S(new S(new S(new O()))))));
        System.out.print("Should be six:\t");
        System.out.println(intFromNat(six));

        String shouldBeCircle = new Match<Shape, String>(new Circle())
                .is(Circle.class, (Circle circle) -> "Circle")
                .is(Square.class, (Square square) -> "Square")
                .otherwise((Shape shape) -> "Shape")
                .execute();
        System.out.print("Should be circle:\t");
        System.out.println(shouldBeCircle);

        // Switch Expressions


//        String sevenForSix = new Switch<Integer, String>(6)
//                .is(2, i -> "two")
//                .is(3, i -> "three")
//                .otherwise(i -> String.valueOf((i + 1)))
//                .execute();
//        System.out.println(sevenForSix);
//
//        String literalSix = new Switch<Integer, String>(6)
//                .is(8, i -> "eight")
//                .is(7, i -> "seven")
//                .is(6, i -> "six")
//                .execute();
//        System.out.println(literalSix);
    }
}