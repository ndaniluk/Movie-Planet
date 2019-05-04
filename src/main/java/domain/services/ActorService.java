package domain.services;

import domain.Actor;

import java.util.ArrayList;
import java.util.List;

public class ActorService {

    private static List<Actor> actorsDb = new ArrayList<>();
    private static int currentActorsId = 1;

    public List<Actor> getAll() {
        return actorsDb;
    }

    public Actor getById(int id) {
        for (Actor movie : actorsDb) {
            if (movie.getId() == id)
                return movie;
        }
        return null;
    }

    public void add(Actor actor) {
        actor.setId(currentActorsId);
        currentActorsId++;
        actorsDb.add(actor);
    }

    public void update(Actor movie) {
        for (Actor m : actorsDb) {
            if(movie.getId() == m.getId())
                m = movie;
        }
    }
}
