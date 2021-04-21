package com.cognizant.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.Dao.MovieDao;
import com.cognizant.model.Movie;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class MovieServiceImpl implements MovieService {

	@Autowired
	private MovieDao md;

	/**
	 * 
	 * @return all the movies as a list
	 */
	@Override
	public List<Movie> getMovieListAdmin() {
		log.info("started get all Movies service");
		List<Movie> l = md.findAll();
		log.info("ended");
		return l;
	}

	/**
	 * 
	 * @return all movies the has already released
	 */
	@Override
	public List<Movie> getMovieListCustomer() {
		log.info("started get customer movies service");
		List<Movie> l = md.findAll().stream().filter(o -> o.getDateOfLaunch().before(new Date()))
				.collect(Collectors.toList());
		log.info("ended");
		return l;
	}

	/**
	 * 
	 * @param movieId
	 * @return the movie with the given Id
	 */
	@Override
	public Movie getMovie(int movieId) {
		log.info("started get movie service");
		Movie i = md.findById(movieId).get();
		log.info("ended");
		return i;
	}

	/**
	 * 
	 * @param movie
	 * @return success / failure message after editing the movie
	 */
	@Override
	public String modifyMovie(Movie movie) {
		log.info("started edit movie service");
		Optional<Movie> m = md.findById(movie.getId());
		if (m.isPresent()) {
			md.save(movie);
			log.info("ended");
			return "Movie Updated Successfully";
		}
		log.info("ended");
		return "Movie Not Found";
	}

	

	@Override
	public Movie AddMovieList(Movie movie) {
		return md.save(movie);
	}

}
