package rest;

import domain.Actor;
import domain.Comment;
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
    public List<Movie> getAllMovies() {
        return db.getAllMovies();
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addMovie(Movie movie) {
        if (db.addMovie(movie))
            return Response.ok().build();
        return Response.status(400).build();
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMovieById(@PathParam("id") int id) {
        Movie result = db.getMovieById(id);
        if (result != null)
            return Response.ok(result).build();
        return Response.status(404).build();
    }

    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateMovie(Movie movie) {
        db.updateMovie(movie);
        return Response.ok().build();
    }

    @GET
    @Path("/{id}/comments")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getComments(@PathParam("id") int movieId) {
        List<Comment> comments = db.getComments(movieId);
        if (comments != null)
            return Response.ok(comments).build();
        return Response.status(404).build();
    }

    @POST
    @Path("/{id}/comments")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response addComment(@PathParam("id") int movieId, String comment) {
        if (db.addComment(comment, movieId))
            return Response.ok().build();
        return Response.status(404).build();

    }

    @DELETE
    @Path("/{movieId}/comments/{commentId}")
    public Response removeComment(@PathParam("movieId") int movieId, @PathParam("commentId") int commentId) {
        if (db.removeComment(movieId, commentId))
            return Response.ok().build();
        return Response.status(404).build();
    }

    @PUT
    @Path("/{id}/rating")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response rateMovie(@PathParam("id") int movieId, double rating) {
        return db.rateMovie(movieId, rating); // 404 for incorrect movieId and 400 for incorrect rating
    }

}