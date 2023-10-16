package ru.shrf.testjob.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shrf.testjob.dto.UserProfileResponseDTO;
import ru.shrf.testjob.dto.UserRegistrationRequestDTO;
import ru.shrf.testjob.dto.UserUpdateRequestDTO;
import ru.shrf.testjob.entity.Movie;
import ru.shrf.testjob.services.MovieService;
import ru.shrf.testjob.services.UserService;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;
    private final MovieService movieService;

    @PostMapping("/register")
    public ResponseEntity<String> registerUser(@Valid @RequestBody UserRegistrationRequestDTO request) {
            userService.registerUser(request);
            return ResponseEntity.ok("User registered successfully");
    }

    @GetMapping("/profile")
    public ResponseEntity<UserProfileResponseDTO> getUserProfile(@RequestHeader("User-Id") Long userId) {
            UserProfileResponseDTO profile = userService.getUserProfile(userId);
            return ResponseEntity.ok(profile);
    }

    @PutMapping("/profile")
    public ResponseEntity<String> updateUserProfile(@RequestHeader("User-Id") Long userId,
                                                    @Valid @RequestBody UserUpdateRequestDTO request) {
            userService.updateUserProfile(userId, request);
            return ResponseEntity.ok("User profile updated successfully");
    }

    @DeleteMapping("/profile")
    public ResponseEntity<String> deleteUserProfile(@RequestHeader("User-Id") Long userId) {
            userService.deleteUserProfile(userId);
            return ResponseEntity.ok("User profile deleted successfully");
    }

    @PostMapping("/favorites")
    public ResponseEntity<String> addMovieToFavorites(@RequestHeader("User-Id") Long userId,
                                                      @RequestBody String movieTitle) {
        userService.addMovieToFavorites(userId, movieTitle);
        return ResponseEntity.ok("Movie added to favorites");
    }

    @DeleteMapping("/favorites/{movieTitle}")
    public ResponseEntity<String> removeMovieFromFavorites(
            @RequestHeader("User-Id") Long userId,
            @PathVariable String movieTitle
    ) {
        userService.removeMovieFromFavorites(userId, movieTitle);
        return ResponseEntity.ok("Movie removed from favorites.");
    }

    @GetMapping("/not_in_favorites")
    public ResponseEntity<List<Movie>> getMoviesNotInFavorites(
            @RequestHeader("User-Id") Long userId,
            @RequestParam String loaderType
    ) {
        List<Movie> moviesNotInFavorites;
        moviesNotInFavorites = movieService.getMoviesNotInFavorites(userId, loaderType);
        return ResponseEntity.ok(moviesNotInFavorites);
    }

    @GetMapping("/movie")
    public void collectMovies() {
        movieService.collectMoviesFromDiscover();
    }
}