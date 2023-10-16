package ru.shrf.testjob.services;

import org.springframework.data.domain.Page;
import ru.shrf.testjob.entity.Movie;

import java.util.List;

public interface MovieService {

    void collectMoviesFromDiscover();

    Page<Movie> getAllMoviesWithPagination(int page, int size);

    List<Movie> getMoviesNotInFavorites(Long userId, String loaderType);

}
