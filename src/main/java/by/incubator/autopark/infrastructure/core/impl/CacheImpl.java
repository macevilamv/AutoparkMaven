package by.incubator.autopark.infrastructure.core.impl;

import by.incubator.autopark.infrastructure.core.Cache;

import java.util.HashMap;
import java.util.Map;

public class CacheImpl implements Cache {
    private Map<String, Object> cache;

    public CacheImpl() {
        this.cache = new HashMap<>();
    }

    @Override
    public boolean contains(Class<?> cClass) {
        return cache.containsKey(cClass.getSimpleName());
    }

    @Override
    public <T> T get(Class<T> tClass) {
        return (T) cache.get(tClass.getSimpleName());
    }

    @Override
    public <T> void put(Class<T> tClass, T value) {
        cache.put(tClass.getSimpleName(), value);
    }
}
