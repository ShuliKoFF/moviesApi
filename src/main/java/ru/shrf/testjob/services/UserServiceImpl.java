package ru.shrf.testjob.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.shrf.testjob.mapper.DtoMapper;
import ru.shrf.testjob.dto.UserProfileResponseDTO;
import ru.shrf.testjob.dto.UserRegistrationRequestDTO;
import ru.shrf.testjob.dto.UserUpdateRequestDTO;
import ru.shrf.testjob.entity.Movie;
import ru.shrf.testjob.entity.User;
import ru.shrf.testjob.exeption.DuplicateException;
import ru.shrf.testjob.exeption.NotFoundException;
import ru.shrf.testjob.repository.MovieRepository;
import ru.shrf.testjob.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final MovieRepository movieRepository;
    private final DtoMapper dtoMapper;


    @Override
    @Transactional
    public void registerUser(UserRegistrationRequestDTO request) {
        checkDuplicateEmail(request.getEmail());
        checkDuplicateUser(request.getUsername());
        User user = dtoMapper.toUser(request);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public UserProfileResponseDTO getUserProfile(Long userId) {
        User user = findById(userId);
        UserProfileResponseDTO response = dtoMapper.toUserProfileResponseDTO(user);
        return response;
    }

    @Override
    @Transactional
    public void updateUserProfile(Long userId, UserUpdateRequestDTO request) {
        checkDuplicateUser(request.getUsername());
        User user = findById(userId);
        user.setUsername(request.getUsername());
        user.setName(request.getName());
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void deleteUserProfile(Long userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }

    @Override
    @Transactional
    public void addMovieToFavorites(Long userId, String movieTitle) {
        User user = findById(userId);
        Movie movie = findByTitle(movieTitle);
        user.getFavoriteMovies().add(movie);
        userRepository.save(user);
    }

    @Override
    @Transactional
    public void removeMovieFromFavorites(Long userId, String movieTitle) {
        User user = findById(userId);
        Movie movie = findByTitle(movieTitle);
        user.getFavoriteMovies().remove(movie);
        userRepository.save(user);
    }

    private void checkDuplicateEmail(String email) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicateException("Email already exists");
        }
    }

    private void checkDuplicateUser(String username) {
        if (userRepository.findByUsername(username).isPresent()) {
            throw new DuplicateException("Username already exists");
        }
    }

    private User findById(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private Movie findByTitle(String title) {
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Movie not found"));
    }

}