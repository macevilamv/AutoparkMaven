package by.incubator.autopark.infrastructure.config.impl;

import by.incubator.autopark.infrastructure.config.Config;
import by.incubator.autopark.infrastructure.core.Scanner;
import lombok.AllArgsConstructor;

import java.util.Map;
import java.util.Set;

@AllArgsConstructor
public class JavaConfig implements Config {
    private final Scanner scanner;
    private final Map<Class<?>, Class<?>> interfaceToImplementation;

    @Override
    public <T> Class<? extends T> getImplementation(Class<T> target) {
        Set<Class<? extends T>> subTypes = scanner.getSubTypesOf(target);

        if (interfaceToImplementation.containsKey(target)) {
            return (Class<? extends T>) interfaceToImplementation.get(target);
        }

        if (subTypes.size() != 1) {
            throw new RuntimeException("target interface has 0 or more then one implementation");
        } else {
            return subTypes.stream().findFirst().get();
        }
    }

    @Override
    public Scanner getScanner() {
        return this.scanner;
    }
}