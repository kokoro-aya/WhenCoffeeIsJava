package moe.irony.java.when.base.patterns;

import moe.irony.java.when.base.PatternVisitor;

import java.util.function.Function;

public class ClassPattern<T, R> implements Pattern<T, R> {
  private final Function<T, R> function;

  public ClassPattern(Function<T, R> function) {
    this.function = function;
  }


  public Function<T, R> getFunction() {
    return this.function;
  }

  @Override
  public R accept(PatternVisitor<T, R> visitor) {
    return visitor.visit(this);
  }
}
