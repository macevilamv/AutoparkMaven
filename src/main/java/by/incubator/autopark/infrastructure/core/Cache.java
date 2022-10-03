package by.incubator.autopark.infrastructure.core;

public interface Cache {
    boolean contains(Class<?> cClass);
    <T> T get(Class<T> tClass);
    <T> void put(Class<T> tClass, T value);
}
