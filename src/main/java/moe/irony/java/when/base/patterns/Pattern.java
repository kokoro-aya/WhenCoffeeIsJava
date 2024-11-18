package moe.irony.java.when.base.patterns;

import moe.irony.java.when.base.PatternVisitor;

public interface Pattern<T, R> {


  public R accept(PatternVisitor<T, R> visitor);
}
