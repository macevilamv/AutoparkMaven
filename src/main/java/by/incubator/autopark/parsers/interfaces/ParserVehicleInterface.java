package by.incubator.autopark.parsers.interfaces;

import by.incubator.autopark.vehicle.Driveable;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public interface ParserVehicleInterface {
    List<Driveable> loadVehicles() throws IOException, ParseException;
}
