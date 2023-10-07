package com.cityguide.cityguidemanagement.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.cityguide.cityguidemanagement.dao.ReviewRepository;
import com.cityguide.cityguidemanagement.entity.Reviews;
import com.cityguide.cityguidemanagement.exception.AttractionNotFoundException;
import com.cityguide.cityguidemanagement.exception.UserNotFoundException;

class ReviewsServiceTest {

	@Mock
	private ReviewRepository reviewRepository;

	@InjectMocks
	private ReviewsService reviewsService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testAddReview() {
		Reviews review = new Reviews();
		when(reviewRepository.save(review)).thenReturn(review);

		Reviews result = reviewsService.addReview(review);

		assertEquals(review, result);
		verify(reviewRepository).save(review);
	}

	@Test
	void testDeleteReview() {
		int id = 1;
		doNothing().when(reviewRepository).deleteById(id);

		assertDoesNotThrow(() -> reviewsService.deleteReview(id));

		verify(reviewRepository).deleteById(id);
	}

	@Test
	void testLikeReview() {
		int id = 1;
		doNothing().when(reviewRepository).likeReview(id);

		assertDoesNotThrow(() -> reviewsService.likeReview(id));

		verify(reviewRepository).likeReview(id);
	}

	@Test
	void testReportReview() {
		int id = 1;
		doNothing().when(reviewRepository).reportReview(id);

		assertDoesNotThrow(() -> reviewsService.reportReview(id));

		verify(reviewRepository).reportReview(id);
	}
//
//	@Test
//	void testSortReviewsByReports() {
//		List<Reviews> reviews = new ArrayList<>();
//		when(reviewRepository.sortReviewsByReports()).thenReturn(reviews);
//
//		List<Reviews> result = reviewsService.sortReviewsByReports();
//
//		assertEquals(reviews, result);
//		verify(reviewRepository).sortReviewsByReports();
//	}
//
//	@Test
//	void testSortReviewsByLikes() {
//		List<Reviews> reviews = new ArrayList<>();
//		when(reviewRepository.sortReviewsByLikes()).thenReturn(reviews);
//
//		List<Reviews> result = reviewsService.sortReviewsByLikes();
//
//		assertEquals(reviews, result);
//		verify(reviewRepository).sortReviewsByLikes();
//	}

//	@Test
//	void testFindAllReviewsByAttraction() {
//		int id = 1;
//		List<Reviews> reviews = new ArrayList<>();
//		when(reviewRepository.findAllReviewsByAttraction(id)).thenReturn(reviews);
//
//		assertDoesNotThrow(() -> {
//			List<Reviews> result = reviewsService.findAllReviewsByAttraction(id);
//			assertEquals(reviews, result);
//		});
//
//		verify(reviewRepository).findAllReviewsByAttraction(id);
//	}

	@Test
	void testFindAllReviewsByAttraction_AttractionNotFoundException() {
		int id = 3;
		when(reviewRepository.findAllReviewsByAttraction(id)).thenReturn(new ArrayList<>());

		AttractionNotFoundException exception = assertThrows(AttractionNotFoundException.class,
				() -> reviewsService.findAllReviewsByAttraction(id));

		assertEquals("No Review in this Attraction ", exception.getMessage());
		verify(reviewRepository).findAllReviewsByAttraction(id);
	}

//	@Test
//	void testFindAllReviewsByUser() {
//		int id = 1;
//		List<Reviews> reviews = new ArrayList<>();
//		when(reviewRepository.findAllReviewsByUser(id)).thenReturn(reviews);
//
//		assertDoesNotThrow(() -> {
//			List<Reviews> result = reviewsService.findAllReviewsByUser(id);
//			assertEquals(reviews, result);
//		});
//
//		verify(reviewRepository).findAllReviewsByUser(id);
//	}

	@Test
	void testFindAllReviewsByUser_UserNotFoundException() {
		int id = 3;
		when(reviewRepository.findAllReviewsByUser(id)).thenReturn(new ArrayList<>());

		UserNotFoundException exception = assertThrows(UserNotFoundException.class,
				() -> reviewsService.findAllReviewsByUser(id));

		assertEquals("No Review by this User", exception.getMessage());
		verify(reviewRepository).findAllReviewsByUser(id);
	}
}
