package by.incubator.autopark.parsers.interfaces;

import by.incubator.autopark.vehicle.TypeInterface;

import java.io.IOException;
import java.util.List;

public interface ParserVehicleTypeInterface {
    List<TypeInterface> loadVehicleTypes() throws IOException;
}
