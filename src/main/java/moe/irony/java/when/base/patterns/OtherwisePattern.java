package moe.irony.java.when.base.patterns;

import moe.irony.java.when.base.PatternVisitor;

import java.util.function.Supplier;

public class OtherwisePattern<T, R> implements Pattern<T, R> {

  private final Supplier<R> function;

  public OtherwisePattern(Supplier<R> function) {
    this.function = function;
  }

  public Supplier<R> getFunction() {
    return function;
  }

  @Override
  public R accept(PatternVisitor<T, R> visitor) {
    return visitor.visit(this);
  }
}