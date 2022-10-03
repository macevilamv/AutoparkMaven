package by.incubator.autopark.infrastructure.configurators.impl;

import by.incubator.autopark.infrastructure.configurators.ObjectConfigurator;
import by.incubator.autopark.infrastructure.core.Context;
import by.incubator.autopark.infrastructure.core.annotations.Property;
import lombok.SneakyThrows;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class PropertyObjectConfigurator implements ObjectConfigurator {
    private final Map<String, String> properties;

    @SneakyThrows
    public PropertyObjectConfigurator() {
        URL path = this.getClass().getClassLoader()
                .getResource("application.properties");

        if (path == null) {
            throw new FileNotFoundException(String.format("File %s not found",
                    "application.properties"));
        }
        Stream<String> lines = new BufferedReader(
                new InputStreamReader(path.openStream())).lines();
        properties = lines.map(line -> line.split("="))
                .collect(Collectors.toMap(arr -> arr[0], arr -> arr[1]));
    }

    @Override
    @SneakyThrows
    public void configure(Object object, Context context) {
        String key;

        if (object.getClass().isAnnotationPresent(Property.class)) {
            if (object.getClass().getAnnotation(Property.class).value().equals("")) {
                key = object.getClass().getSimpleName();
            } else {
                key = object.getClass().getAnnotation(Property.class).value();
            }
            object = properties.get(key);
        }
    }
}