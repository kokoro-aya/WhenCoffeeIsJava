package moe.irony.java.when.base.patterns;

import moe.irony.java.when.base.PatternVisitor;
import moe.irony.java.when.base.chain.ChainedResult;

public class RangePattern<T, R> implements Pattern<T, R> {
  @Override
  public ChainedResult<R> accept(PatternVisitor<T, R> visitor) {
    return visitor.visit(this);
  }
}
