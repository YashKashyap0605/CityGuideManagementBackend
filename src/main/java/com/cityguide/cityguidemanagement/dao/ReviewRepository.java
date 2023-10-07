package com.cityguide.cityguidemanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cityguide.cityguidemanagement.entity.Reviews;

public interface ReviewRepository extends JpaRepository<Reviews, Integer> {

	@Query(value = "SELECT r from Reviews r WHERE r.attraction.id=?1")
	public List<Reviews> findAllReviewsByAttraction(int id);

	@Query(value = "SELECT r from Reviews r ORDER BY r.likes DESC")
	public List<Reviews> sortReviewsByLikes();

	@Query(value = "SELECT r from Reviews r ORDER BY r.reports DESC")
	public List<Reviews> sortReviewsByReports();

	@Modifying
	@Query(value = "UPDATE Reviews r SET r.likes = r.likes+1 where r.id=?1")
	public void likeReview(int id);

	@Modifying
	@Query(value = "UPDATE Reviews r SET r.reports = r.reports+1 where r.id=?1")
	public void reportReview(int id);

	@Query(value = "SELECT r from Reviews r WHERE r.user.id=?1")
	public List<Reviews> findAllReviewsByUser(int id);

}
