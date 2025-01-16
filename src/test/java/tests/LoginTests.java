package tests;

import api.LoginApi;
import models.LoginRsModel;
import models.RequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("api")
@DisplayName("Тесты на логин")
public class LoginTests extends TestBase {

    LoginApi loginApi = new LoginApi();

    @Test
    @DisplayName("Проверка успешного логина")
    void successfulLoginTest() {
        RequestModel loginData = new RequestModel();
        loginData.setEmail("eve.holt@reqres.in");
        loginData.setPassword("cityslicka");
        LoginRsModel response = loginApi.doLoginPostRequest(loginData);
        loginApi.checkToken(response);
    }

    @Test
    @DisplayName("Проверка не успешного логина")
    void unSuccessfulLoginTest() {
        RequestModel loginData = new RequestModel();
        loginData.setEmail("peter@klaven");
        LoginRsModel response = loginApi.doUnSuccessfulLoginPostRequest(loginData);
        loginApi.checkErrorLogin(response);
    }
}