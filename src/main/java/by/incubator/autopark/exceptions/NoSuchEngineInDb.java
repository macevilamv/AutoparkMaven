package by.incubator.autopark.exceptions;

public class NoSuchEngineInDb extends RuntimeException {
    public NoSuchEngineInDb() {
        super("Error! There is no such engine in database.");
    }
}
