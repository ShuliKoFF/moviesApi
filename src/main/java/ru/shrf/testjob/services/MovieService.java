package ru.shrf.testjob.services;

import org.springframework.data.domain.Page;
import ru.shrf.testjob.dto.MovieResponseDTO;

import java.util.List;

public interface MovieService {

    void collectMoviesFromDiscover();

    Page<MovieResponseDTO> getAllMoviesWithPagination(int page, int size);

    List<MovieResponseDTO> getMoviesNotInFavorites(Long userId, String loaderType);

}
