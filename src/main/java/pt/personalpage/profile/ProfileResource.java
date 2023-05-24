package pt.personalpage.profile;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@RequestScoped
@Path("profile")
public class ProfileResource {
    @Inject
    ProfileService service;

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response get(){
        var profile = (Profile)Profile.findAll().firstResult();
        return Response.ok(profile).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response createOrUpdate(Profile profile){
        service.createOrUpdate(profile);
        return Response.ok().build();
    }
}
