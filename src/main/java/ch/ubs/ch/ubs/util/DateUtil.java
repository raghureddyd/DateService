package ch.ubs.ch.ubs.util;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;

public class DateUtil {

    public static DayOfWeek getDateDayOfWeek(LocalDate expDate) {
        return expDate.getDayOfWeek();
    }

    public static LocalDate addPeriodToDate(LocalDate expDate, Period days) {
        return expDate.plus(days);
    }

    public static LocalDate getLocalDateFromString(String expDateStr) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return LocalDate.parse(expDateStr, formatter);
    }

    private DateUtil() {
    }
}
