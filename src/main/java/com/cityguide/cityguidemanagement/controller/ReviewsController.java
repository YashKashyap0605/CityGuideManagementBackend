package com.cityguide.cityguidemanagement.controller;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cityguide.cityguidemanagement.entity.Attractions;
import com.cityguide.cityguidemanagement.entity.Reviews;
import com.cityguide.cityguidemanagement.entity.Users;
import com.cityguide.cityguidemanagement.exception.AttractionNotFoundException;
import com.cityguide.cityguidemanagement.exception.UserNotFoundException;
import com.cityguide.cityguidemanagement.model.ReviewInputModel;
import com.cityguide.cityguidemanagement.model.ReviewOutputModel;
import com.cityguide.cityguidemanagement.service.AttractionsService;
import com.cityguide.cityguidemanagement.service.ReviewsService;
import com.cityguide.cityguidemanagement.service.UserService;

@RestController
public class ReviewsController {

	Logger logger = LoggerFactory.getLogger(AttractionsController.class);

	@Autowired
	ReviewsService reviewService;

	@Autowired
	UserService us;

	@Autowired
	AttractionsService as;

	@GetMapping("/findallreviewsbyattraction/{id}")
	public ResponseEntity<List<ReviewOutputModel>> findAllReviewsByAttraction(@PathVariable("id") int id)
			throws AttractionNotFoundException {
		List<ReviewOutputModel> reviewByAttraction = reviewService.findAllReviewsByAttraction(id);
		logger.info("All Reviews in this ATtractions are : " + reviewByAttraction);
		return new ResponseEntity<List<ReviewOutputModel>>(reviewByAttraction, HttpStatus.OK);

	}

	@PostMapping("/addreview")
	public ResponseEntity<Reviews> addReview(@RequestBody ReviewInputModel review) throws UserNotFoundException {
		Reviews r = new Reviews();
		int aid = review.getAid();
		Attractions a = as.getAttractionById(aid);
		r.setAttraction(a);
		r.setComments(review.getComments());
		Timestamp ts = Timestamp.from(Instant.now());
		r.setCreatedAt(ts);
		r.setId(review.getId());
		r.setLikes(0);
		r.setReports(0);
		int uid = review.getUid();
		Users u = us.getUserById(uid);
		r.setUser(u);
		r = reviewService.addReview(r);
		logger.info("Review Added Successfully");
		return new ResponseEntity<Reviews>(r, HttpStatus.OK);
	}

	@DeleteMapping("/deletereview/{id}")
	public void deleteReview(@PathVariable("id") int id) {
		logger.info("Review Deleted Successfully");
		reviewService.deleteReview(id);
	}

	@PutMapping("/likereview/{id}")
	public void likeReview(@PathVariable("id") int id) {
		logger.info("Review Liked Successfully");
		reviewService.likeReview(id);
	}

	@PutMapping("/reportreview/{id}")
	public void reportReview(int id) {
		logger.info("Review Reported Successfully");
		reviewService.reportReview(id);
	}

	@GetMapping("/sortreviewsbyreports")
	public ResponseEntity<List<ReviewOutputModel>> sortReviewsByReports() {
		List<ReviewOutputModel> r = new ArrayList<>();
		r = reviewService.sortReviewsByReports();
		return new ResponseEntity<List<ReviewOutputModel>>(r, HttpStatus.OK);
	}

	@GetMapping("/sortreviewsbylikes")
	public ResponseEntity<List<ReviewOutputModel>> sortReviewsByLikes() {
		List<ReviewOutputModel> r = new ArrayList<>();
		r = reviewService.sortReviewsByLikes();
		logger.info("Sorted reviews as per likes ");
		return new ResponseEntity<List<ReviewOutputModel>>(r, HttpStatus.OK);
	}

	@GetMapping("/findallreviewsbyuser/{id}")
	public ResponseEntity<List<ReviewOutputModel>> findAllReviewsByUser(int id) throws UserNotFoundException {
		List<ReviewOutputModel> reviewByUser = reviewService.findAllReviewsByUser(id);
		logger.info("Reviews added by user : " + reviewByUser);
		return new ResponseEntity<List<ReviewOutputModel>>(reviewByUser, HttpStatus.OK);
	}

}
