package by.incubator.autopark.infrastructure.core.impl;

import by.incubator.autopark.infrastructure.core.Cache;
import by.incubator.autopark.infrastructure.config.Config;
import by.incubator.autopark.infrastructure.core.Context;
import by.incubator.autopark.infrastructure.core.ObjectFactory;
import by.incubator.autopark.infrastructure.config.impl.JavaConfig;

import java.util.Map;

public class ApplicationContext implements Context {
    private final Config config;
    private final Cache cache;
    private final ObjectFactory factory;

    public ApplicationContext(String packageToScan,
                              Map<Class<?>, Class<?>> interfaceToImplementation) {
        this.config = new JavaConfig(new ScannerImpl(packageToScan), interfaceToImplementation);
        this.cache = new CacheImpl();
        cache.put(Context.class, this);
        this.factory = new ObjectFactoryImpl(this);
    }

    @Override
    public <T> T getObject(Class<T> type) {
        if (cache.contains(type)) {
            return cache.get(type);
        } else {
            if (type.isInterface()) {
                return this.factory.createObject(config.getImplementation(type));
            } else {
                return this.factory.createObject(type);
            }
        }
    }

    @Override
    public Config getConfig() {
        return this.config;
    }
}
