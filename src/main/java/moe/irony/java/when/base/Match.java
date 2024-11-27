package moe.irony.java.when.base;

import moe.irony.java.when.base.chain.ChainedResult;
import moe.irony.java.when.base.chain.Result;
import moe.irony.java.when.base.patterns.*;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

public class Match<T, R> {


  private final MultiArmPattern<T, R> multiArmPattern = new MultiArmPattern<>();

  private final @Nullable T valueToMatch;

  private ChainedResult<R> cachedResult = null;

  public Match(@Nullable T object) {
    this.valueToMatch = object;
  }

  public <U extends T> Match<T, R> is(Class<U> clazz, Function<U, R> fn) {
    this.multiArmPattern.addPattern(new ClassPattern<>(clazz, fn));
    return this;
  }

  public Match<T, R> is(@NotNull T value, Function<T, R> fn) {
    this.multiArmPattern.addPattern(new LiteralPattern<>(value, fn));
    return this;
  }

  public Match<T, R> isAmong(T[] values, Function<T, R> fn) {
    this.multiArmPattern.addPattern(new LiteralPattern<>(values, fn));
    return this;
  }

  public <U extends T> Match<T, R> is(Class<U> clazz, Predicate<U> condition, Function<U, R> fn) {
    ConditionalPattern<U, R> condPattern = new ConditionalPattern<>(condition, new ClassPattern<>(clazz, fn));
    this.multiArmPattern.addPattern(condPattern);
    return this;
  }

  public Match<T, R> is(@NotNull T value, Predicate<T> condition, Function<T, R> fn) {
    ConditionalPattern<T, R> condPattern = new ConditionalPattern<>(condition, new LiteralPattern<>(value, fn));
    this.multiArmPattern.addPattern(condPattern);
    return this;
  }

  public Match<T, R> isAmong(T[] values, Predicate<T> condition, Function<T, R> fn) {
    ConditionalPattern<T, R> condPattern = new ConditionalPattern<>(condition, new LiteralPattern<>(values, fn));
    this.multiArmPattern.addPattern(condPattern);
    return this;
  }

  public Match<T, R> when(Predicate<T> condition, Function<T, R> fn) {
    this.multiArmPattern.addPattern(new ConditionalPattern<>(condition, new OtherwisePattern<>(fn)));
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

  public R get() {
    if (this.cachedResult != null) {
      return retrieveResult(this.cachedResult);
    }

    PatternVisitor<T, R> visitor = new PatternMatcher<>(this.valueToMatch);
    this.cachedResult = this.multiArmPattern.accept(visitor);
    return retrieveResult(this.cachedResult);
  }

  private @Nullable R retrieveResult(ChainedResult<R> wrapper) {
    if (wrapper instanceof Result) {
      return ((Result<R>) wrapper).getResult();
    }

    return null;
  }

}
