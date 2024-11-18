package moe.irony.java.when.base.patterns;

import moe.irony.java.when.base.PatternVisitor;
import moe.irony.java.when.base.chain.ChainedResult;

import java.util.function.Function;

public class OtherwisePattern<T, R> implements Pattern<T, R> {

  private final Function<T, R> function;

  public OtherwisePattern(Function<T, R> function) {
    this.function = function;
  }

  public Function<T, R> getFunction() {
    return function;
  }

  @Override
  public ChainedResult<R> accept(PatternVisitor<T, R> visitor) {
    return visitor.visit(this);
  }
}
