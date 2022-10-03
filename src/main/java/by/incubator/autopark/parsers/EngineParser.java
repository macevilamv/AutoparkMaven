package by.incubator.autopark.parsers;

import by.incubator.autopark.engine.DieselEngine;
import by.incubator.autopark.engine.ElectricalEngine;
import by.incubator.autopark.engine.GasolineEngine;
import by.incubator.autopark.engine.Startable;

import java.util.Map;

public class EngineParser {
    public static Startable parseEngine(String name, String[] vehicleParametersBuffer, Map<String, Integer> config) {
        if (name.equals("Gasoline")) {
            return new GasolineEngine(
                    Double.parseDouble(vehicleParametersBuffer[config.get("VEHICLE-CSV_VOLUME")]),
                    Double.parseDouble(vehicleParametersBuffer[config.get("VEHICLE-CSV_FUEL_CONS")]),
                    Double.parseDouble(vehicleParametersBuffer[config.get("VEHICLE-CSV_TANK_SIZE")]));
        } else if (name.equals("Diesel")) {
            return new DieselEngine(
                    Double.parseDouble(vehicleParametersBuffer[config.get("VEHICLE-CSV_VOLUME")]),
                    Double.parseDouble(vehicleParametersBuffer[config.get("VEHICLE-CSV_FUEL_CONS")]),
                    Double.parseDouble(vehicleParametersBuffer[config.get("VEHICLE-CSV_TANK_SIZE")])
            );
        } else if (name.equals("Electrical")) {
            return new ElectricalEngine(
                    Double.parseDouble(vehicleParametersBuffer[config.get("VEHICLE-CSV_EL_CONS")]),
                    Double.parseDouble(vehicleParametersBuffer[config.get("VEHICLE-CSV_BATTERY")])
            );
        } else {
            throw new RuntimeException("Such engine doesn't exist");
        }
    }
}
