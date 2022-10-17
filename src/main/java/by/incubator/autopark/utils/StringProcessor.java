package by.incubator.autopark.utils;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Locale;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcessor {
    public static List<String> processDataInCsvFormat(List<String> buffer) {
        Pattern pattern = Pattern.compile("(\")(\\d+)(,)(\\d+)(\")");
        int bufferSize = buffer.size();

        for (int i = 0; i < bufferSize; i++) {
            Matcher matcher = pattern.matcher(buffer.get(i));
            buffer.remove(i);
            buffer.add(i, matcher.replaceAll("$2.$4"));
        }
        return buffer;
    }

    public static String generateGetterName(Field field) {
        return "get" + field.getName()
                .substring(0, 1).toUpperCase(Locale.ROOT)
                + field.getName().substring(1);
    }

    public static String generateSetterName(Field field) {
        return "set" + field.getName()
                .substring(0, 1).toUpperCase(Locale.ROOT)
                + field.getName().substring(1);
    }
}
