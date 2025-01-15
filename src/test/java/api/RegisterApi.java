package api;

import models.RegisterRsModel;
import models.RequestModel;
import org.assertj.core.api.Assertions;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DataSpec.*;

public class RegisterApi {
    public RegisterRsModel doSuccessfulRegisterPostRequest(RequestModel request) {
        RegisterRsModel response = step("Сделать запрос регистрации", () ->
                given(commonRequest)
                        .body(request)

                        .when()
                        .post("/register")

                        .then()
                        .spec(response200)
                        .extract().as(RegisterRsModel.class));
        return response;
    }

    public void checkTokenAndId(RegisterRsModel response) {
        step("Проверить, что id и token not null", () -> {
            Assertions.assertThat(response.getId()).asInt();
            Assertions.assertThat(response.getToken()).isAlphanumeric();
        });
    }

    public RegisterRsModel doUnSuccessfulRegisterPostRequest(RequestModel request) {
        RegisterRsModel response = step("Сделать запрос регистрации", () ->
                given(commonRequest)
                        .body(request)

                        .when()
                        .post("/register")

                        .then()
                        .spec(response400)
                        .extract().as(RegisterRsModel.class));
        return response;
    }

    public void checkErrorRegister(RegisterRsModel response) {
        step("Проверить, что id и token not null", () -> {
            Assertions.assertThat(response.getError()).isEqualTo("Missing password");
        });
    }
}