package testCases;

import services.ApiClient;
import base.ApiBaseTest;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginApiTest extends ApiBaseTest {

    @Test
    public void loginShouldWork() {
        String payload = "{ \"email\":\"test@qa.com\", \"password\":\"12345\" }";

        Response response = ApiClient.post("/login", payload);

        Assert.assertEquals(response.statusCode(), 200);
    }
}
