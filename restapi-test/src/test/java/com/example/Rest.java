import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.BeforeClass;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class Rest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test
    public void getUserById() {
        Response response = given()
                .log().all()
                .when()
                .get("/users/2");

        System.out.println("=== GET RESPONSE ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime() + "ms");
        System.out.println("Body:\n" + response.getBody().asPrettyString());

        response.then()
                .statusCode(200)
                .body("data.id", equalTo(2))
                .body("data.email", containsString("@reqres.in"))
                .time(lessThan(1000L));
    }

    @Test
    public void registerUser() {
        Response response = given()
                .contentType("application/json")
                .body("{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }")
                .log().all()
                .when()
                .post("/register");

        System.out.println("=== POST RESPONSE ===");
        System.out.println("Status Code: " + response.getStatusCode());
        System.out.println("Response Time: " + response.getTime() + "ms");
        System.out.println("Body:\n" + response.getBody().asPrettyString());

        response.then()
                .statusCode(200)
                .body("id", notNullValue())
                .body("token", notNullValue())
                .time(lessThan(1000L));
    }

}
