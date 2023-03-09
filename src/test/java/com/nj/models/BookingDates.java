package com.nj.models;

import lombok.*;

import java.time.LocalDate;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BookingDates {
    private LocalDate checkin;
    private LocalDate checkout;
}
