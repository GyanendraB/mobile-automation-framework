package api;

import api.ObjectEndpoints;
import api.ObjectPayload;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

public class ObjectTest {

    @Test
    public void testGetAllObjects() {

        Response response = ObjectEndpoints.getAllObjects();

        response.then()
                .statusCode(200);

        List<ObjectPayload> objects =
                response.jsonPath().getList("", ObjectPayload.class);

        assertThat(objects.size(), greaterThan(0));

        // Validate first object structure
        ObjectPayload first = objects.get(0);

        assertThat(first.getid(), notNullValue());
        assertThat(first.getName(), notNullValue());
        // data can be null, so no hard assertion
    }
}
