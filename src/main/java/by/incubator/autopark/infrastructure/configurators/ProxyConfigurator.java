package by.incubator.autopark.infrastructure.configurators;

import by.incubator.autopark.infrastructure.core.Context;

public interface ProxyConfigurator {
    <T> T makeProxy(T object, Class<T> implementation, Context context);
}
