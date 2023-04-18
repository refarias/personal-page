package pt.personalpage;

import org.bson.types.ObjectId;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import java.net.URI;
import java.time.LocalDateTime;

@RequestScoped
@Path("post")
public class PostResource {
    @Inject
    public PostService service;

    @GET
    @Path("page/{pageNumber}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response showPage(int pageNumber) {
        return Response.ok(Post.showPage(pageNumber)).build();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createPost(@Valid CreatePostDTO createDTO) {
        ObjectId id = service.createPost(new Post(createDTO.path, createDTO.title, createDTO.content, LocalDateTime.now(), createDTO.visible, createDTO.language));
        return Response.created(
                        URI.create("/post/%s".formatted(id.toString())))
                .build();
    }

    @PUT
    @Path("{postId}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updatePost(String postId, @Valid CreatePostDTO createDTO) {
        var postUpdate = new Post(createDTO.path, createDTO.title, createDTO.content, LocalDateTime.now(), createDTO.visible, createDTO.language);
        boolean isPostUpdated = service.updatePost(new ObjectId(postId), postUpdate);
        if (isPostUpdated) {
            return Response.ok().build();
        }
        return Response.status(Status.BAD_REQUEST).build();
    }

    @GET
    @Path("{postId}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPost(String postId) {
        Post post = Post.findById(new ObjectId(postId));
        if (post == null) {
            return Response.status(
                            Status.NOT_FOUND)
                    .build();
        }
        return Response.ok(post).build();
    }

    @DELETE
    @Path("{postId}")
    public Response deletePost(String postId) {
        Post post = Post.findById(postId);
        if ( post == null ) {
            return Response.status(Status.NOT_FOUND).build();
        }
        post.delete();
        return Response.noContent().build();
    }

}
