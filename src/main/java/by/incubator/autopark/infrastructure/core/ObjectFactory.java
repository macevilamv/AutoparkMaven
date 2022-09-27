package by.incubator.autopark.infrastructure.core;

public interface ObjectFactory {
    <T> T createObject(Class<T> implementation);
}
