package moe.irony.java.when.base.patterns;

import moe.irony.java.when.base.PatternVisitor;

import java.util.function.Predicate;

public class ConditionalPattern<T, R> implements Pattern<T, R> {

  private final Predicate<T> condition;
  private final Pattern<T, R> then;

  public ConditionalPattern(Predicate<T> condition, Pattern<T, R> then) {
    this.condition = condition;
    this.then = then;
  }

  public Predicate<T> getCondition() {
    return condition;
  }

  public Pattern<T,R> getThen() {
    return then;
  }

  @Override
  public R accept(PatternVisitor<T, R> visitor) {
    return visitor.visit(this);
  }
}
