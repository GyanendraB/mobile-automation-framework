package services;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiClient {

    static {
        RestAssured.baseURI = "https://api.timeto.me";
    }

    public static Response get(String path) {
        return RestAssured.given().get(path);
    }

    public static Response post(String path, Object body) {
        return RestAssured
                .given()
                .contentType("application/json")
                .body(body)
                .post(path);
    }
}
