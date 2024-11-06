package tests;

import models.UserBodyResponseModel;
import models.UserBodyRequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.DataSpec.dataCreateUserRequestSpec;
import static specs.DataSpec.dataCreateUserResponseSpec;

@Tag("Regress")
public class RestApiPostTest extends TestBase {

    @Test
    @DisplayName("CreateUser name and job not null")
    void createUserTest() {
        UserBodyResponseModel userBody = new UserBodyResponseModel();
        userBody.setName("Andrey");
        userBody.setJob("QA Automation");
        UserBodyRequestModel response = step("Make request", () ->
                given(dataCreateUserRequestSpec)
                        .body(userBody)

                        .when()
                        .post("/users")

                        .then()
                        .spec(dataCreateUserResponseSpec)
                        .extract().as(UserBodyRequestModel.class));
        step("Check response", () -> {
            assertEquals(userBody.getName(), response.getName());
            assertEquals(userBody.getJob(), response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    @DisplayName("CreateUser name null")
    void createUserNotNameTest() {
        UserBodyResponseModel userBody = new UserBodyResponseModel();
        userBody.setJob("QA Automation");
        UserBodyRequestModel response = step("Make request", () ->
                given(dataCreateUserRequestSpec)
                        .body(userBody)

                        .when()
                        .post("/users")

                        .then()
                        .spec(dataCreateUserResponseSpec)
                        .extract().as(UserBodyRequestModel.class));
        step("Check response", () -> {
            assertNull(userBody.getName());
            assertEquals(userBody.getJob(), response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    @DisplayName("CreateUser job null")
    void createUserNotJobTest() {
        UserBodyResponseModel userBody = new UserBodyResponseModel();
        userBody.setName("Andrey");
        UserBodyRequestModel response = step("Make request", () ->
                given(dataCreateUserRequestSpec)
                        .body(userBody)

                        .when()
                        .post("/users")

                        .then()
                        .spec(dataCreateUserResponseSpec)
                        .extract().as(UserBodyRequestModel.class));
        step("Check response", () -> {
            assertEquals(userBody.getName(), response.getName());
            assertNull(userBody.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }

    @Test
    @DisplayName("CreateUser name and job null")
    void createUserNotNameNotJobTest() {
        UserBodyResponseModel userBody = new UserBodyResponseModel();

        UserBodyRequestModel response = step("Make request", () ->
                given(dataCreateUserRequestSpec)
                        .body(userBody)

                        .when()
                        .post("/users")

                        .then()
                        .spec(dataCreateUserResponseSpec)
                        .extract().as(UserBodyRequestModel.class));
        step("Check response", () -> {
            assertNull(userBody.getName(), response.getName());
            assertNull(userBody.getJob(), response.getJob());
            assertNotNull(response.getId());
            assertNotNull(response.getCreatedAt());
        });
    }
}
