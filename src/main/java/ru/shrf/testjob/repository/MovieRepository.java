package ru.shrf.testjob.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.shrf.testjob.entity.Movie;
import ru.shrf.testjob.entity.User;

import java.util.List;
import java.util.Optional;


@Repository
    public interface MovieRepository extends JpaRepository<Movie, Long> {

    Page<Movie> findAll(Pageable pageable);

    Optional<Movie> findByTitle(String title);

    List<Movie> findByUsersNotContaining(User user);
}
