package domain.services;

import domain.Actor;
import domain.Movie;
import rest.MovieResources;

import java.util.ArrayList;
import java.util.List;

public class ActorService {

    private static List<Actor> actorsDb = new ArrayList<>();
    private static int currentActorsId = 1;

    public List<Actor> getAllActors() {
        return actorsDb;
    }

    private Actor getActorFromDb(int id) {
        for (Actor actor : actorsDb) {
            if (actor.getId() == id)
                return actor;
        }
        return null;
    }

    public Actor getActorById(int id) {
        return getActorFromDb(id);
    }

    public boolean addActor(Actor actor) {
        if (actor == null || actor.getName().isEmpty() || actor.getSurname().isEmpty())
            return false;
        actor.setId(currentActorsId);
        currentActorsId++;
        actorsDb.add(actor);
        return true;
    }

    public boolean addActorToMovie(int actorId, int movieId) {
        if (getActorById(actorId) == null)
            return false;
        if (MovieResources.db.getMovieById(movieId) == null)
            return false;

        getActorById(actorId).addMovieId(movieId);
        MovieResources.db.getMovieById(movieId).addActorId(actorId);
        return true;
    }

    public List<Movie> getAllMoviesFromActor(int actorId) {
        List<Movie> moviesListFromActor = new ArrayList<>();
        Actor actor = getActorById(actorId);
        if (actor == null)
            return null;

        for (int id : actor.getMoviesIdList()) {
            Movie movie = MovieResources.db.getMovieById(id);
            moviesListFromActor.add(MovieResources.db.getMovieById(id));
        }
        return moviesListFromActor;
    }

}
