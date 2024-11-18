package moe.irony.java.when.base.patterns;

import moe.irony.java.when.base.PatternVisitor;
import moe.irony.java.when.base.chain.ChainedResult;

public interface Pattern<T, R> {


  public ChainedResult<R> accept(PatternVisitor<T, R> visitor);
}
