package by.incubator.autopark.infrastructure.configurators.impl;

import by.incubator.autopark.infrastructure.configurators.ObjectConfigurator;
import by.incubator.autopark.infrastructure.core.Context;
import by.incubator.autopark.infrastructure.core.annotations.Autowired;
import lombok.SneakyThrows;

import java.lang.reflect.Field;

public class AutoWiredObjectConfigurators implements ObjectConfigurator {
    private Object element;

    @SneakyThrows
    @Override
    public void configure(Object object, Context context) {
        for (Field field : object.getClass()
                .getDeclaredFields()) {
            if (field.isAnnotationPresent(Autowired.class)) {
                element = context.getObject(field.getType());
                field.setAccessible(true);
                field.set(object, element);
            }
        }
    }
}
