package by.incubator.autopark.parsers.csv_parsers;

import by.incubator.autopark.entity.EngineEntity;
import by.incubator.autopark.engine.DieselEngine;
import by.incubator.autopark.engine.ElectricalEngine;
import by.incubator.autopark.engine.GasolineEngine;
import by.incubator.autopark.engine.Startable;

import java.util.ArrayList;
import java.util.List;

public class EngineParser {
    public static Startable parseEngine(String name, String[] vehicleParametersBuffer) {
        if (name.equals("Gasoline")) {
            return new GasolineEngine(
                    Double.parseDouble(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_VOLUME")]),
                    Double.parseDouble(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_FUEL_CONS")]),
                    Double.parseDouble(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_TANK_SIZE")]));
        } else if (name.equals("Diesel")) {
            return new DieselEngine(
                    Double.parseDouble(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_VOLUME")]),
                    Double.parseDouble(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_FUEL_CONS")]),
                    Double.parseDouble(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_TANK_SIZE")])
            );
        } else if (name.equals("Electrical")) {
            return new ElectricalEngine(
                    Double.parseDouble(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_EL_CONS")]),
                    Double.parseDouble(vehicleParametersBuffer[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_BATTERY")])
            );
        } else {
            throw new RuntimeException("Such engine doesn't exist");
        }
    }
    public static List<EngineEntity> loadEngineEntities() {
        List<String> vehicleParametersBuffer = VehicleParserFromCsvFile.generateVehicleParameters();
        List<EngineEntity> engineEntities = new ArrayList<>();

        for (String csvString : vehicleParametersBuffer) {
            engineEntities.add(createEngineEntity(csvString));
        }

        return engineEntities;
    }

    static EngineEntity createEngineEntity(String csvStringOfEngine) {
        String[] engineParameters = csvStringOfEngine.split(",");
        String engineType = engineParameters[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_ENGINE")];
        Double consumption;
        Double capacity;
        Double engineVolume;

        if (engineType.equalsIgnoreCase("electrical")) {
            consumption = Double.parseDouble(engineParameters[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_EL_CONS")]);
            capacity = Double.parseDouble(engineParameters[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_BATTERY")]);
            engineVolume = 0.0d;
        } else {
            engineType = engineParameters[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_ENGINE")];
            engineVolume =  Double.parseDouble(engineParameters[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_VOLUME")]);
            capacity = Double.parseDouble(engineParameters[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_TANK_SIZE")]);
            consumption = Double.parseDouble(engineParameters[CsvIndexingParser.CSV_INDEX.get("VEHICLE-CSV_FUEL_CONS")]);
       }

       return new EngineEntity(engineType, capacity, consumption, engineVolume);
    }
}
