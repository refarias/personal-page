package pt.personalpage.post;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pt.personalpage.MongoDBContainer;

import java.time.LocalDateTime;
import java.util.List;

@QuarkusTest
@QuarkusTestResource(MongoDBContainer.class)
public class PostTest {
    private static final String LANGUAGE = "english";
    @ConfigProperty(name = "page.post.number")
    Integer numberOfPages;

    @BeforeAll
    public static void setUp() {
        new Post("invisible", null, "Post invisible", " ", LocalDateTime.now(), false, LANGUAGE).persist();

        for (int i = 0; i < 5; i++) {
            var p = new Post("home", null, "test" + i, " ", LocalDateTime.now(), true, LANGUAGE);
            p.persist();
        }
    }

    @Test
    public void showPageZeroTest() {
        List<Post> posts = Post.showPage(0, 4);
        Assertions.assertEquals(numberOfPages, posts.size());
    }

}
