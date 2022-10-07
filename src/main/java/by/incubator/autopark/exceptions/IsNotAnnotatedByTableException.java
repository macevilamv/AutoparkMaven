package by.incubator.autopark.exceptions;

public class IsNotAnnotatedByTableException extends RuntimeException {
    public IsNotAnnotatedByTableException() {
        super("Error! Class is not annotated by @Table annotation.");
    }
}
