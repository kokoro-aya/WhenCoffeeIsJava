package moe.irony.java.when;

import moe.irony.java.when.base.Match;
import moe.irony.java.when.cases.nat.Nat;
import moe.irony.java.when.cases.nat.O;
import moe.irony.java.when.cases.nat.S;

public class TestingUtils {
  public static int intFromNat(Nat nat) {
    return new Match<Nat, Integer>(nat)
            .is(O.class, (O o) -> 0)
            .is(S.class, (S s) -> 1 + intFromNat(s.prev()))
            .get();
  }
}
