package moe.irony.java.when.base;

import moe.irony.java.when.base.patterns.*;

import java.util.List;

public class PatternMatcher<T, R> implements  PatternVisitor<T, R> {
  
  private T matchedValue;
  
  public PatternMatcher(T matchedValue) {
    this.matchedValue = matchedValue;
  }
  
  @Override
  public R visit(ClassPattern<T, R> pattern) {
    return pattern.getFunction().apply(matchedValue);
  }

  @Override
  public R visit(ConditionalPattern<T, R> pattern) {
    boolean condition = pattern.getCondition().test(matchedValue);
    if (condition) {
      return pattern.getThen().accept(this);
    }

    return null;
  }

  @Override
  public R visit(LiteralPattern<T, R> pattern) {
    List<T> literals = pattern.getLiterals();
    for (T literal : literals) {
      if (this.matchedValue.equals(literal)) {
        return pattern.getFunction().apply(this.matchedValue);
      }
    }

    return null;
  }

  @Override
  public R visit(NullPattern<T, R> pattern) {
    if (this.matchedValue == null) {
      return pattern.getFunction().get();
    }

    return null;
  }

  @Override
  public R visit(OtherwisePattern<T, R> pattern) {
    return pattern.getFunction().get();
  }

  @Override
  public R visit(RangePattern<T, R> pattern) {
    return null;
  }

  @Override
  public R visit(RawPattern<T, R> pattern) {
    return null;
  }
}
