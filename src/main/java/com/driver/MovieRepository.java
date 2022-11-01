package com.driver;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@Component
public class MovieRepository {
    List<Movie> Movies = new ArrayList<>();
    List<Director> Directors = new ArrayList<>();
    public void  addMovie( Movie movie){
        Movies.add(movie);
    }

    public void  addDirector( Director director){
        Directors.add(director);
    }
     Movie getMovieByName(String name){
        for(Movie m : Movies){
            if(m.getName().equals(name)){
                return m;
            }
        }
        return  null;
    }
    public  List<String>  findAllMovies(){
        List<String> movieList = new ArrayList<>();
        for(Movie m : Movies){
            movieList.add(m.getName());

        }
        return movieList;
    }
    Director getDirectorByDirectorName(String name){
        for(Director d: Directors){
            if(d.getName().equals(name)){
                return d;
            }
        }
        return  null;
    }

    void deleteDirector(String name){
        for(int i=0; i<Directors.size(); i++){
            for(Director d: Directors){
                if(Directors.get(i).getName().equals(name)){
                    Directors.remove(i);
                }
            }
        }
    }
    void deleteMovie(String name){
        for(int i=0; i<Movies.size(); i++){
            for(Movie m: Movies){
                if(Movies.get(i).getName().equals(name)){
                    Movies.remove(i);
                }
            }
        }
    }
    void  deleteAllDirectors(){
        Directors = new ArrayList<>();
    }

}
