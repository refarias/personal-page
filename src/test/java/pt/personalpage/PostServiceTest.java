package pt.personalpage;

import com.mongodb.assertions.Assertions;
import io.quarkus.panache.mock.PanacheMock;
import io.quarkus.test.junit.QuarkusTest;
import org.bson.types.ObjectId;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import javax.inject.Inject;
import java.time.LocalDateTime;

@QuarkusTest
public class PostServiceTest {
    private static final ObjectId POST_ID = new ObjectId("643ad40ea60f2449f20158e7");
    private static final Post POST = new Post("update", null, "shouldNotUpdatePost", "", LocalDateTime.now(), false, "english");
    @Inject
    PostService postService;

    @Test
    public void updatePostTest() {
        PanacheMock.mock(Post.class);
        Mockito.when(Post.findById(POST_ID)).thenReturn(new Post());
        Assertions.assertTrue(postService.updatePost(POST_ID, POST));
    }

    @Test
    public void shouldNotUpdatePostTest() {
        PanacheMock.mock(Post.class);
        Mockito.when(Post.findById(POST_ID)).thenReturn(null);
        Assertions.assertFalse(postService.updatePost(POST_ID, POST));
    }

}
