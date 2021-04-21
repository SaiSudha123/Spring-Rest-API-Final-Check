package com.cognizant.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cognizant.Dao.FavoritesDao;
import com.cognizant.Dao.MovieDao;
import com.cognizant.Dao.UserDao;
import com.cognizant.exception.FavoritesEmptyException;
import com.cognizant.model.Favorites;
import com.cognizant.model.Movie;
import com.cognizant.model.User;

import lombok.extern.slf4j.Slf4j;
@Service
@Slf4j
public class FavoritesServiceImpl implements FavoritesService {

	@Autowired
	private FavoritesDao fd;
	@Autowired
	private MovieDao md;
	@Autowired
	private UserDao ud;
	/**
	 * 
	 * @param userId
	 * @param movieId
	 * @return Success/failure message after adding favorite movie
	 */
	@Override
	public String addFavoritesMovie(int userId, int movieId) {
		log.info("Started add to Favorite service");
		Optional<Movie> m=md.findById(movieId);
		if(m.isPresent()) {
			User u=ud.findById(userId).get();
			Favorites fav=new Favorites(m.get(),u);
			fd.save(fav);
			log.info("ended");
			return "Movie added Successfully";
		}
		log.info("ended");
		return "Movie Not Found";

	}
	/**
	 * 
	 * @param userId
	 * @return List of favorite movies for user id
	 * @throws FavoritesEmptyException
	 */
	@Override
	public List<Object> getAllFavoriteMovies(int userId) throws FavoritesEmptyException{
		log.info("Started get Fav Movies service");
		List<Object> l=fd.getItems(userId);
		if(l.isEmpty()) {
			throw new FavoritesEmptyException("No Favorites added yet");
		}
		log.info("Ended");
		return l;
	}
	/**
	 * 
	 * @param userId
	 * @param movieId
	 * @return success / failure message after deleting the movie
	 */
	@Override
	public String removeFavoriteMovie(int userId, int movieId) {
		log.info("Started delete Fav Movie service");
		Optional<Movie> m=md.findById(movieId);
		if(m.isPresent()) {
			fd.deleteFromFav(userId, movieId);
			log.info("Ended");
			return "Movie Successfully deleted";
		}
		log.info("Ended");
		return "Movie Not found";
	}

}
