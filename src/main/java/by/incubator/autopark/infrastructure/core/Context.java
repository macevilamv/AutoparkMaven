package by.incubator.autopark.infrastructure.core;

public interface Context {
    <T> T getObject(Class<T> type);
    Config getConfig();
}
