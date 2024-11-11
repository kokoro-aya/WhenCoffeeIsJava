import java.util.HashMap;
import java.util.Map;
import java.util.function.Consumer;

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
