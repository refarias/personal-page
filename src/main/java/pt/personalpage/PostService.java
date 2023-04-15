package pt.personalpage;

import org.bson.types.ObjectId;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class PostService {
    public ObjectId createPost(Post post){
        Post.persist(post);
        return post.id;
    }
}
