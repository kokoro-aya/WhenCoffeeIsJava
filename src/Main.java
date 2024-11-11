import nat.Nat;
import nat.O;
import nat.S;
import shapes.Circle;
import shapes.Rectangle;
import shapes.Shape;
import shapes.Square;

public class Main {
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
    }
}