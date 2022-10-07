package by.incubator.autopark.parsers.csv_parsers;

import by.incubator.autopark.rent.Rent;

import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import static by.incubator.autopark.parsers.csv_parsers.FromCsvFileParser.readFromFile;
import static by.incubator.autopark.utils.StringProcessor.processDataInCsvFormat;

public class RentsParserFromCsvFile {
    private static final String RENTS_CSV_PATH = "src/main/resources/csv/rents.csv";

    public RentsParserFromCsvFile() {
    }

    public List<Rent> loadRents() throws IOException, ParseException {
        List<Rent> rentList = new ArrayList<>();
        File csvFile = new File(RENTS_CSV_PATH);
        List<String> rentsBuffer = processDataInCsvFormat(readFromFile(csvFile));

        for (String csvString : rentsBuffer) {
            rentList.add(createRent(csvString));
        }
        return rentList;
    }

    private Rent createRent(String csvStringOfRent) throws ParseException {
        String[] rentParametersBuffer = csvStringOfRent.split(",");
        int vehicleId = Integer.parseInt(rentParametersBuffer[CsvIndexingParser.CSV_INDEX.get("RENT-CSV_CAR_ID")]);;
        DateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy");
        String rentDate = simpleDateFormat.format(simpleDateFormat.
                parse(rentParametersBuffer[CsvIndexingParser.CSV_INDEX.get("RENT-CSV_DATE")]));;
        double cost = Double.parseDouble(rentParametersBuffer[CsvIndexingParser.CSV_INDEX.get("RENT-CSV_COST")]);

        return new Rent(vehicleId, rentDate, cost);
    }
}
