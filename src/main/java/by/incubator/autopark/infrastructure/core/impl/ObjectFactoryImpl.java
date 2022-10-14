package by.incubator.autopark.infrastructure.core.impl;

import by.incubator.autopark.infrastructure.configurators.ObjectConfigurator;
import by.incubator.autopark.infrastructure.configurators.ProxyConfigurator;
import by.incubator.autopark.infrastructure.core.Context;
import by.incubator.autopark.infrastructure.core.ObjectFactory;
import by.incubator.autopark.infrastructure.core.annotations.InitMethod;
import lombok.SneakyThrows;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

public class ObjectFactoryImpl implements ObjectFactory {
    private final Context context;
    private final List<ObjectConfigurator> objectConfigurators;
    private final List<ProxyConfigurator> proxyConfigurators;

    @SneakyThrows
    public ObjectFactoryImpl(Context context) {
        this.context = context;
        objectConfigurators = initializeObjectConfiguratorsList();
        proxyConfigurators = initializeProxyConfiguratorsList();
    }

    @SneakyThrows
    @Override
    public <T> T createObject(Class<T> implementation) {
        T tInstance = create(implementation);

        configure(tInstance);
        initialize(implementation, tInstance);
        tInstance = makeProxy(implementation, tInstance);

        return tInstance;
    }

    private <T> T makeProxy(Class<T> implClass, T object) {
        for (ProxyConfigurator proxyConfigurator:
                proxyConfigurators) {
            object = (T) proxyConfigurator.makeProxy(object, implClass, context);
        }

        return object;
    }

    private List<ProxyConfigurator> initializeProxyConfiguratorsList() {
        Set<Class<? extends ProxyConfigurator>> proxyConfSet =
                context.getConfig().getScanner().getSubTypesOf(ProxyConfigurator.class);

        return proxyConfSet.stream().map(element -> {
            try {
                return element.getConstructor().newInstance();
            } catch (InstantiationException | IllegalAccessException | InvocationTargetException |
                     NoSuchMethodException e) {
                throw new RuntimeException(e);
            }
        }).collect(Collectors.toList());
    }
    private List<ObjectConfigurator> initializeObjectConfiguratorsList() {
        Set<Class<? extends ObjectConfigurator>> objectConfSet
                = context.getConfig().getScanner().getSubTypesOf(ObjectConfigurator.class);

        return objectConfSet.stream().map(element -> {
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
