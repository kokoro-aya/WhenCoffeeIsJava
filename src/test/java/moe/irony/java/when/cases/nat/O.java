package moe.irony.java.when.cases.nat;

public class O extends Nat {

    public S next() {
        return new S(this);
    }

    @Override
    public String toString() {
        return "O";
    }
}
