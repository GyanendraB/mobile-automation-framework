package api.test;

import api.endpoints.ObjectEndpoints;
import api.payload.ObjectPayload;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ObjectTest {

    @Test
    public void testGetAllObjects() {

        Response response = ObjectEndpoints.getAllObjects();

        response.then()
                .statusCode(200)
                    .body("find { it.name == 'Apple AirPods' }.data.price", equalTo(120));;

        List<ObjectPayload> objects =
                response.jsonPath().getList("", ObjectPayload.class);

        assertThat(objects.size(), greaterThan(0));

        // Validate first object structure
        ObjectPayload first = objects.get(0);

        assertThat(first.getid(), notNullValue());
        assertThat(first.getName(), notNullValue());
        // data can be null, so no hard assertion
    }
    @Test
    public void testCreateObject() {

        Map<String, Object> data = new HashMap<>();
        data.put("color", "Cloudy White");
        data.put("capacity", "128 GB");

        ObjectPayload payload = new ObjectPayload();
        payload.setName("Google Pixel 6 Pro");
        payload.setData(data);

        Response response = ObjectEndpoints.createObject(payload);

        response.then()
                .log().all()
                .statusCode(200)
                .body("id", notNullValue())
                .body("name", equalTo("Google Pixel 6 Pro"))
                .body("data.color", equalTo("Cloudy White"))
                .body("data.capacity", equalTo("128 GB"));
                System.out.println("Both the Test ran Successfully");
    }
}
