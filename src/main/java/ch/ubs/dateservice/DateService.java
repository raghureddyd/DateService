package ch.ubs.dateservice;

import ch.ubs.ch.ubs.util.DateUtil;
import ch.ubs.ch.ubs.util.PeriodType;
import ch.ubs.ch.ubs.util.PublicHolidaysCache;
import ch.ubs.vo.FinancialInstrumentExpiration;
import org.apache.commons.lang3.StringUtils;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class DateService {

    public static final String ERROR_MSG_CONST = "The expiration date falls on weekend/public holiday and the next valid date is %s";

    private DateService() {
    }

    public static FinancialInstrumentExpiration validateComputeExpirationDate(String expDateStr, String issueDateStr, String periodOfExpiration) {
        FinancialInstrumentExpiration financialInstrumentExpiration = null;
        Period period;
        LocalDate expDate;
        if (StringUtils.isNotEmpty(expDateStr)) {
            expDate = DateUtil.getLocalDateFromString(expDateStr);
            financialInstrumentExpiration = getFinancialInstrumentExpiration(expDate);
        } else {
            if (Objects.nonNull(periodOfExpiration)) {
                period = getPeriodObjFromStrInput(periodOfExpiration);
                if (StringUtils.isNotEmpty(issueDateStr)) {
                    expDate = DateUtil.addPeriodToDate(DateUtil.getLocalDateFromString(issueDateStr), period);
                } else {
                    expDate = DateUtil.addPeriodToDate(LocalDate.now(), period);
                }
                financialInstrumentExpiration = getFinancialInstrumentExpiration(expDate);
            }
        }
        return financialInstrumentExpiration;
    }

    private static FinancialInstrumentExpiration getFinancialInstrumentExpiration(LocalDate expDate) {
        boolean isExpDateWeekendOrHoliday = false;
        String errorMsg = "";
        while (isExpDatePublicHoliday(expDate) || isExpDateWeekEnd(expDate)) {
            expDate = DateUtil.addPeriodToDate(expDate, Period.ofDays(1));
            isExpDateWeekendOrHoliday = true;
        }
        if (isExpDateWeekendOrHoliday) {
            String formattedDate = expDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy"));
            errorMsg = String.format(ERROR_MSG_CONST, formattedDate);
        }
        return new FinancialInstrumentExpiration(errorMsg, expDate);
    }

    private static Period getPeriodObjFromStrInput(String periodOfExpiration) {
        Period period = null;
        String periodType = periodOfExpiration.substring(periodOfExpiration.length() - 1).toUpperCase();
        int periodValue = Integer.parseInt(periodOfExpiration.substring(0, periodOfExpiration.length() - 1));
        if (PeriodType.D.equals(PeriodType.valueOf(periodType))) {
            period = Period.ofDays(periodValue);
        } else if (PeriodType.W.equals(PeriodType.valueOf(periodType))) {
            period = Period.ofWeeks(periodValue);
        } else if (PeriodType.M.equals(PeriodType.valueOf(periodType))) {
            period = Period.ofMonths(periodValue);
        } else if (PeriodType.Y.equals(PeriodType.valueOf(periodType))) {
            period = Period.ofYears(periodValue);
        }
        return period;
    }

    private static boolean isExpDateWeekEnd(LocalDate expDate) {
        DayOfWeek dateDayOfWeek = DateUtil.getDateDayOfWeek(expDate);
        return dateDayOfWeek.getValue() == DayOfWeek.SATURDAY.getValue() || dateDayOfWeek.getValue() == DayOfWeek.SUNDAY.getValue();
    }

    private static boolean isExpDatePublicHoliday(LocalDate expDate) {
        return PublicHolidaysCache.getPublicHolidaysByCountryYear("CH", expDate.getYear()).contains(expDate);
    }
}
