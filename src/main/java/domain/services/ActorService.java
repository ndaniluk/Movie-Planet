package domain.services;

import domain.Actor;
import domain.Movie;

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

        getActorById(actorId).addMovieId(movieId);
        return true;
    }

}
