package moe.irony.java.when.base;

import moe.irony.java.when.base.chain.ChainedResult;
import moe.irony.java.when.base.chain.Result;
import moe.irony.java.when.base.patterns.*;

import java.util.function.Function;
import java.util.function.Supplier;

public class Match<T, R> {


  private MultiArmPattern<T, R> multiArmPattern = new MultiArmPattern<>();

  private final T valueToMatch;

  public Match(T object) {
    this.valueToMatch = object;
  }

  public <U extends T> Match<T, R> is(Class<U> clazz, Function<U, R> fn) {
    this.multiArmPattern.addPattern(new ClassPattern<>(clazz, fn));
    return this;
  }

  public Match<T, R> is(T value, Function<T, R> fn) {
    this.multiArmPattern.addPattern(new LiteralPattern<>(value, fn));
    return this;
  }

  public Match<T, R> isAmong(T[] values, Function<T, R> fn) {
    this.multiArmPattern.addPattern(new LiteralPattern<>(values, fn));
    return this;
  }

  public Match<T, R> isNull(Supplier<R> fn) {
    this.multiArmPattern.addPattern(new NullPattern<>(fn));
    return this;
  }

  public Match<T, R> otherwise(Function<T, R> fn) {
    this.multiArmPattern.addPattern(new OtherwisePattern<>(fn));
    return this;
  }

  public R execute() {
    PatternVisitor<T, R> visitor = new PatternMatcher<>(this.valueToMatch);

    ChainedResult<R> result = this.multiArmPattern.accept(visitor);

    if (result instanceof Result) {
      return ((Result<R>) result).getResult();
    } else {
      return null;
    }
  }

}
