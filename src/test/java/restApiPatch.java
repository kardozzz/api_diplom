import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class restApiPatch {

    @Test
    @DisplayName("UpdateUser name and job not null")
    void patchUserTests() {
        String userData = "{\"name\": \"morpheus\", \"job\": \"zion resident\"}";
        given()
                .body(userData)
                .contentType(JSON)

                .when()
                .log().uri()
                .patch("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is("zion resident"))
                .body("updatedAt", is(notNullValue()));
    }
    @Test
    @DisplayName("UpdateUser name null")
    void patchUserNotNameTests() {
        String userData = "{\"job\": \"zion resident\"}";
        given()
                .body(userData)
                .contentType(JSON)

                .when()
                .log().uri()
                .patch("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("job", is("zion resident"))
                .body("updatedAt", is(notNullValue()));
    }
    @Test
    @DisplayName("UpdateUser job null")
    void patchUserNotJobTests() {
        String userData = "{\"name\": \"morpheus\"}";
        given()
                .body(userData)
                .contentType(JSON)

                .when()
                .log().uri()
                .patch("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is("morpheus"))
                .body("job", is(nullValue()))
                .body("updatedAt", is(notNullValue()));
    }
    @Test
    @DisplayName("UpdateUser job null")
    void patchUserNotJobAndNameTests() {
        String userData = "{}";
        given()
                .body(userData)
                .contentType(JSON)

                .when()
                .log().uri()
                .patch("https://reqres.in/api/users/2")

                .then()
                .log().status()
                .log().body()
                .statusCode(200)
                .body("name", is(nullValue()))
                .body("job", is(nullValue()))
                .body("updatedAt", is(notNullValue()));
    }
}
