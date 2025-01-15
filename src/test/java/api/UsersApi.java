package api;

import models.PatchRsModel;
import models.PutRsModel;
import models.RequestModel;
import models.UserListRsModel;

import static io.qameta.allure.Allure.step;
import static io.restassured.RestAssured.given;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static specs.DataSpec.*;

public class UsersApi {

    public UserListRsModel getUserList() {
        UserListRsModel response = step("Сделать запрос полного списка пользователей", () ->
                given(commonRequest)
                        .queryParam("page", "2")
                        .get("/users")
                        .then()
                        .spec(response200)
                        .extract().as(UserListRsModel.class));
        return response;
    }

    public void checkCorrespondence(String email, int id, UserListRsModel response) {
        step("Проверить, что в ответе у email пользователя соответствует {0}", () ->
                assertEquals(response.getData().get(id).getEmail(), email));
    }

    public void checkCorrespondence(String name, String job, PatchRsModel response) {
        step("Проверить в ответе соответствие name и job", () -> {
            assertEquals(response.getName(), name);
            assertEquals(response.getJob(), job);
        });
    }

    public void checkCorrespondence(String name, String job, PutRsModel response) {
        step("Проверить в ответе соответствие name и job", () -> {
            assertEquals(response.getName(), name);
            assertEquals(response.getJob(), job);
        });
    }

    public PatchRsModel patchUserData(RequestModel request) {
        PatchRsModel response = step("Отправить запрос на обновление данных пользователя", () ->

                given(commonRequest)
                        .body(request)

                        .when()
                        .patch("/users/2")

                        .then()
                        .spec(response200)

                        .extract().as(PatchRsModel.class));
        return response;
    }

    public void deleteData() {
        step("Выполнить удаление пользователя. Ожидаемая ошибка - 204.", () ->
                given(commonRequest)
                        .delete("/users/2")
                        .then()
                        .spec(response204));
    }

    public PutRsModel putNewData(RequestModel request) {
        PutRsModel response = step("Отправить запрос на обновление данных пользователя", () ->
                given(commonRequest)
                        .body(request)

                        .when()
                        .put("/users/2")

                        .then()
                        .spec(response200)
                        .extract().as(PutRsModel.class));
        return response;
    }

}