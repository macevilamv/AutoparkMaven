package by.incubator.autopark.infrastructure.core;

import by.incubator.autopark.infrastructure.config.Config;

public interface Context {
    <T> T getObject(Class<T> type);
    Config getConfig();
}
