package ru.shrf.testjob.services;


import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
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

    @Override
    public void registerUser(UserRegistrationRequestDTO request) {

        checkDuplicateEmail(request.getEmail());
        checkDuplicateUser(request.getUsername());

        User user = new User();
        user.setEmail(request.getEmail());
        user.setUsername(request.getUsername());
        user.setName(request.getName());

        userRepository.save(user);
    }

    @Override
    public UserProfileResponseDTO getUserProfile(Long userId) {
        User user = findById(userId);

        UserProfileResponseDTO response = new UserProfileResponseDTO();
        response.setId(user.getId());
        response.setEmail(user.getEmail());
        response.setUsername(user.getUsername());
        response.setName(user.getName());

        return response;
    }

    @Override
    public void updateUserProfile(Long userId, UserUpdateRequestDTO request) {

        checkDuplicateUser(request.getUsername());

        User user = findById(userId);
        user.setUsername(request.getUsername());
                user.setName(request.getName());

        userRepository.save(user);
    }

    @Override
    public void deleteUserProfile(Long userId) {
        User user = findById(userId);
        userRepository.delete(user);
    }

    public void addMovieToFavorites(Long userId, String movieTitle) {
        User user = findById(userId);
        Movie movie = findByTitle(movieTitle);
        user.getFavoriteMovies().add(movie);
        userRepository.save(user);
    }

    public void removeMovieFromFavorites(Long userId, String movieTitle) {
        User user = findById(userId);
        Movie movie = findByTitle(movieTitle);
        user.getFavoriteMovies().remove(movie);
        userRepository.save(user);
    }

    private void checkDuplicateEmail(String email){
        if (userRepository.findByEmail(email).isPresent()) {
            throw new DuplicateException("Email already exists");
        }
    }

    private void checkDuplicateUser(String username){
        if (userRepository.findByUsername(username).isPresent()) {
            throw new DuplicateException("Username already exists");
        }
    }

    private User findById(Long userId){
        return userRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("User not found"));
    }

    private Movie findByTitle(String title){
        return movieRepository.findByTitle(title)
                .orElseThrow(() -> new NotFoundException("Movie not found"));
    }

}