package com.cognizant.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cognizant.exception.FavoritesEmptyException;
import com.cognizant.service.FavoritesService;

import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/favorites")
@Slf4j
public class FavoriteController {
	
	@Autowired
	FavoritesService service;
	/**
	 * 
	 * @param userId
	 * @param movieId
	 * @return Success/failure message after adding favorite movie
	 */
	//localhost:8081/favorites/1/1
	@PostMapping("/{userId}/{movieId}")
	public String addFavoritesMovie(@PathVariable int userId, @PathVariable int movieId)
	{
		log.info("Add Favorite started");
		String s=service.addFavoritesMovie(userId, movieId);
		log.debug(s);
		log.info("ended");
		return s;
	}
	/**
	 * 
	 * @param userId
	 * @return List of favorite movies for user id
	 * @throws FavoritesEmptyException
	 */
	//localhost:8081/favorites/1
	@GetMapping("/{userId}")
	public List<Object> getAllFavoriteMovies(@PathVariable int userId) throws FavoritesEmptyException
	{
		log.info("Get All user Favourite movies service started");
		List<Object> l=service.getAllFavoriteMovies(userId);
		log.debug("Favorite Movies {}",l);
		log.info("Ended");
		return l;
	}
	/**
	 * 
	 * @param userId
	 * @param movieId
	 * @return success / failure message after deleting the movie
	 */
	//localhost:8081/favorites/1/1
	@DeleteMapping("/{userId}/{movieId}")
	public String deleteFavoriteMovie(@PathVariable int userId, @PathVariable int movieId)
	{
		log.info("delete service started");
		String s=service.removeFavoriteMovie(userId, movieId);
		log.debug(s);
		log.info("Ended");
		return s;
	}

}