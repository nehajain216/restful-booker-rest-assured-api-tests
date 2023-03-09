package com.nj.api;

import com.nj.helper.CsvInputHelper;
import com.nj.helper.RequestExecutor;
import com.nj.models.Booking;
import com.opencsv.exceptions.CsvValidationException;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.io.IOException;
import java.util.stream.Stream;

@Feature("Booking - Create Booking")
public class CreateBookingTests extends AbstractBaseTest {
    @Severity(SeverityLevel.CRITICAL)
    @Story("Create Booking")
    @ParameterizedTest
    @MethodSource("createBookingArguments")
    void shouldCreateBookingSuccessfully(Booking booking, int expectedStatus) {
        RequestExecutor.createBooking(booking,expectedStatus);
    }

    static Stream<Arguments> createBookingArguments() throws IOException, CsvValidationException {
        String filePath = config.getCreateBookingInputCsvPath();
        return CsvInputHelper.createBookingArguments(filePath);
    }
}
