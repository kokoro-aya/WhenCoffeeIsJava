package moe.irony.java.when.base;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

/**
 * A derived simple class for side-effect-ful matching, can be used as an "advanced switch-case statement" <br>
 * It doesn't support multiple patterns. The code is left here but may be removed/refactored in near future.
 * @deprecated Use the unified `Match` interface instead
 */
@Deprecated
public class When<T> {

    private final Map<Class<? extends T>, Consumer<? extends T>> arms = new HashMap<>();

    private Consumer<T> defaultArm = null;

    private final T valueToMatch;

    public When(T object) {
        this.valueToMatch = object;
    }

    public <U extends T> When<T> is(Class<U> clazz, Consumer<? extends U> fn) {
        this.arms.put(clazz, fn);
        return this;
    }

    public When<T> otherwise(Consumer<T> fn) {
        this.defaultArm = fn;
        return this;
    }

    public void execute() {
        for (Class<? extends T> clazz : this.arms.keySet()) {
            Consumer<T> fn = (Consumer<T>) this.arms.get(clazz);
            if (clazz.isInstance(this.valueToMatch)) {
                fn.accept(this.valueToMatch);
                return;
            }
        }
        this.defaultArm.accept(this.valueToMatch);
    }

}
