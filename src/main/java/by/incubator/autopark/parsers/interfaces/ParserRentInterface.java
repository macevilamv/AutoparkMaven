package by.incubator.autopark.parsers.interfaces;

import by.incubator.autopark.rent.Rentable;

import java.text.ParseException;
import java.util.List;

public interface ParserRentInterface {
    List<Rentable> loadRents() throws ParseException;
}
