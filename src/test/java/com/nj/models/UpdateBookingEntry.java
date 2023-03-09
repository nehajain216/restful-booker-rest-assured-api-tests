package com.nj.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateBookingEntry {
    private String firstname;
    private String lastname;
    private int totalPrice;
    private boolean depositPaid;
    private BookingDates bookingDates;
    private String additionalNeeds;

    private String newFirstname;
    private String newLastname;
    private int newTotalPrice;
    private boolean newDepositPaid;
    private BookingDates newBookingDates;
    private String newAdditionalNeeds;
}
