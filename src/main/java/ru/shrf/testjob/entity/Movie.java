package ru.shrf.testjob.entity;

import lombok.*;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "movies", uniqueConstraints = @UniqueConstraint(columnNames = {"title"}))
@Builder
@Getter
@Setter
@ToString(exclude = {"id", "users"})
@NoArgsConstructor
@AllArgsConstructor
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String title;

    private String posterPath;

    @ManyToMany(mappedBy = "favoriteMovies")
    private Set<User> users = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Movie movie = (Movie) o;

        if (!title.equals(movie.title)) return false;
        return posterPath != null ? posterPath.equals(movie.posterPath) : movie.posterPath == null;
    }

    @Override
    public int hashCode() {
        int result = title.hashCode();
        result = 31 * result + (posterPath != null ? posterPath.hashCode() : 0);
        return result;
    }
}