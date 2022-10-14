package by.incubator.autopark.infrastructure.config;

import by.incubator.autopark.infrastructure.core.Scanner;

public interface Config {
    <T> Class<? extends T> getImplementation(Class<T> target);
    Scanner getScanner();
}
