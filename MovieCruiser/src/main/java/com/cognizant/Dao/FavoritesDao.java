package com.cognizant.Dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cognizant.model.Favorites;
@Repository
public interface FavoritesDao extends JpaRepository<Favorites, Integer>{
	@Transactional
	@Modifying
	@Query("delete from Favorites f where f.user.id=?1 and f.movie.id=?2")
	void deleteFromFav(int userId, int movieId);
	@Query("select i from Movie i join Favorites c on i.id=c.movie.id where c.user.id=?1")
	List<Object> getItems(int userId);

}
