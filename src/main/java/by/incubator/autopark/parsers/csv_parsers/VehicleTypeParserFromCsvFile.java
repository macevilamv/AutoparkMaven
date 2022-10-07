package by.incubator.autopark.parsers.csv_parsers;

import by.incubator.autopark.dto.entity.TypeEntity;
import by.incubator.autopark.vehicle.VehicleType;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static by.incubator.autopark.parsers.csv_parsers.FromCsvFileParser.readFromFile;
import static by.incubator.autopark.utils.StringProcessor.processDataInCsvFormat;

public class VehicleTypeParserFromCsvFile {
    private static final String TYPES_CSV_PATH = "src/main/resources/csv/types.csv";

    public VehicleTypeParserFromCsvFile() {
    }

    public List<VehicleType> loadVehicleTypes() throws IOException {
        List<VehicleType> typesList = new ArrayList<>();
        List<String> typesBuffer = createTypesParametersList();

        for (String csvString : typesBuffer) {
            typesList.add(createType(csvString));
        }
        return typesList;
    }

    public List<TypeEntity> loadTypeEntities() throws IOException {
        List<TypeEntity> typeEntitiesList = new ArrayList<>();
        List<String> typeEntitiesStringViews = createTypesParametersList();

        for (String csvString : typeEntitiesStringViews) {
            typeEntitiesList.add(createTypeEntity(csvString));
        }
        return typeEntitiesList;
    }

    private VehicleType createType(String csvStringOfType) {
        String[] typeParametersBuffer = csvStringOfType.split(",");
        int id = Integer.parseInt(typeParametersBuffer[CsvIndexingParser.CSV_INDEX.get("TYPE-CSV_ID")]);;
        String typeName = typeParametersBuffer[CsvIndexingParser.CSV_INDEX.get("TYPE-CSV_NAME")];;
        double tax = Double.parseDouble(typeParametersBuffer[CsvIndexingParser.CSV_INDEX.get("TYPE-CSV_TAX")]);;

        return new VehicleType(id, typeName, tax);
    }

    private TypeEntity createTypeEntity(String csvStringOfType) {
        String[] typeParametersBuffer = csvStringOfType.split(",");
        Integer id = Integer.parseInt(typeParametersBuffer[CsvIndexingParser.CSV_INDEX.get("TYPE-CSV_ID")]);;
        String typeName = typeParametersBuffer[CsvIndexingParser.CSV_INDEX.get("TYPE-CSV_NAME")];;
        Double tax = Double.parseDouble(typeParametersBuffer[CsvIndexingParser.CSV_INDEX.get("TYPE-CSV_TAX")]);;

        return new TypeEntity(id, typeName, tax);
    }

    private List<String> createTypesParametersList() throws IOException {
        File csvFile = new File(TYPES_CSV_PATH);

        return processDataInCsvFormat(readFromFile(csvFile));
    }
}
