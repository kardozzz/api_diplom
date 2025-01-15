package api;

import models.LoginRsModel;
import models.RequestModel;
import org.assertj.core.api.Assertions;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static specs.DataSpec.*;

public class LoginApi {

    public LoginRsModel doLoginPostRequest(RequestModel loginData) {
        LoginRsModel response = step("Сделать запрос логина", () ->
                given(commonRequest)
                        .body(loginData)

                        .when()
                        .post("/login")

                        .then()
                        .spec(response200)
                        .extract().as(LoginRsModel.class));

        return response;
    }

    public void checkToken(LoginRsModel response) {
        step("Проверить, что в ответе присутствует токен", () ->
                Assertions.assertThat(response.getToken()).isAlphanumeric());
    }

    public LoginRsModel doUnSuccessfulLoginPostRequest(RequestModel loginData) {
        LoginRsModel response = step("Сделать запрос логина", () ->
                given(commonRequest)
                        .body(loginData)

                        .when()
                        .post("/login")

                        .then()
                        .spec(response400)
                        .extract().as(LoginRsModel.class));

        return response;
    }

    public void checkErrorLogin(LoginRsModel response) {
        step("Проверить, что вернулась ошибка", () ->
                Assertions.assertThat(response.getError()).isEqualTo("Missing password"));
    }

}