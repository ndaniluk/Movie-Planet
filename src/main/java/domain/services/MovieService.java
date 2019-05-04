package domain.services;

import domain.Comment;
import domain.Movie;

import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class MovieService {

    private static List<Movie> moviesDb = new ArrayList<>();
    private static int currentMoviesId = 1;
    private static int currentCommentsId = 1;

    private Movie getMovieFromDb(int id) {
        for (Movie movie : moviesDb) {
            if (movie.getId() == id)
                return movie;
        }
        return null;
    }

    public List<Movie> getAllMovies() {
        return moviesDb;
    }

    public Movie getMovieById(int id) {
        return getMovieFromDb(id);
    }

    public boolean addMovie(Movie movie) {
        if (!(movie.getName().isEmpty() || movie.getDescription().isEmpty())) {
            movie.setId(currentMoviesId++);
            moviesDb.add(movie);
            return true;
        }
        return false;
    }

    public void updateMovie(Movie movie) {
        for (Movie m : moviesDb) {
            if (movie.getId() == m.getId()) {
                m.setName(movie.getName());
                m.setDescription(movie.getDescription());
            }
        }

    }

    public List<Comment> getComments(int id) {
        Movie result = getMovieFromDb(id);
        if (result == null)
            return null;
        return result.getComments();
    }

    public boolean addComment(String commentStr, int id) {
        Movie result = getMovieFromDb(id);
        if (result == null)
            return false;

        Comment comment = new Comment(currentCommentsId, commentStr);
        currentCommentsId++;

        result.addComment(comment);
        return true;
    }

    public boolean removeComment(int movieId, int commentId) {
        Movie result = getMovieFromDb(movieId);
        if (result != null) {
            List<Comment> commentsList = result.getComments();
            for (int i = 0; i < commentsList.size(); i++) {
                if (commentsList.get(i).getId() == commentId) {
                    commentsList.remove(i);
                    return true;
                }
            }
        }
        return false;
    }

    public Response rateMovie(int movieId, double rating) {
        if (!(rating > 5 || rating < 1)) {
            Movie result = getMovieFromDb(movieId);
            if (result != null) {
                result.setRating(rating);
                return Response.ok().build();
            }
            return Response.status(404).build();
        }
        return Response.status(400).build();
    }
}
