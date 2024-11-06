package tests;

import models.UserBodyResponseModel;
import models.UserBodyRequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.*;
import static specs.DataSpec.*;

@Tag("Regress")
public class RestApiPatchTest extends TestBase {


    @Test
    @DisplayName("UpdateUser name and job not null")
    void patchUserTests() {
        UserBodyResponseModel userBody = new UserBodyResponseModel();
        userBody.setName("morpheus");
        userBody.setJob("zion resident");
        UserBodyRequestModel response = step("Make request", () ->
                given(dataUpdateUserRequestSpec)
                        .body(userBody)

                        .when()
                        .patch("/users/2")

                        .then()
                        .spec(dataUpdateUserResponseSpec)
                        .extract().as(UserBodyRequestModel.class));
        step("Check response", () -> {
            assertEquals(userBody.getName(), response.getName());
            assertEquals(userBody.getJob(), response.getJob());
            assertNotNull(response.getUpdatedAt());
        });
    }

    @Test
    @DisplayName("UpdateUser name null")
    void patchUserNotNameTests() {
        UserBodyResponseModel userBody = new UserBodyResponseModel();
        userBody.setJob("zion resident");
        UserBodyRequestModel response = step("Make request", () ->
                given(dataUpdateUserRequestSpec)
                        .body(userBody)

                        .when()
                        .patch("/users/2")

                        .then()
                        .spec(dataUpdateUserResponseSpec)
                        .extract().as(UserBodyRequestModel.class));
        step("Check response", () -> {
            assertNull(response.getName());
            assertEquals(userBody.getJob(), response.getJob());
            assertNotNull(response.getUpdatedAt());
        });
    }

    @Test
    @DisplayName("UpdateUser job null")
    void patchUserNotJobTests() {
        UserBodyResponseModel userBody = new UserBodyResponseModel();
        userBody.setName("morpheus");
        UserBodyRequestModel response = step("Make request", () ->
                given(dataUpdateUserRequestSpec)
                        .body(userBody)

                        .when()
                        .patch("/users/2")

                        .then()
                        .spec(dataUpdateUserResponseSpec)
                        .extract().as(UserBodyRequestModel.class));
        step("Check response", () -> {
            assertEquals(userBody.getName(), response.getName());
            assertNull(userBody.getJob());
            assertNotNull(response.getUpdatedAt());
        });
    }

    @Test
    @DisplayName("UpdateUser name and job null")
    void patchUserNotNameNotJobTests() {
        UserBodyResponseModel userBody = new UserBodyResponseModel();
        UserBodyRequestModel response = step("Make request", () ->
                given(dataUpdateUserRequestSpec)
                        .body(userBody)

                        .when()
                        .patch("/users/2")

                        .then()
                        .spec(dataUpdateUserResponseSpec)
                        .extract().as(UserBodyRequestModel.class));
        step("Check response", () -> {
            assertNull(userBody.getName());
            assertNull(userBody.getJob());
            assertNotNull(response.getUpdatedAt());
        });
    }
}

