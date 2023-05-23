package pt.personalpage.post;

import io.quarkus.mongodb.panache.PanacheMongoEntity;
import io.quarkus.mongodb.panache.common.MongoEntity;
import io.quarkus.panache.common.Sort;

import java.time.LocalDateTime;
import java.util.List;

@MongoEntity(collection = "post")
public class Post extends PanacheMongoEntity {
    public String path;
    public String coverImage;
    public String title;
    public String content;
    public LocalDateTime date;
    public boolean visible;
    public String language;

    public Post(){}
    public Post(String path, String coveImage, String title, String content, LocalDateTime date, boolean visible, String language) {
        this.path = path;
        this.coverImage = coveImage;
        this.title = title;
        this.content = content;
        this.date = date;
        this.visible = visible;
        this.language = language;
    }

    public static List<Post> showPage(int pageIndex, Integer numberOfPages) {
        return Post.find("visible", Sort.by("date"), Boolean.TRUE).page(pageIndex,numberOfPages).list();
    }
}

