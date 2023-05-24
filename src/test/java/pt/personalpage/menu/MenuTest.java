package pt.personalpage.menu;

import io.quarkus.test.common.QuarkusTestResource;
import io.quarkus.test.junit.QuarkusTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import pt.personalpage.MongoDBContainer;
import pt.personalpage.post.Post;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@QuarkusTest
@QuarkusTestResource(MongoDBContainer.class)
public class MenuTest {
    private static String GITHUB = "github";
    private static String AGILE = "agile";
    private static String PROGRAMMING_JAVA = "programming/java";
    private static String CLOUD_PROVIDER_AWS = "cloud/provider/aws";
    private static String ClOUD = "cloud";
    private static String PROGRAMMING = "programming";


    private static Menu menu;

    @BeforeAll
    public static void setUp() {
        menu = new Menu(new HashMap<>());
        menu.addItem(GITHUB);
        menu.addItem(AGILE);
        menu.addItem(PROGRAMMING_JAVA);
        menu.addItem("programming/javascript");
        menu.addItem("programming/go");
        menu.addItem("cloud/container/podman");
        menu.addItem("cloud/container/docker");
        menu.addItem(CLOUD_PROVIDER_AWS);
        menu.addItem("cloud/provider/gcp");
    }

    @Test
    public void addItem() {
        // testing the first menu layer
        Assertions.assertEquals(4, menu.items().size());
        Object github = menu.items().get(GITHUB);
        Assertions.assertTrue(github instanceof String);
        Assertions.assertEquals(GITHUB,github);

        Object agile = menu.items().get(AGILE);
        Assertions.assertTrue(agile instanceof String);
        Assertions.assertEquals(AGILE,agile);

        // testing the second menu layer
        Object programming = menu.items().get(PROGRAMMING);
        Assertions.assertTrue(programming instanceof Map);
        Assertions.assertEquals(3,((Map)programming).size());

        var java = ((Map)programming).get("java");
        Assertions.assertTrue(java instanceof String);
        Assertions.assertEquals(PROGRAMMING_JAVA,java);


        // testing the third menu layer
        Object cloud = menu.items().get(ClOUD);
        Assertions.assertTrue(cloud instanceof Map);
        Assertions.assertEquals(2,((Map)cloud).size());

        Map<String,String> provide = (Map)((Map)cloud).get("provider");
        Assertions.assertEquals(CLOUD_PROVIDER_AWS ,provide.get("aws"));
    }

    @Test
    public void getTest() {
        new Post(CLOUD_PROVIDER_AWS, null, "Introduction to AWS services","", LocalDateTime.now(),true,"english").persist();
        new Post(PROGRAMMING_JAVA, null, "Introduction to Java","", LocalDateTime.now(),true,"english").persist();
        var menu = Menu.get();
        Assertions.assertEquals(2, menu.items().size());
        menu.items().get(ClOUD);
        menu.items().get(PROGRAMMING);
    }

}
