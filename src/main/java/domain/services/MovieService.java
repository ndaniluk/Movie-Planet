package domain.services;

import domain.Comment;
import domain.Movie;

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

    public List<Movie> getAll() {
        return moviesDb;
    }

    public Movie getById(int id) {
        return getMovieFromDb(id);
    }

    public void add(Movie movie) {
        movie.setId(currentMoviesId++);
        moviesDb.add(movie);
    }

    public void update(Movie movie) {
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

    public void addComment(String commentStr, int id) {
        Comment comment = new Comment(currentCommentsId, commentStr);
        currentCommentsId++;
        Movie result = getMovieFromDb(id);
        result.addComment(comment);
    }

    public void removeComment(int movieId, int commentId) {
        Movie result = getMovieFromDb(movieId);
        if (result != null) {
            List<Comment> commentsList = result.getComments();
            for (int i = 0; i < commentsList.size(); i++) {
                if (commentsList.get(i).getId() == commentId) {
                    commentsList.remove(i);
                    break;
                }
            }
        }
    }

    public void rateMovie(int id, double rating) {
        if (!(rating > 5 || rating < 1)) {
            Movie result = getMovieFromDb(id);
            if (result != null)
                result.setRating(rating);
        }
    }
}
