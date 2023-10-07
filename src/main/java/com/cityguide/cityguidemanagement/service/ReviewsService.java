package com.cityguide.cityguidemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cityguide.cityguidemanagement.entity.Reviews;
import com.cityguide.cityguidemanagement.exception.AttractionNotFoundException;
import com.cityguide.cityguidemanagement.exception.UserNotFoundException;
import com.cityguide.cityguidemanagement.model.ReviewOutputModel;

@Service
public interface ReviewsService {
	public Reviews addReview(Reviews review);

	public void deleteReview(int id);

	public void likeReview(int id);

	public void reportReview(int id);

	public List<ReviewOutputModel> sortReviewsByReports();

	public List<ReviewOutputModel> sortReviewsByLikes();

	public List<ReviewOutputModel> findAllReviewsByAttraction(int id) throws AttractionNotFoundException;

	public List<ReviewOutputModel> findAllReviewsByUser(int id) throws UserNotFoundException;
}
