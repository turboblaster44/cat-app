package com.demo.rest.utils;

@FunctionalInterface
public interface TriFunction<T, U, V, R> {
    R apply(T t, U u, V v);

    // Optional: Add other default methods similar to BiFunction, if needed
    default <W> TriFunction<T, U, V, W> andThen(java.util.function.Function<? super R, ? extends W> after) {
        java.util.Objects.requireNonNull(after);
        return (T t, U u, V v) -> after.apply(apply(t, u, v));
    }
}