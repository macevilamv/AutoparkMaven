package by.incubator.autopark.infrastructure.configurators;

import by.incubator.autopark.infrastructure.core.Context;

public interface ObjectConfigurator {
    void configure(Object object, Context context);
}
