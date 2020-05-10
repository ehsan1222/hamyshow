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

    public MovieInformation(UUID movieId,
                            String name,
                            Set<String> languages,
                            MPARating mpaRating,
                            List<String> genre,
                            Date creationDate,
                            Set<String> directors,
                            Set<String> actors) {
        this.movieId = movieId;
        this.name = name;
        this.languages = languages;
        this.mpaRating = mpaRating;
        this.genre = genre;
        this.creationDate = creationDate;
        this.directors = directors;
        this.actors = actors;
    }
}
