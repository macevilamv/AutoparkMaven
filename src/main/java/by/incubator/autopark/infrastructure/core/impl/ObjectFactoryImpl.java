package by.incubator.autopark.infrastructure.core.impl;

import by.incubator.autopark.infrastructure.configurators.ObjectConfigurator;
import by.incubator.autopark.infrastructure.core.Context;
import by.incubator.autopark.infrastructure.core.ObjectFactory;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectFactoryImpl implements ObjectFactory {
    private final Context context;
    private final List<ObjectConfigurator> objectConfigurators;

    @SneakyThrows
    public ObjectFactoryImpl(Context context) {
        this.context = context;
        Set<Class<? extends ObjectConfigurator>> objectConfSet
                = context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class);
        objectConfigurators = objectConfSet.stream().map(element -> {
            try {
                return element.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }

    private <T> T create(Class<T> implementation) throws Exception {
        return implementation.getConstructor().newInstance();
    }

    @SneakyThrows
    @Override
    public <T> T createObject(Class<T> implementation) {
        T tInstance = create(implementation);

        configure(tInstance);
        initialize(implementation, tInstance);

        return tInstance;
    }

    private <T> void configure(T object) {
        objectConfigurators.stream().forEach(e -> {
            e.configure(object, context);
        });
    }

    private <T> void initialize(Class<T> implementation, T object) throws Exception {
        for (Method method : implementation.getMethods()) {
            if (method.isAnnotationPresent(InitMethod.class)) {
                method.invoke(object);
            }
        }
    }
}
