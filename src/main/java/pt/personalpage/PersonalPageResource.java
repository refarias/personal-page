package pt.personalpage;

import org.eclipse.microprofile.config.inject.ConfigProperty;
import pt.personalpage.menu.Menu;
import pt.personalpage.post.Post;
import pt.personalpage.profile.Profile;

import javax.enterprise.context.RequestScoped;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("page")
public class PersonalPageResource {
    @ConfigProperty(name = "page.post.number")
    Integer numberOfPages;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getFistPage(){
        var profile = (Profile)Profile.findAll().firstResult();
        var menu = Menu.get();
        var posts = Post.showPage(0, numberOfPages);
        return Response.ok(new PersonalPageDTO(profile, menu, posts)).build();
    }
}
