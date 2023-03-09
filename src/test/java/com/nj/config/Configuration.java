package com.nj.config;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Configuration {
    private String baseUrl;
    private String username;
    private String password;
    private String testDataDirectory;
    private String authApiPath;
    private String createBookingApiPath;
    private String updateBookingApiPath;
    private String deleteBookingApiPath;
    private String getAllBookingsApiPath;
    private String getBookingByIdApiPath;

    public String getCreateBookingInputCsvPath() {
        return this.getTestDataDirectory() + "/create-bookings.csv";
    }

    public String getUpdateBookingInputCsvPath() {
        return this.getTestDataDirectory() + "/update-bookings.csv";
    }

    public String getDeleteBookingInputCsvPath() {
        return this.getTestDataDirectory() + "/update-bookings.csv";
    }

    public String getBookingByIdInputCsvPath() {
        return this.getTestDataDirectory() + "/get-booking-by-id.csv";
    }
}
