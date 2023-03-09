package com.nj.api;

import com.nj.models.AuthRequest;
import com.nj.models.AuthResponse;
import io.qameta.allure.Feature;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import io.qameta.allure.Story;
import io.restassured.http.ContentType;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@Feature("User Authentication - Login Functionality")
public class AuthenticationApiTests extends AbstractBaseTest {
    @Severity(SeverityLevel.BLOCKER)
    @Test
    @Story("Successful login")
    void shouldAuthenticateSuccessfully() {
        AuthRequest authRequest = new AuthRequest(config.getUsername(), config.getPassword());
        AuthResponse authResponse = given()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .when()
                .post(config.getAuthApiPath())
                .then()
                .statusCode(200)
                .body("token", CoreMatchers.notNullValue())
                .extract().body().as(AuthResponse.class);
        assertNotNull(authResponse.getToken());
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test
    @Story("UnSuccessful login")
    void shouldReturnForbiddenForInvalidCredentials() {
        AuthRequest authRequest = new AuthRequest("dummy", "dummy");
        given()
                .contentType(ContentType.JSON)
                .body(authRequest)
                .when()
                .post(config.getAuthApiPath())
                .then()
                .statusCode(200)
                .body("reason", CoreMatchers.equalTo("Bad credentials"));
    }
}
