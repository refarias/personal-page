package pt.personalpage;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

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
        new Post("invisible","Post invisible"," ", LocalDateTime.now(),false,LANGUAGE).persist();

        for(int i = 0; i < 5; i++){
            var p = new Post("home","test"+i," ", LocalDateTime.now(),true,LANGUAGE);
            p.persist();
        }
    }

    @Test
    public void showPageZeroTest() {
        List<Post> posts = Post.showPage(0);
        Assertions.assertEquals(numberOfPages, posts.size());
        Assertions.assertEquals("test0",posts.get(0).title);
        Assertions.assertEquals("test3",posts.get(3).title);
    }

}
