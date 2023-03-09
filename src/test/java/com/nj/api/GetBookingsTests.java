package com.nj.api;

import com.nj.helper.RequestExecutor;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Test;

@Feature("Booking - Get All Bookings")
public class GetBookingsTests extends  AbstractBaseTest{
    @Severity(SeverityLevel.NORMAL)
    @Story("Get All Bookings")
    @Test
    void shouldGetAllBookings() {
        RequestExecutor.getAllBookings();
    }
}
