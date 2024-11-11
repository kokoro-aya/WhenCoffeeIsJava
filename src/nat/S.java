package nat;

public class S extends Nat {

    private Nat prev;

    public S(Nat prev) {
        this.prev = prev;
    }

    public Nat prev() {
        return prev;
    }

    public S next() {
        return new S(this);
    }

    @Override
    public String toString() {
        return "S(" + prev + ')';
    }
}
