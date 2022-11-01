package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class MovieService {

    @Autowired
    MovieRepository movieRepository;
    void addMovie(Movie movie){
        movieRepository.addMovie(movie);
    }
    void addDirector(Director director){
        movieRepository.addDirector(director);
    }
    List<String> getAllMovies(){
        return movieRepository.findAllMovies();
    }
    Movie getMovieByName(String name){
        return movieRepository.getMovieByName(name);

    }
    Director getDirectorByDirectorName(String name){
        return movieRepository.getDirectorByDirectorName(name);
    }
    void deleteDirector(String name){
        movieRepository.deleteDirector(name);
    }
    void deleteMovie(String name) {
        movieRepository.deleteMovie(name);
    }
    void deleteAllDirector() {
        movieRepository.deleteAllDirectors();
    }



}
