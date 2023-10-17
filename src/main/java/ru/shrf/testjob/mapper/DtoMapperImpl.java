package ru.shrf.testjob.mapper;

import org.springframework.stereotype.Component;
import ru.shrf.testjob.dto.MovieResponseDTO;
import ru.shrf.testjob.dto.UserProfileResponseDTO;
import ru.shrf.testjob.dto.UserRegistrationRequestDTO;
import ru.shrf.testjob.entity.Movie;
import ru.shrf.testjob.entity.User;

@Component
public class DtoMapperImpl implements DtoMapper{

    @Override
    public UserRegistrationRequestDTO toUserRegistrationRequestDTO(User user) {
        return UserRegistrationRequestDTO.builder()
                .email(user.getEmail())
                .name(user.getName())
                .username(user.getUsername())
                .build();
    }

    @Override
    public User toUser(UserRegistrationRequestDTO request) {
        return User.builder()
                .email(request.getEmail())
                .name(request.getName())
                .username(request.getUsername())
                .build();
    }

    @Override
    public UserProfileResponseDTO toUserProfileResponseDTO(User user) {
        return UserProfileResponseDTO.builder()
                .id(user.getId())
                .username(user.getUsername())
                .name(user.getName())
                .email(user.getEmail())
                .build();
    }

    @Override
    public User toUser(UserProfileResponseDTO response) {
        return User.builder()
                .id(response.getId())
                .email(response.getEmail())
                .name(response.getName())
                .username(response.getUsername())
                .build();
    }

    @Override
    public Movie toMovie(MovieResponseDTO response) {
        return Movie.builder()
                .title(response.getTitle())
                .posterPath(response.getPosterPath())
                .build();
    }

    @Override
    public MovieResponseDTO toMovieResponseDTO(Movie movie) {
        return MovieResponseDTO.builder()
                .title(movie.getTitle())
                .posterPath(movie.getPosterPath())
                .build();
    }
}
