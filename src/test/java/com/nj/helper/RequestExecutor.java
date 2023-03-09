package com.nj.helper;

import com.nj.api.AbstractBaseTest;
import com.nj.models.Booking;
import com.nj.models.BookingId;
import io.restassured.common.mapper.TypeRef;
import io.restassured.http.ContentType;
import io.restassured.response.ValidatableResponse;

import java.util.List;
import java.util.Optional;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class RequestExecutor extends AbstractBaseTest{

    public static Optional<Integer> createBooking(Booking booking, int expectedStatus){
        ValidatableResponse validatableResponse = given()
                .contentType(ContentType.JSON)
                .body(booking)
                .log().everything()
                .when()
                .post(config.getCreateBookingApiPath())
                .then()
                .log().everything()
                .statusCode(expectedStatus);

        if(expectedStatus == 200) {
            int bookingId = validatableResponse.assertThat()
                    .body("booking.firstname", equalTo(booking.getFirstname()))
                    .body("booking.lastname", equalTo(booking.getLastname()))
                    .body("booking.totalprice", equalTo(booking.getTotalPrice()))
                    .body("booking.depositpaid", equalTo(booking.isDepositPaid()))
                    .body("booking.bookingdates.checkin", equalTo(booking.getBookingDates().getCheckin().toString()))
                    .body("booking.bookingdates.checkout", equalTo(booking.getBookingDates().getCheckout().toString()))
                    .extract().path("bookingid");
            return Optional.of(bookingId);
        }
        return Optional.empty();
    }

    public static void updateBooking(Booking booking, int bookingId, int expectedStatus){
        given()
                .contentType(ContentType.JSON)
                .body(booking)
                .log().everything()
                .auth()
                .preemptive()
                .basic(config.getUsername(), config.getPassword())
                .when().patch(config.getUpdateBookingApiPath(), bookingId)
                .then().log().everything()
                .assertThat().statusCode(200)
                .assertThat().body("firstname", equalTo(booking.getFirstname()))
                ;
    }

    public static void deleteBooking(int bookingId) {
        given().contentType(ContentType.JSON)
                .auth()
                .preemptive()
                .basic(config.getUsername(),config.getPassword())
                .when().delete(config.getDeleteBookingApiPath(),bookingId)
                .then().log().body()
                .assertThat().statusCode(201)
        ;
    }

    public static void getBookingById(Booking booking,int bookingId){
        given()
                .contentType(ContentType.JSON)
                .when()
                .get(config.getGetBookingByIdApiPath(), bookingId)
                .then()
                .statusCode(200)
                .assertThat().body("firstname", equalTo(booking.getFirstname()))
                .assertThat().body("lastname", equalTo(booking.getLastname()))
        ;
    }

    public static void getAllBookings(){
        List<BookingId> bookingIds = given()
                .contentType(ContentType.JSON)
                .when()
                .get(config.getGetAllBookingsApiPath())
                .then()
                .statusCode(200)
                .extract().body().as(new TypeRef<>() {
                });
        assertTrue(bookingIds.size() > 0);
    }
}
