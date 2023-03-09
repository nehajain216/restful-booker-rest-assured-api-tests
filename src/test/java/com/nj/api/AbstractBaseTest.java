package com.nj.api;

import com.nj.config.ConfigLoader;
import com.nj.config.Configuration;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;

public class AbstractBaseTest {
    public static Configuration config;

    @BeforeAll
    static void beforeAll() {
        config = ConfigLoader.loadConfiguration();
        RestAssured.baseURI = config.getBaseUrl();
    }
}
