package moe.irony.java.when.base.patterns;

import moe.irony.java.when.base.PatternVisitor;
import moe.irony.java.when.base.chain.ChainedResult;

import java.util.ArrayList;
import java.util.List;

public class MultiArmPattern<T, R> implements Pattern<T, R> {

  private final List<Pattern<? extends T, R>> patterns;

  public MultiArmPattern() {
    this.patterns = new ArrayList<>();
  }

  public void addPattern(Pattern<? extends T, R> pattern) {
    patterns.add(pattern);
  }

  public List<Pattern<? extends T, R>> getPatterns() {
    return patterns;
  }

  @Override
  public ChainedResult<R> accept(PatternVisitor<T, R> visitor) {
    return visitor.visit(this);
  }
}
