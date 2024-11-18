package moe.irony.java.when.base.patterns;

import moe.irony.java.when.base.PatternVisitor;
import moe.irony.java.when.base.chain.ChainedResult;

import java.util.function.Function;

public class ClassPattern<T, R> implements Pattern<T, R> {

  private final Class<T> clazz;
  private final Function<T, R> function;

  public ClassPattern(Class<T> clazz,Function<T, R> function) {
    this.clazz = clazz;
    this.function = function;
  }

  public Class<T> getClazz() {
    return clazz;
  }

  public Function<T, R> getFunction() {
    return this.function;
  }

  @Override
  public ChainedResult<R> accept(PatternVisitor<T, R> visitor) {
    return visitor.visit(this);
  }
}
