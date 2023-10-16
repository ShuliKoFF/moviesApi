package ru.shrf.testjob.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.shrf.testjob.services.MovieApi;

@Configuration
public class ConfigurationApp {

    private static final String URL = "https://api.themoviedb.org/3/";

@Bean
    public MovieApi creatMovieApi(){
    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build();

   return retrofit.create(MovieApi.class);
}

}
