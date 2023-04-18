package pt.personalpage;

import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class PostResourceTest {

    @Test
    public void testHelloEndpoint() {
        given()
                .when().get("/hello")
                .then()
                .statusCode(200)
                .body(is("Hello RESTEasy"));
    }

    @Test
    public void showPage() {
    }

    @Test
    public void createPost() {
    }

    @Test
    public void updatePost() {
    }

    @Test
    public void getPost() {
    }

    @Test
    public void deletePost() {
    }

}