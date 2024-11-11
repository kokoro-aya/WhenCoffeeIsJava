import java.util.function.Consumer;

public class When<T> {
    public When(T object) {
    }

    public <U extends T> When<T> is(Class<U> clazz, Consumer<U> fn) {
        return this;
    }

    public When<T> otherwise(Consumer<T> fn) {
        return this;
    }

    public void execute() {

    }

}
