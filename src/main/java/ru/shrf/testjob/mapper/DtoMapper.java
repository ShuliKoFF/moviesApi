package ru.shrf.testjob.mapper;

import ru.shrf.testjob.dto.MovieResponseDTO;
import ru.shrf.testjob.dto.UserProfileResponseDTO;
import ru.shrf.testjob.dto.UserRegistrationRequestDTO;
import ru.shrf.testjob.entity.Movie;
import ru.shrf.testjob.entity.User;



public interface DtoMapper {

    UserRegistrationRequestDTO toUserRegistrationRequestDTO(User user);

    User toUser(UserRegistrationRequestDTO request);

    UserProfileResponseDTO toUserProfileResponseDTO(User user);

    User toUser(UserProfileResponseDTO response);

    Movie toMovie(MovieResponseDTO response);

    MovieResponseDTO toMovieResponseDTO(Movie movie);
}

