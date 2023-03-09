package com.nj.api;

import com.nj.helper.CsvInputHelper;
import com.nj.helper.RequestExecutor;
import com.nj.models.Booking;
import com.nj.models.UpdateBookingEntry;
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

@Feature("Booking - Update Booking")
public class UpdateBookingTests extends AbstractBaseTest {
    @Severity(SeverityLevel.CRITICAL)
    @Story("Update Booking")
    @ParameterizedTest
    @MethodSource("updateBookingArguments")
    void shouldUpdateBookingSuccessfully(UpdateBookingEntry updateBookingEntry, int expectedStatus) {
        Booking booking = new Booking(
                updateBookingEntry.getFirstname(),
                updateBookingEntry.getLastname(),
                updateBookingEntry.getTotalPrice(),
                updateBookingEntry.isDepositPaid(),
                updateBookingEntry.getBookingDates(),
                updateBookingEntry.getAdditionalNeeds()
        );
        Optional<Integer> bookingId = RequestExecutor.createBooking(booking, expectedStatus);

        if(bookingId.isPresent()){
            Booking updatedBooking = new Booking(
                    updateBookingEntry.getNewFirstname(),
                    updateBookingEntry.getNewLastname(),
                    updateBookingEntry.getNewTotalPrice(),
                    updateBookingEntry.isNewDepositPaid(),
                    updateBookingEntry.getNewBookingDates(),
                    updateBookingEntry.getNewAdditionalNeeds()
            );
            RequestExecutor.updateBooking(updatedBooking,bookingId.get(),expectedStatus);
        }
        else {
            fail("Failed to delete booking "+bookingId);
        }
    }

    static Stream<Arguments> updateBookingArguments() throws IOException, CsvValidationException {
        String filePath = config.getTestDataDirectory() + "/update-bookings.csv";
        return CsvInputHelper.updateBookingArguments(filePath);
    }

}
