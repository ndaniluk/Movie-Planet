package rest;

import domain.Actor;
import domain.Movie;
import domain.services.ActorService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("actors")
public class ActorResources {
    public static ActorService db = new ActorService();

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addActor(Actor actor) {
        if (db.addActor(actor))
            return Response.ok().build();
        return Response.status(400).build();
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllActors() {
        return Response.ok(db.getAllActors()).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getActorById(@PathParam("id") int actorId) {
        Actor result = db.getActorById(actorId);
        if (result != null)
            return Response.ok(result).build();
        return Response.status(404).build();
    }

    @POST
    @Path("/{actorId}/movies/{movieId}")
    public Response addActorToMovie(@PathParam("actorId") int actorId, @PathParam("movieId") int movieId) {
        if (db.addActorToMovie(actorId, movieId))
            return Response.ok().build();
        return Response.status(404).build();
    }

    @GET
    @Path("/{id}/movies")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMoviesFromActor(@PathParam("id") int actorId) {
        List<Movie> moviesList = db.getAllMoviesFromActor(actorId);
        if (moviesList != null)
            return Response.ok(moviesList).build();
        return Response.status(404).build();


    }
}
