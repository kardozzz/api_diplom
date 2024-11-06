package specs;

import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

import static io.restassured.RestAssured.with;
import static io.restassured.filter.log.LogDetail.BODY;
import static io.restassured.http.ContentType.JSON;

public class DataSpec {
    public static RequestSpecification dataUpdateUserRequestSpec = with()
            .contentType(JSON)
            .log().uri();
    public static ResponseSpecification dataUpdateUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(200)
            .log(BODY)
            .build();
    public static RequestSpecification dataCreateUserRequestSpec = with()
            .contentType(JSON)
            .log().uri();
    public static ResponseSpecification dataCreateUserResponseSpec = new ResponseSpecBuilder()
            .expectStatusCode(201)
            .log(BODY)
            .build();

}


