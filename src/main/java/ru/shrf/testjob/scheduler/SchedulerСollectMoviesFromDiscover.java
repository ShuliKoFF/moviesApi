package ru.shrf.testjob.scheduler;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import ru.shrf.testjob.services.MovieService;

@Slf4j
@Service
@EnableScheduling
@EnableAsync
@RequiredArgsConstructor
public class SchedulerСollectMoviesFromDiscover {

    public final MovieService movieService;

    @Scheduled(cron = "${scheduler.collectMoviesFromDiscover.cron}")
    public void collectMoviesFromDiscover() {
        movieService.collectMoviesFromDiscover();
    }
}
