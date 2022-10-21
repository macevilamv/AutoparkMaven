package by.incubator.autopark.parsers.csv_parsers;

import by.incubator.autopark.entity.RentEntity;
import by.incubator.autopark.parsers.interfaces.ParserRentInterface;
import by.incubator.autopark.rent.Rent;
import by.incubator.autopark.rent.Rentable;
import lombok.SneakyThrows;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static by.incubator.autopark.parsers.csv_parsers.FromCsvFileParser.readFromFile;
import static by.incubator.autopark.utils.StringProcessor.processDataInCsvFormat;

public class RentsParserFromCsvFile implements ParserRentInterface {
    private static final String RENTS_CSV_PATH = "src/main/resources/csv/rents.csv";

    public RentsParserFromCsvFile() {
    }

    public List<Rentable> loadRents() throws ParseException {
        List<Rentable> rentList = new ArrayList<>();
        List<String> rentsBuffer = createRentParametersList();

        for (String csvString : rentsBuffer) {
            rentList.add(createRent(csvString));
        }
        return rentList;
    }

    public List<RentEntity> loadRentEntities() throws IOException {
        List<RentEntity> rentEntitiesList = new ArrayList<>();
        List<String> rentEntitiesStringViews = createRentParametersList();

        for (String csvString : rentEntitiesStringViews) {
            rentEntitiesList.add(createRentEntity(csvString));
        }
        return rentEntitiesList;
    }

    @SneakyThrows
    private List<String> createRentParametersList() {
        File csvFile = new File(RENTS_CSV_PATH);
        List<String> rentsBuffer = processDataInCsvFormat(readFromFile(csvFile));

        return rentsBuffer;
    }

    @SneakyThrows
    private RentEntity createRentEntity(String csvStringOfRent) {
        String[] rentParametersBuffer = csvStringOfRent.split(",");
        Long vehicleId = Long.parseLong(rentParametersBuffer[CsvIndexingParser.CSV_INDEX.get("RENT-CSV_CAR_ID")]);;
        DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String rentDate = simpleDateFormat.format(simpleDateFormat.
                parse(rentParametersBuffer[CsvIndexingParser.CSV_INDEX.get("RENT-CSV_DATE")]));;
        Double cost = Double.parseDouble(rentParametersBuffer[CsvIndexingParser.CSV_INDEX.get("RENT-CSV_COST")]);

        return new RentEntity(vehicleId, rentDate, cost);
    }


    private Rent createRent(String csvStringOfRent) throws ParseException {
        String[] rentParametersBuffer = csvStringOfRent.split(",");
        Long vehicleId = Long.parseLong(rentParametersBuffer[CsvIndexingParser.CSV_INDEX.get("RENT-CSV_CAR_ID")]);;
        DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String rentDate = simpleDateFormat.format(simpleDateFormat.
                parse(rentParametersBuffer[CsvIndexingParser.CSV_INDEX.get("RENT-CSV_DATE")]));;
        double cost = Double.parseDouble(rentParametersBuffer[CsvIndexingParser.CSV_INDEX.get("RENT-CSV_COST")]);

        return new Rent(vehicleId, rentDate, cost);
    }
}
