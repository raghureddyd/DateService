package ch.ubs.dateservice;

import ch.ubs.vo.FinancialInstrumentExpiration;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DateServiceTest {

    @Test
    void getFinancialInstrumentExpirationDateWithValidExpDate(){
        FinancialInstrumentExpiration financialInstrumentExpirationDate = DateService.validateComputeExpirationDate("01.02.2022", null, null);
        assertEquals(LocalDate.of(2022,02,01), financialInstrumentExpirationDate.expDate());
    }


    @Test
    void getFinancialInstrumentExpirationDateWithValidShortCut(){
        FinancialInstrumentExpiration financialInstrumentExpirationDate = DateService.validateComputeExpirationDate(null, "01.02.2022", "2d");
        assertEquals(LocalDate.of(2022,02,03), financialInstrumentExpirationDate.expDate());
    }


    @Test
    void getFinancialInstrumentExpirationDateWithWeekendExpDate(){
        FinancialInstrumentExpiration financialInstrumentExpirationDate = DateService.validateComputeExpirationDate("05.02.2022", null, null);
        assertEquals(LocalDate.of(2022,02,07), financialInstrumentExpirationDate.expDate());
    }

    @Test
    void getFinancialInstrumentExpirationDateWithHolidayExpDate(){
        FinancialInstrumentExpiration financialInstrumentExpirationDate = DateService.validateComputeExpirationDate(null, "25.07.2022", "1w");
        assertEquals(LocalDate.of(2022,8,02), financialInstrumentExpirationDate.expDate());
    }

    @Test
    void getFinancialInstrumentExpirationDateWithWeekendnHoliday(){
        FinancialInstrumentExpiration financialInstrumentExpirationDate = DateService.validateComputeExpirationDate("24.12.2022", null, null);
        assertEquals(LocalDate.of(2022,12,27), financialInstrumentExpirationDate.expDate());
    }

    @Test
    void getFinancialInstrumentExpirationDateWithYearEndTransition(){
        FinancialInstrumentExpiration financialInstrumentExpirationDate = DateService.validateComputeExpirationDate(null, "29.12.2022", "2d");
        assertEquals(LocalDate.of(2023,01,02), financialInstrumentExpirationDate.expDate());
    }

    @Test
    void getFinancialInstrumentExpirationDateWithOnlyPeriod(){
        FinancialInstrumentExpiration financialInstrumentExpirationDate = DateService.validateComputeExpirationDate(null, null, "2d");
        assertEquals(LocalDate.now().plusDays(2), financialInstrumentExpirationDate.expDate());
    }

}