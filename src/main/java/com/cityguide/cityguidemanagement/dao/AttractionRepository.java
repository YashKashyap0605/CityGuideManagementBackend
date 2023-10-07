package com.cityguide.cityguidemanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cityguide.cityguidemanagement.entity.Attractions;

public interface AttractionRepository extends JpaRepository<Attractions, Integer> {

	@Query(value = "select a from Attractions a where a.rating>=?1")
	public List<Attractions> getAllAttractionsByRatings(float ratings);

	@Query(value = "select a from Attractions a where a.likes>=?1")
	public List<Attractions> getAllAttractionsByLikes(int likes);

	@Query(value = "select a from Attractions a where a.name=?1")
	public Attractions getAttractionByName(String name);

	@Query(value = "select a from Attractions a where a.reports>=?1")
	public List<Attractions> getAttractionByReports(int reports);

	@Query(value = "select a.name from Attractions a")
	public List<String> getAllNamesOfAttractions();

	@Query("SELECT a FROM Attractions a WHERE a.name LIKE ?1%")
	List<Attractions> searchByKeyword(String key);

	@Query(value = "SELECT a from Attractions a ORDER BY a.distanceFromStation")
	public List<Attractions> findClosestAttractionsToStations();

	@Query(value = "SELECT a from Attractions a WHERE a.user.id=?1")
	public List<Attractions> findAllAttractionsByUser(int id);

	@Query(value = "SELECT a from Attractions a WHERE a.type.id=?1")
	public List<Attractions> getAttractionsOfType(int id);

	@Modifying
	@Query(value = "UPDATE Attractions a SET a.likes = a.likes+1 where a.id=?1")
	public void likeAttraction(int id);

	@Modifying
	@Query(value = "UPDATE Attractions a SET a.reports = a.reports+1 where a.id=?1")
	public void reportAttraction(int id);

}
