package moe.irony.java.when.base.patterns;

import moe.irony.java.when.base.PatternVisitor;
import moe.irony.java.when.base.chain.ChainedResult;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

public class LiteralPattern<T, R> implements Pattern<T, R> {

  private final List<T> literals;
  private final Function<T, R> function;

  public LiteralPattern(T literal, Function<T, R> function) {
    this.literals = Collections.singletonList(literal);
    this.function = function;
  }

  public LiteralPattern(T[] literals, Function<T, R> function) {
    this.literals = Arrays.asList(literals);
    this.function = function;
  }

  public LiteralPattern(List<T> literals, Function<T, R> function) {
    this.literals = literals;
    this.function = function;
  }

  public List<T> getLiterals() {
    return literals;
  }

  public Function<T,R> getFunction() {
    return function;
  }

  @Override
  public ChainedResult<R> accept(PatternVisitor<T, R> visitor) {
    return visitor.visit(this);
  }
}
