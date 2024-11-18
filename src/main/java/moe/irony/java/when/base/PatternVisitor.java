package moe.irony.java.when.base;

import moe.irony.java.when.base.patterns.*;

public interface PatternVisitor<T, R> {

  R visit(ClassPattern<T, R> pattern);
  R visit(ConditionalPattern<T, R> pattern);
  R visit(LiteralPattern<T, R> pattern);
  R visit(NullPattern<T, R> pattern);
  R visit(OtherwisePattern<T, R> pattern);
  R visit(RangePattern<T, R> pattern);
  R visit(RawPattern<T, R> pattern);

}
