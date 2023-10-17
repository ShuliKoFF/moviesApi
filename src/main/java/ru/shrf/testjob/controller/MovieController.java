package ru.shrf.testjob.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.shrf.testjob.dto.MovieResponseDTO;
import ru.shrf.testjob.services.MovieService;

@RestController
@RequestMapping("/movies")
@RequiredArgsConstructor
public class MovieController {
    private final MovieService movieService;

    @GetMapping("/all_pagination")
    public ResponseEntity<Page<MovieResponseDTO>> getAllMoviesWithPagination(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "15") int size) {
        Page<MovieResponseDTO> movieResponseDTO = movieService.getAllMoviesWithPagination(page, size);
        return ResponseEntity.ok(movieResponseDTO);
    }
}