package moe.irony.java.when.base;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

public class Match<T, R> {

  private final Map<Class<? extends T>, Function<? extends T, R>> arms = new HashMap<>();

  private Function<T, R> defaultArm = null;

  private final T valueToMatch;

  public Match(T object) {
    this.valueToMatch = object;
  }

  public <U extends T> Match<T, R> is(Class<U> clazz, Function<? extends U, R> fn) {
    this.arms.put(clazz, fn);
    return this;
  }

  public Match<T, R> otherwise(Function<T, R> fn) {
    this.defaultArm = fn;
    return this;
  }

  public R execute() {
    for (Class<? extends T> clazz : this.arms.keySet()) {
      Function<T, R> fn = (Function<T, R>) this.arms.get(clazz);
      if (clazz.isInstance(this.valueToMatch)) {
        return fn.apply(this.valueToMatch);
      }
    }
    return this.defaultArm.apply(this.valueToMatch);
  }

}
