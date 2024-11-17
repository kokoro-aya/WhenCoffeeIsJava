package moe.irony.java.when.base;

@FunctionalInterface
public interface HeptaFunction<T, U, V, W, X, Y, Z, R> {

  R apply(T t, U u, V v, W w, X x, Y y, Z z);
}
