package by.incubator.autopark.ParsingUtils;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringProcessor {
    public static List<String> proceedStrings(List<String> buffer) {
        Pattern pattern = Pattern.compile("(\")(\\d+)(,)(\\d+)(\")");
        int bufferSize = buffer.size();

        for (int i = 0; i < bufferSize; i++) {
            Matcher matcher = pattern.matcher(buffer.get(i));
            buffer.remove(i);
            buffer.add(i, matcher.replaceAll("$2.$4"));
        }
        return buffer;
    }
}
