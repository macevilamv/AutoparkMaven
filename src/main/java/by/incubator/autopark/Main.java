package by.incubator.autopark;

import by.incubator.autopark.infrastructure.core.impl.ApplicationContext;
import by.incubator.autopark.infrastructure.core.impl.ObjectFactoryImpl;
import by.incubator.autopark.infrastructure.orm.service.PostgreDataBase;
import by.incubator.autopark.parsers.csv_parsers.FromCsvFileParser;
import by.incubator.autopark.utils.StringProcessor;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.util.HashMap;
import java.util.Map;


public class Main {
    private static final String TYPES_CSV_PATH = "src/main/resources/csv/types.csv";

    public static void main(String[] args) throws IOException, ParseException {
        Map<Class<?>, Class<?>> interfaceToImplementations = new HashMap<>();
        ApplicationContext context = new ApplicationContext("by", interfaceToImplementations);
        ObjectFactoryImpl objectFactory = new ObjectFactoryImpl(context);
        PostgreDataBase dataBase = objectFactory.createObject(PostgreDataBase.class);
//        dataBase.init();

        File csvFile = new File(TYPES_CSV_PATH);
        System.out.println(StringProcessor.processDataInCsvFormat(FromCsvFileParser.readFromFile(csvFile))
        );
    }
}
