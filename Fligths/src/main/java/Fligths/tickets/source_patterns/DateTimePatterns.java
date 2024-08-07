package Fligths.tickets.source_patterns;

import java.time.format.DateTimeFormatter;

public class DateTimePatterns {
    private static DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd.MM.yy");
    private static DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H:m");

    public static DateTimeFormatter getDateFormatter() {
        return dateFormatter;
    }
    public static DateTimeFormatter getTimeFormatter() {
        return timeFormatter;
    }
}
