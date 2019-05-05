package domain;


import java.util.ArrayList;
import java.util.List;

public class Movie {
    private int id;
    private String name;
    private String description;
    private double rating;
    private int ratingsCount = 0;
    private List<Comment> comments = new ArrayList<>();
    private List<Integer> actorsIdList = new ArrayList<>();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        if (this.ratingsCount == 0) {
            this.rating = rating;
            ratingsCount++;
        } else {
            this.rating *= ratingsCount;
            ratingsCount++;
            this.rating += rating;
            this.rating /= ratingsCount;
        }
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void addComment(Comment comment) {
        comments.add(comment);
    }

    public List<Integer> getActorsIdList() {
        return actorsIdList;
    }

    public void addActorId(int id) {
        actorsIdList.add(id);
    }
}
