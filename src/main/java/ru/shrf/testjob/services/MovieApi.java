package ru.shrf.testjob.services;

import org.springframework.stereotype.Component;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;
import ru.shrf.testjob.dto.DiscoverResponseDTO;


@Component
public interface MovieApi {
    @GET("discover/movie")
    Call<DiscoverResponseDTO> getMoviesFromDiscover(
            @Query("api_key") String apiKey,
            @Query("page") int page
    );
}