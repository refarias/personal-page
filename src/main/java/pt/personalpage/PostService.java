package pt.personalpage;

import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;
import java.time.LocalDateTime;

@ApplicationScoped
public class PostService {
    public ObjectId createPost(Post post) {
        Post.persist(post);
        return post.id;
    }

    public boolean updatePost(ObjectId postId, Post postUpdate) {
        Post post = Post.findById(postId);
        if(post == null) {
            return false;
        }
        post.path = postUpdate.path;
        post.coverImage = postUpdate.coverImage;
        post.title = postUpdate.title;
        post.content = postUpdate.content;
        post.date =  LocalDateTime.now();
        post.visible =postUpdate.visible;
        post.language = postUpdate.language;
        post.persistOrUpdate();
        return true;
    }

}
