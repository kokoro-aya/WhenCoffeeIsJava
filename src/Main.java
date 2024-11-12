import nat.Nat;
import nat.O;
import nat.S;
import shapes.Circle;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;

public class Main {

    public static int intFromNat(Nat nat) {
      return new Match<Nat, Integer>(nat)
              .is(O.class, (O o) -> 0)
              .is(S.class, (S s) -> 1 + intFromNat(s.prev()))
              .execute();
    }

    public static void main(String[] args) {
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

        Nat six = new S(new S(new S(new S(new S(new S(new O()))))));
        System.out.println(intFromNat(six));

        String shouldBeCircle = new Match<Shape, String>(new Circle())
                .is(Circle.class, (Circle circle) -> "Circle")
                .is(Square.class, (Square square) -> "Square")
                .otherwise((Shape shape) -> "Shape")
                .execute();
        System.out.println(shouldBeCircle);

    }
}