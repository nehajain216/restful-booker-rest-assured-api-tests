package com.nj.models;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class BookingId {
    @JsonProperty("bookingid")
    private Long bookingId;
}
