package ru.shrf.testjob.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.shrf.testjob.services.MovieApi;

@Configuration
public class ConfigurationApp {

    @Value(value = "${themoviedb_URL}")
    private String url;

    @Bean
    public MovieApi creatMovieApi() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(MovieApi.class);
    }
}
