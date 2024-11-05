import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static io.restassured.http.ContentType.JSON;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.core.IsNull.notNullValue;
import static org.hamcrest.core.IsNull.nullValue;

public class restApiPost {

    @Test
    @DisplayName("CreateUser name and job not null")
    void createUserTests() {
        String userData = "{\"name\": \"Andrey\", \"job\": \"QA Automation\"}";
        given()
                .body(userData)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Andrey"))
                .body("job", is("QA Automation"))
                .body("id",is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }
    @Test
    @DisplayName("CreateUser name null")
    void createUserNotNameTests() {
        String userData = "{\"job\": \"QA Automation\"}";
        given()
                .body(userData)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is(nullValue()))
                .body("job", is("QA Automation"))
                .body("id",is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }
    @Test
    @DisplayName("CreateUser job null")
    void createUserNotJobTests() {
        String userData = "{\"name\": \"Andrey\"}";
        given()
                .body(userData)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is("Andrey"))
                .body("job", is(nullValue()))
                .body("id",is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }
    @Test
    @DisplayName("CreateUser name and job null")
    void createUserNotJobNotNameTests() {
        String userData = "{}";
        given()
                .body(userData)
                .contentType(JSON)

                .when()
                .log().uri()
                .post("https://reqres.in/api/users")

                .then()
                .log().status()
                .log().body()
                .statusCode(201)
                .body("name", is(nullValue()))
                .body("job", is(nullValue()))
                .body("id",is(notNullValue()))
                .body("createdAt", is(notNullValue()));
    }
}
