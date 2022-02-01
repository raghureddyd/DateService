package ch.ubs.ch.ubs.util;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class PublicHolidaysCache {

    private static final Map<String, List<LocalDate>> country_year_holidays = new HashMap<>();

    static {
        //In realtime load from DB and cache here
        List<LocalDate> year2022Holidays = List.of(
          LocalDate.of(2022, 4, 15),
          LocalDate.of(2022, 4, 18),
          LocalDate.of(2022, 4, 25),
          LocalDate.of(2022, 5, 26),
          LocalDate.of(2022, 6, 06),
          LocalDate.of(2022, 8, 01),
          LocalDate.of(2022, 9, 12),
          LocalDate.of(2022, 12, 26));

        List<LocalDate> year2023Holidays = List.of(
          LocalDate.of(2023, 4, 7),
          LocalDate.of(2023, 4, 10),
          LocalDate.of(2023, 4, 17),
          LocalDate.of(2023, 5, 1),
          LocalDate.of(2023, 5, 18),
          LocalDate.of(2023, 5, 29),
          LocalDate.of(2023, 8, 1),
          LocalDate.of(2023, 9, 11),
          LocalDate.of(2023, 12, 25),
          LocalDate.of(2023, 12, 26));

        country_year_holidays.put("CH_2022", year2022Holidays);
        country_year_holidays.put("CH_2023", year2023Holidays);
    }

    private PublicHolidaysCache() {
    }

    public static List<LocalDate> getPublicHolidaysByCountryYear(String countryCode, int year) {
        return country_year_holidays.get(countryCode + "_" + year);
    }
}
