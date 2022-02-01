package ch.ubs.client;

import ch.ubs.dateservice.DateService;
import ch.ubs.vo.FinancialInstrumentExpiration;
import org.apache.commons.lang3.StringUtils;

import java.util.Scanner;

public class DateServiceClient {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please enter either expiration date or period od expiration ");
        String input = scanner.nextLine();
        //No validation added for input format as it's not part of the requirements
        if (StringUtils.isNotEmpty(input)) {
            FinancialInstrumentExpiration financialInstrumentExpirationDate;
            if (input.contains(".")) {
                financialInstrumentExpirationDate = DateService.validateComputeExpirationDate(input, "", "");
            } else {
                financialInstrumentExpirationDate = DateService.validateComputeExpirationDate("", "", input);
            }
            //use logs in real time
            System.out.println("Exp Date --> " + financialInstrumentExpirationDate.expDate());
            System.out.println("Error Msg --> " + financialInstrumentExpirationDate.errorMsg());
        }
        scanner.close();
    }
}
