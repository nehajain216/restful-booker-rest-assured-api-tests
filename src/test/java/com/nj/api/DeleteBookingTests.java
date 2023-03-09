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
import java.util.Optional;
import java.util.stream.Stream;
import static org.junit.jupiter.api.Assertions.fail;

@Feature("Booking - Delete Booking")
public class DeleteBookingTests extends AbstractBaseTest {
    @Severity(SeverityLevel.CRITICAL)
    @Story("Delete Booking")
    @ParameterizedTest
    @MethodSource("deleteBookingArguments")
    void shouldDeleteBookingSuccessfully(Booking booking, int expectedStatus) {
        Optional<Integer> bookingId = RequestExecutor.createBooking(booking,expectedStatus);
        if(bookingId.isPresent())
        {
            RequestExecutor.deleteBooking(bookingId.get());
        }
        else {
            fail("Failed to delete booking "+bookingId);
        }
    }

    static Stream<Arguments> deleteBookingArguments() throws IOException, CsvValidationException {
        String filePath = config.getDeleteBookingInputCsvPath();
        return CsvInputHelper.createBookingArguments(filePath);
    }
}
