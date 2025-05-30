import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.lessThan;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;
import static io.restassured.RestAssured.given;

public class ItemApiTest {

    @BeforeClass
    public static void setup() {
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";
    }

    @Test
    public void createPost() {
        String newPostJson = """
            {
              "title": "foo",
              "body": "bar",
              "userId": 1
            }
            """;

        given()
            .contentType("application/json")
            .body(newPostJson)
            .log().all()
            .when()
            .post("/posts")
            .then()
            .statusCode(201)
            .body("title", equalTo("foo"))
            .time(lessThan(1000L));  // 1 saniye altında cevap süresi kontrolü
    }

    @Test
    public void getPostById() {
        int postId = 1;

        given()
            .log().all()
            .when()
            .get("/posts/" + postId)
            .then()
            .statusCode(200)
            .body("id", equalTo(postId))
            .body("title", equalTo("sunt aut facere repellat provident occaecati excepturi optio reprehenderit"))
            .time(lessThan(1000L));  // 1 saniye altında cevap süresi kontrolü
    }
}
