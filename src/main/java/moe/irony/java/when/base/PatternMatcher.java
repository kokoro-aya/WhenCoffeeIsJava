package moe.irony.java.when.base;

import moe.irony.java.when.base.chain.ChainedResult;
import moe.irony.java.when.base.chain.Empty;
import moe.irony.java.when.base.chain.Result;
import moe.irony.java.when.base.patterns.*;
import org.jetbrains.annotations.Nullable;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Objects;

public class PatternMatcher<T, R> implements  PatternVisitor<T, R> {
  
  private @Nullable T matchedValue;
  
  public PatternMatcher(@Nullable T matchedValue) {
    this.matchedValue = matchedValue;
  }
  
  @Override
  public ChainedResult<R> visit(ClassPattern<T, R> pattern) {
    if (matchedValue == null) {
      return new Empty<>();
    }

    if (matchedValue.getClass().equals(pattern.getClazz())) {
      return new Result<>(pattern.getFunction().apply(matchedValue));
    }

    return new Empty<>();
  }

  @Override
  public ChainedResult<R> visit(ConditionalPattern<T, R> pattern) {
    boolean condition = pattern.getCondition().test(matchedValue);
    if (condition) {
      return pattern.getThen().accept(this);
    }

    return new Empty<>();
  }

  @Override
  public ChainedResult<R> visit(LiteralPattern<T, R> pattern) {
    List<T> literals = pattern.getLiterals();
    for (T literal : literals) {
      if (Objects.equals(this.matchedValue, literal)) {
        return new Result<>(pattern.getFunction().apply(this.matchedValue));
      }
    }

    return new Empty<>();
  }

  @Override
  public ChainedResult<R> visit(NullPattern<T, R> pattern) {
    if (this.matchedValue == null) {
      return new Result<>(pattern.getFunction().get());
    }

    return new Empty<>();
  }

  @Override
  @SuppressWarnings({"unchecked", "rawtypes"})
  public ChainedResult<R> visit(MultiArmPattern<T, R> arms) {

    List<Pattern<? extends T, R>> patterns = arms.getPatterns();

    for (Pattern<? extends T, R> pattern : patterns) {
      ChainedResult<R> chainedResult = pattern.accept((PatternVisitor) this);
      if (chainedResult instanceof Result) {
        return chainedResult;
      }
    }

    return new Empty<>();
  }

  @Override
  public ChainedResult<R> visit(OtherwisePattern<T, R> pattern) {
    return new Result<>(pattern.getFunction().apply(this.matchedValue));
  }
}
