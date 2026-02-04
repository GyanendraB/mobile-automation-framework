package api.endpoints;

import api.payload.ObjectPayload;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class ObjectEndpoints {

    private static final String BASE_URL = "https://api.restful-api.dev/objects";

    public static Response createObject(ObjectPayload payload) {
        return given()
                .contentType(ContentType.JSON)
                .body(payload)
                .when()
                .post(BASE_URL);
    }

    public static Response getObjectById(String id) {
        return given()
                .when()
                .get(BASE_URL + "/" + id);
    }

    public static Response getAllObjects() {
        return given()
                .when()
                .get(BASE_URL);
    }

    public static Response deleteObject(String id) {
        return given()
                .when()
                .delete(BASE_URL + "/" + id);
    }
}
