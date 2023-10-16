package ru.shrf.testjob.services;

import ru.shrf.testjob.dto.UserProfileResponseDTO;
import ru.shrf.testjob.dto.UserRegistrationRequestDTO;
import ru.shrf.testjob.dto.UserUpdateRequestDTO;

public interface UserService {
    void registerUser(UserRegistrationRequestDTO request);

    UserProfileResponseDTO getUserProfile(Long userId);

    void updateUserProfile(Long userId, UserUpdateRequestDTO request);

    void deleteUserProfile(Long userId);

    void addMovieToFavorites(Long userId, String movieTitle);

    void removeMovieFromFavorites(Long userId, String movieTitle);
}
