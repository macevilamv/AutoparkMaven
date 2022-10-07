package by.incubator.autopark.infrastructure.orm.annotations;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

@Retention(RetentionPolicy.RUNTIME)
public @interface ID {
    String name() default "";
}
