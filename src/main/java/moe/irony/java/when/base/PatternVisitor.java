package moe.irony.java.when.base;

import moe.irony.java.when.base.chain.ChainedResult;
import moe.irony.java.when.base.patterns.*;

public interface PatternVisitor<T, R> {

  ChainedResult<R> visit(ClassPattern<T, R> pattern);
  ChainedResult<R> visit(ConditionalPattern<T, R> pattern);
  ChainedResult<R> visit(LiteralPattern<T, R> pattern);
  ChainedResult<R> visit(NullPattern<T, R> pattern);
  ChainedResult<R> visit(OtherwisePattern<T, R> pattern);
  ChainedResult<R> visit(RangePattern<T, R> pattern);

  ChainedResult<R> visit(MultiArmPattern<T, R> pattern);
}
