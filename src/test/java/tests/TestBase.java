package tests;

import config.WebConfig;
import io.restassured.RestAssured;
import org.junit.jupiter.api.BeforeAll;
import org.aeonbits.owner.ConfigFactory;

public class TestBase {
    @BeforeAll
    public static void setUp() {
        WebConfig webConfig = ConfigFactory.create(WebConfig.class, System.getProperties());

        RestAssured.baseURI = webConfig.getBaseUri();
        RestAssured.basePath = webConfig.getBasePath();
    }
}

