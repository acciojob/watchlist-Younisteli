package com.driver;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.*;
import java.util.List;
import java.util.Map;


@RestController
@RequestMapping("movies")
public class MovieController {
    @Autowired
    MovieService movieService;
    Map<String,List<Movie>> directorMoviepair = new HashMap<>();
    @PostMapping("/add-movie")
    public ResponseEntity addMovie(@RequestBody Movie movie){
        movieService.addMovie(movie);
        return  new ResponseEntity<>("success", HttpStatus.OK);
    }
    @PostMapping("/add-director")
    public ResponseEntity addDirector(@RequestBody Director director) {
        movieService.addDirector(director);
        directorMoviepair.put(director.getName(), new ArrayList<>());
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @GetMapping("/get-all-movies")
    List<String> findAllMovies(){
        return movieService.getAllMovies();
    }
    @PutMapping("/add-movie-director-pair")
    public  ResponseEntity addMovieDirectorPair(@RequestParam String MName,String DName){
        Movie movie = movieService.getMovieByName(MName);
        for(Map.Entry<String, List<Movie>> e: directorMoviepair.entrySet()){
            if(e.getKey().equals(DName));
            directorMoviepair.get(DName).add(movie);
        }
        return new ResponseEntity<>("success", HttpStatus.OK);
    }
    @GetMapping("/get-movie-by-name/{name}")
    public  ResponseEntity<Movie> getMovieByName(@PathVariable("name") String name){
        Movie m = movieService.getMovieByName(name);
        return new ResponseEntity<>(m, HttpStatus.OK);
    }

    @GetMapping("/get-director-by-name/{name}")
    public  ResponseEntity<Director> getDirectorByDirectorName(@PathVariable("name") String name){
        Director d = movieService.getDirectorByDirectorName(name);
       return new ResponseEntity<>(d, HttpStatus.OK);
    }

    @GetMapping("/get-movies-by-director-name/{director}")
    public  ResponseEntity<List<String>> getMoviesByDirectorName(@PathVariable("name") String dname){
        List<Movie> movieList = new ArrayList<>();
        for(Map.Entry<String, List<Movie>> e: directorMoviepair.entrySet()){
            if(e.getKey().equals(dname));
            movieList = e.getValue();

        }
        // done

        List<String> movieNames = new ArrayList<>();
        for(Movie m : movieList){
            movieNames.add(m.getName());

        }
        return new ResponseEntity<>(movieNames, HttpStatus.OK);
    }

    @DeleteMapping("/delete-director-by-name")
    public ResponseEntity deleteDirectorByName(@RequestParam String name){
        List<Movie> movieList = new ArrayList<>();
        for(Map.Entry<String, List<Movie>> e: directorMoviepair.entrySet()){
            if(e.getKey().equals(name));
            movieService.deleteDirector(name);
            movieList=e.getValue();
            for(Movie m : movieList){
                movieService.deleteMovie(m.getName());

            }
            directorMoviepair.remove(e.getKey());


        }
        return new ResponseEntity<>("Success", HttpStatus.OK);
    }
    @DeleteMapping("/delete-all-directors")
    public ResponseEntity deleteAllDirectors(){
        movieService.deleteAllDirector();
        List<Movie> movieList = new ArrayList<>();
        for(Map.Entry<String, List<Movie>> e: directorMoviepair.entrySet()){
            movieList=e.getValue();
            for(Movie m : movieList){
                movieService.deleteMovie(m.getName());

            }
            directorMoviepair.remove(e.getKey());


        }
        return new ResponseEntity<>("Success", HttpStatus.OK);

    }



}
