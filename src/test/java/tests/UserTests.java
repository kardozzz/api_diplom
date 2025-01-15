package tests;

import api.UsersApi;
import models.PatchRsModel;
import models.PutRsModel;
import models.RequestModel;
import models.UserListRsModel;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;


@Tag("api")
@DisplayName("Тесты с базой пользователей")
public class UserTests extends TestBase {

    UsersApi usersApi = new UsersApi();

    @Test
    @DisplayName("Проверка конкретного email")
    void checkUserEmailTest() {
        UserListRsModel response = usersApi.getUserList();
        usersApi.checkCorrespondence("lindsay.ferguson@reqres.in", 1, response);
    }

    @Test
    @DisplayName("Проверка обновления данных")
    void patchRequestTest() {
        RequestModel request = new RequestModel();
        request.setName("morpheus");
        request.setJob("zion resident");
        PatchRsModel response = usersApi.patchUserData(request);
        usersApi.checkCorrespondence("morpheus", "zion resident", response);
    }

    @Test
    @DisplayName("Проверка удаления данных пользователя")
    void deleteRequestTest() {
        usersApi.deleteData();
    }

    @Test
    @DisplayName("Проверка обновления данных")
    void putRequestTest() {
        RequestModel request = new RequestModel();
        request.setName("morpheus");
        request.setJob("zion resident");
        PutRsModel response = usersApi.putNewData(request);
        usersApi.checkCorrespondence("morpheus", "zion resident", response);
    }
}