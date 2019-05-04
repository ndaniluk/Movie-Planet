package rest;

import domain.Movie;
import domain.services.MovieService;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("movies")
public class MovieResources {
    private MovieService db = new MovieService();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public List<Movie> getAll() {
        return db.getAll();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(Movie movie) {
        db.add(movie);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getById(@PathParam("id") int id) {
        Movie result = db.getById(id);
        if (result == null)
            return Response.status(404).build();
        return Response.ok(result).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(Movie movie) {
        db.update(movie);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments(@PathParam("id") int movieId) {
        return Response.ok(db.getComments(movieId)).build();
    }

    @POST
    @Path("/{id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("id") int movieId, String comment) {
        db.addComment(comment, movieId);
        return Response.ok(comment).build();
    }

    @DELETE
    @Path("/{movieId}/comments/{commentId}")
    public Response removeComment(@PathParam("movieId") int movieId, @PathParam("commentId") int commentId){
        db.removeComment(movieId, commentId);
        return Response.ok().build();
    }

    @PUT
    @Path("/{id}/rating")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rateMovie(@PathParam("id") int id, double rating){
        db.rateMovie(id, rating);
        return Response.ok().build();
    }
}