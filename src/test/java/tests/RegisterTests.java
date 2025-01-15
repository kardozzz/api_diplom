package tests;

import api.RegisterApi;
import models.RegisterRsModel;
import models.RequestModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("api")
@DisplayName("Тесты на регистрацию")
public class RegisterTests extends TestBase {

    RegisterApi registerApi = new RegisterApi();

    @Test
    @DisplayName("Проверка успешной регистрации")
    void successfulRegistrationTest() {
        RequestModel request = new RequestModel();
        request.setEmail("eve.holt@reqres.in");
        request.setPassword("pistol");
        RegisterRsModel response = registerApi.doSuccessfulRegisterPostRequest(request);
        registerApi.checkTokenAndId(response);
    }

    @Test
    @DisplayName("Проверка не успешной регистрации")
    void unsuccessfulRegistrationTest() {
        RequestModel request = new RequestModel();
        request.setEmail("sydney@fif");
        RegisterRsModel response = registerApi.doUnSuccessfulRegisterPostRequest(request);
        registerApi.checkErrorRegister(response);
    }
}