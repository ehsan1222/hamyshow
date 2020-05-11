package ir.hamyiar.security.model;

import lombok.Getter;

import java.sql.Date;
import java.util.List;
import java.util.Set;
import java.util.UUID;

@Getter
public class MovieInformation {
    private final UUID movieId;
    private final String name;
    private final Set<String> languages;
    private final MPARating mpaRating;
    private final List<String> genre;
    private final Date creationDate;
    private final Set<String> directors;
    private final Set<String> actors;
    private final String picturePath;

    public MovieInformation(String name,
                            Set<String> languages,
                            MPARating mpaRating,
                            List<String> genre,
                            Date creationDate,
                            Set<String> directors,
                            Set<String> actors, String picturePath) {
        this.movieId = UUID.randomUUID();
        this.name = name;
        this.languages = languages;
        this.mpaRating = mpaRating;
        this.genre = genre;
        this.creationDate = creationDate;
        this.directors = directors;
        this.actors = actors;
        this.picturePath = picturePath;
    }

    public UUID getMovieId() {
        return movieId;
    }

    public String getName() {
        return name;
    }

    public Set<String> getLanguages() {
        return languages;
    }

    public MPARating getMpaRating() {
        return mpaRating;
    }

    public List<String> getGenre() {
        return genre;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Set<String> getDirectors() {
        return directors;
    }

    public Set<String> getActors() {
        return actors;
    }

    public String getPicturePath() {
        return picturePath;
    }
}
