package pt.personalpage.profile;

import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
@TestHTTPEndpoint(ProfileResource.class)
public class ProfileResourceTest {
    private static String NAME = "Bobby Generic";

    @Test
    public void createOrUpdate(){
        var profile = new Profile("","Bobby","I'm a softwarer engineer","I have worked as software engineer for 12 years", null);
        var jsonObject = "{\"image\" : \"%s\",\"name\" : \"%s\", \"introduction\" : \"%s\", \"aboutMe\" : \"%s\", \"contacts\" : {\"email\" :\"user.test@email.com\"}}";


        //Testing profile creation
        given()
                .body(jsonObject.formatted(profile.image, profile.name, profile.introduction, profile.aboutMe))
                .contentType(ContentType.JSON)
                .when()
                .put()
                .then().statusCode(HttpStatus.SC_OK);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then().statusCode(HttpStatus.SC_OK)
                .body("name", is(profile.name))
                .body("introduction", is(profile.introduction))
                .body("aboutMe", is(profile.aboutMe));

        //Testing profile update
        given()
                .body(jsonObject.formatted(profile.image, NAME, profile.introduction, profile.aboutMe))
                .contentType(ContentType.JSON)
                .when()
                .put()
                .then().statusCode(HttpStatus.SC_OK);

        given()
                .contentType(ContentType.JSON)
                .when()
                .get()
                .then().statusCode(HttpStatus.SC_OK)
                .body("name", is(NAME));
    }


}
