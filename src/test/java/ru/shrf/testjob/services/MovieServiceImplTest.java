package ru.shrf.testjob.services;


import lombok.NoArgsConstructor;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import ru.shrf.testjob.entity.Movie;
import ru.shrf.testjob.repository.MovieRepository;

import java.util.HashSet;
import java.util.Set;


@RunWith(SpringRunner.class)
@SpringBootTest
@NoArgsConstructor
public class MovieServiceImplTest {

    @Autowired
    private MovieRepository movieRepository;

    @Test
    public void containsMovieTest() {
        Set<Movie> existingMovies = new HashSet<>(movieRepository.findAll());
        Movie movie = Movie.builder()
                .id(Long.valueOf(1))
                .title("Mission: Impossible - Dead Reckoning Part One")
                .posterPath("/NNxYkU70HPurnNCSiCjYAmacwm.jpg").build();
        {
            Assert.assertTrue(existingMovies.contains(movie));
        }
    }
}