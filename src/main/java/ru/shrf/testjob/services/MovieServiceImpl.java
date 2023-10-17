package ru.shrf.testjob.services;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import retrofit2.Call;
import retrofit2.Response;
import ru.shrf.testjob.dto.DiscoverResponseDTO;
import ru.shrf.testjob.dto.MovieResponseDTO;
import ru.shrf.testjob.entity.Movie;
import ru.shrf.testjob.entity.User;
import ru.shrf.testjob.exeption.BusinessException;
import ru.shrf.testjob.exeption.NotFoundException;
import ru.shrf.testjob.mapper.DtoMapper;
import ru.shrf.testjob.repository.MovieRepository;
import ru.shrf.testjob.repository.UserRepository;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@Service

public class MovieServiceImpl implements MovieService {

    @Value(value = "${api_key}")
    private String apiKey;
    @Value(value = "${num_pages_to_collect}")
    private int NUM_PAGES_TO_COLLECT;

    private final Set<Movie> existingMovies;

    private final MovieApi movieApi;
    private final MovieRepository movieRepository;
    private final UserRepository userRepository;
    private final DtoMapper dtoMapper;

    public MovieServiceImpl(MovieApi movieApi, MovieRepository movieRepository, UserRepository userRepository, DtoMapper dtoMapper) {
        this.movieApi = movieApi;
        this.movieRepository = movieRepository;
        this.userRepository = userRepository;
        existingMovies = new HashSet<>(movieRepository.findAll());
        this.dtoMapper = dtoMapper;
    }

    @Override
    @Transactional
    public void collectMoviesFromDiscover() {

        for (int page = 1; page <= NUM_PAGES_TO_COLLECT; page++) {
            Call<DiscoverResponseDTO> call = movieApi.getMoviesFromDiscover(apiKey, page);
            try {
                Response<DiscoverResponseDTO> response = call.execute();
                if (response.isSuccessful() && response.body() != null) {
                    DiscoverResponseDTO discoverResponse = response.body();
                    List<MovieResponseDTO> movies = discoverResponse.getResults();
                    movies.forEach(dto -> {
                        Movie movie = dtoMapper.toMovie(dto);
                        if (!existingMovies.contains(movie)) {
                            movieRepository.save(movie);
                            existingMovies.add(movie);
                        }
                    });
                } else {
                    log.warn("Failed to get list of movies from Discover: " + response.message());
                }
            } catch (IOException e) {
                log.warn("Error while making HTTP request: " + e.getMessage());
            }
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Page<MovieResponseDTO> getAllMoviesWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return movieRepository.findAll(pageable).map(dtoMapper::toMovieResponseDTO);
    }

    @Override
    @Transactional(readOnly = true)
    public List<MovieResponseDTO> getMoviesNotInFavorites(Long userId, String loaderType) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
        if (loaderType.equals("sql")) {
            return getMoviesNotInFavoritesUsingSql(user);
        } else if (loaderType.equals("inMemory")) {
            return getMoviesNotInFavoritesInMemory(user);
        } else {
            throw new BusinessException("Invalid loaderType.");
        }
    }

    private List<MovieResponseDTO> getMoviesNotInFavoritesUsingSql(User user) {
        return movieRepository.findByUsersNotContaining(user).stream()
                .map(dtoMapper::toMovieResponseDTO)
                .collect(Collectors.toList());
    }

    private List<MovieResponseDTO> getMoviesNotInFavoritesInMemory(User user) {
        Set<Movie> favoriteMovies = user.getFavoriteMovies();
        return existingMovies.stream()
                .filter((movie) -> !favoriteMovies.contains(movie))
                .map(dtoMapper::toMovieResponseDTO)
                .collect(Collectors.toList());
    }
}