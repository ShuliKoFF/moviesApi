package ru.shrf.testjob.entity;


import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table (name = "users")
@Builder
@Getter
@Setter
@ToString(exclude = {"id", "favoriteMovies"})
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotNull
    @Email(message = "Invalid email format")
    private String email;

    @Column(nullable = false, unique = true)
    @NotNull
    @Pattern(regexp = "^[a-zA-Z0-9]+$", message = "Username can only contain Latin characters and numbers")
    private String username;

    private String name;

    @ManyToMany
    @JoinTable(
            name = "user_favorite_movies",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "movie_id")
    )
    private Set<Movie> favoriteMovies = new HashSet<>();

}
