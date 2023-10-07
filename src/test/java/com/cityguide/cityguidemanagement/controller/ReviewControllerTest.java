package com.cityguide.cityguidemanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cityguide.cityguidemanagement.entity.Reviews;
import com.cityguide.cityguidemanagement.exception.AttractionNotFoundException;
import com.cityguide.cityguidemanagement.exception.UserNotFoundException;
import com.cityguide.cityguidemanagement.model.ReviewInputModel;
import com.cityguide.cityguidemanagement.model.ReviewOutputModel;
import com.cityguide.cityguidemanagement.service.ReviewsService;

public class ReviewControllerTest {

	@Mock
	private ReviewsService reviewService;

	@InjectMocks
	private ReviewsController reviewsController;

	@BeforeEach
	public void setup() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testFindAllReviewsByAttraction_withExistingAttraction() throws AttractionNotFoundException {
		int attractionId = 1;
		List<ReviewOutputModel> reviews = new ArrayList<>();
		when(reviewService.findAllReviewsByAttraction(attractionId)).thenReturn(reviews);

		ResponseEntity<List<ReviewOutputModel>> response = reviewsController.findAllReviewsByAttraction(attractionId);

		assertEquals(reviews, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testAddReview() throws UserNotFoundException {
		Reviews review = new Reviews();
		when(reviewService.addReview(review)).thenReturn(review);
		ReviewInputModel rim = new ReviewInputModel();
		rim.setAid(review.getAttraction().getId());
		rim.setComments(review.getComments());
		rim.setId(review.getId());
		rim.setUid(review.getUser().getId());
		ResponseEntity<Reviews> response = reviewsController.addReview(rim);

		assertEquals(review, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testDeleteReview() {
		int reviewId = 1;
		doNothing().when(reviewService).deleteReview(reviewId);

		reviewsController.deleteReview(reviewId);
	}

	@Test
	public void testLikeReview() {
		int reviewId = 1;
		doNothing().when(reviewService).likeReview(reviewId);

		reviewsController.likeReview(reviewId);
	}

	@Test
	public void testReportReview() {
		int reviewId = 1;
		doNothing().when(reviewService).reportReview(reviewId);

		reviewsController.reportReview(reviewId);
	}

	@Test
	public void testSortReviewsByReports() {
		List<ReviewOutputModel> sortedReviews = new ArrayList<>();
		when(reviewService.sortReviewsByReports()).thenReturn(sortedReviews);

		ResponseEntity<List<ReviewOutputModel>> response = reviewsController.sortReviewsByReports();

		assertEquals(sortedReviews, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testSortReviewsByLikes() {
		List<ReviewOutputModel> sortedReviews = new ArrayList<>();
		when(reviewService.sortReviewsByLikes()).thenReturn(sortedReviews);

		ResponseEntity<List<ReviewOutputModel>> response = reviewsController.sortReviewsByLikes();

		assertEquals(sortedReviews, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	public void testFindAllReviewsByUser_withExistingUser() throws UserNotFoundException {
		int userId = 1;
		List<ReviewOutputModel> reviews = new ArrayList<>();
		when(reviewService.findAllReviewsByUser(userId)).thenReturn(reviews);

		ResponseEntity<List<ReviewOutputModel>> response = reviewsController.findAllReviewsByUser(userId);

		assertEquals(reviews, response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}
}
