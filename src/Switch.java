import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Function;

public class Switch<T, R> {

  private final Map<T, Function<T, R>> arms = new HashMap<>();

  private Function<T, R> defaultArm = null;

  private final T valueToMatch;

  public Switch(T object) {
    this.valueToMatch = object;
  }

  public Switch<T, R> is(T value, Function<T, R> fn) {
    this.arms.put(value, fn);
    return this;
  }

  public Switch<T, R> otherwise(Function<T, R> fn) {
    this.defaultArm = fn;
    return this;
  }

  public R execute() {
    for (T value : this.arms.keySet()) {
      Function<T, R> fn = this.arms.get(value);
      if (value.equals(this.valueToMatch)) {
        return fn.apply(this.valueToMatch);
      }
    }
    return this.defaultArm.apply(this.valueToMatch);
  }

}
