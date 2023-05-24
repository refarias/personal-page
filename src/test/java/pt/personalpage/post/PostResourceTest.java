package pt.personalpage.post;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;

@QuarkusTest
@TestHTTPEndpoint(PostResource.class)
public class PostResourceTest {
    private static final String PATH = "home";
    private static final String LANGUAGE = "english";
    @ConfigProperty(name = "page.post.number")
    Integer numberOfPost;

    @Test
    public void showPage() {
        new Post(PATH, null, "who am I?", "I'm a software engineer. ", LocalDateTime.now(), Boolean.TRUE, LANGUAGE).persist();
        new Post(PATH, null, "Which skills I have?", "I program in java and angular.", LocalDateTime.now(), Boolean.TRUE, LANGUAGE).persist();
        new Post(PATH, null, "How many years of experience I have?", "More than 10 years. ", LocalDateTime.now(), Boolean.TRUE, LANGUAGE).persist();
        new Post(PATH, null, "What I like todo in my free time?", "Play COD (Call of Duty war-zone 2).", LocalDateTime.now(), Boolean.TRUE, LANGUAGE).persist();


        given()
                .contentType(ContentType.JSON)
                .when().get("/page/0")
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("size()", is(numberOfPost));

    }

    @Test
    public void createPost() {
        given()
                .body("{\"path\" : \"Containers\",\"title\" : \"Introduction to docker\"," +
                        "\"content\" : \"docker run --name mongodb mongo:4.1.2\", \"language\" : \"english\"}")
                .contentType(ContentType.JSON)
                .when()
                .post()
                .then().statusCode(HttpStatus.SC_CREATED)
                .header("Location",is(notNullValue(String.class)));
    }

    @Test
    public void updatePost() {
        var post = new Post("Donation", null, "Pay me a coffee!", "Thank you for help me drink some coffee!", LocalDateTime.now(), Boolean.TRUE, LANGUAGE);
        post.persist();

        var jsonObject = "{\"path\" : \"%s\",\"title\" : \"%s\", \"content\" : \"%s\", \"language\" : \"%s\"}";

        given()
                .body(jsonObject.formatted(post.path,post.title,post.content,post.language))
                .contentType(ContentType.JSON)
                .when()
                .put(post.id.toString())
                .then().statusCode(HttpStatus.SC_OK);
    }

    @Test
    public void getPost() {
        var post = new Post("Java", null, "Hello world?", "Public static void main(String [] args){}", LocalDateTime.now(), Boolean.TRUE, LANGUAGE);
        post.persist();

        given()
                .contentType(ContentType.JSON)
                .when().get(post.id.toString())
                .then()
                .statusCode(HttpStatus.SC_OK)
                .body("path", is(post.path))
                .body("title", is(post.title))
                .body("content", is(post.content));
    }

    @Test
    public void deletePost() {
        var post = new Post("PHP", null, "Java is better than PHP!", "It's all I have to say :P", LocalDateTime.now(), Boolean.TRUE, LANGUAGE);
        post.persist();

        given()
                .when()
                .delete(post.id.toString())
                .then()
                .statusCode(HttpStatus.SC_NO_CONTENT);
    }

}