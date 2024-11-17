package moe.irony.java.when.base.interfaces;

@FunctionalInterface
public interface OctaFunction<T, U, V, W, X, Y, Z, A, R> {

  R apply(T t, U u, V v, W w, X x, Y y, Z z, A a);
}
