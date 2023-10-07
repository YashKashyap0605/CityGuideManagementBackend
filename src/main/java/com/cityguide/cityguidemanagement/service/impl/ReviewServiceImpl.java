package com.cityguide.cityguidemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityguide.cityguidemanagement.dao.ReviewRepository;
import com.cityguide.cityguidemanagement.entity.Reviews;
import com.cityguide.cityguidemanagement.exception.AttractionNotFoundException;
import com.cityguide.cityguidemanagement.exception.UserNotFoundException;
import com.cityguide.cityguidemanagement.model.AttractionOutputModel;
import com.cityguide.cityguidemanagement.model.ReviewOutputModel;
import com.cityguide.cityguidemanagement.model.UserOutputModel;
import com.cityguide.cityguidemanagement.service.ReviewsService;

import jakarta.transaction.Transactional;

@Service
public class ReviewServiceImpl implements ReviewsService {
	@Autowired
	ReviewRepository reviewRepository;

	// ADD A REVIEW

	@Transactional
	@Override
	public Reviews addReview(Reviews review) {
		return reviewRepository.save(review);
	}

	// DELETE A REVIEW

	@Transactional
	@Override
	public void deleteReview(int id) {
		reviewRepository.deleteById(id);
	}

	// LIKE A REVIEW

	@Transactional
	@Override
	public void likeReview(int id) {
		reviewRepository.likeReview(id);
	}

	// REPORT A REVIEW

	@Transactional
	@Override
	public void reportReview(int id) {
		reviewRepository.reportReview(id);
	}

	// SORT REVIEW ON BASIS OF REPORTS

	@Transactional
	@Override
	public List<ReviewOutputModel> sortReviewsByReports() {
		List<Reviews> rl = new ArrayList<>();
		List<ReviewOutputModel> rom = new ArrayList<>();
		rl = reviewRepository.sortReviewsByReports();
		for (Reviews r : rl) {
			UserOutputModel uom = new UserOutputModel();
			uom.setEmail(r.getUser().getEmail());
			uom.setFirstName(r.getUser().getFirstName());
			uom.setId(r.getUser().getId());
			uom.setLastName(r.getUser().getLastName());
			uom.setUserName(r.getUser().getUserName());

			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(r.getAttraction().getDistanceFromStation());
			aom.setId(r.getAttraction().getId());
			aom.setName(r.getAttraction().getName());
			aom.setRating(r.getAttraction().getRating());
			aom.setReports(r.getAttraction().getReports());
			aom.setLikes(r.getAttraction().getLikes());
			aom.setType(r.getAttraction().getType());
			aom.setUserOutputModel(uom);

			ReviewOutputModel rev = new ReviewOutputModel();
			rev.setComments(r.getComments());
			rev.setCreatedAt(r.getCreatedAt());
			rev.setId(r.getId());
			rev.setLikes(r.getLikes());
			rev.setReports(r.getReports());
			rev.setAttractionOutputModel(aom);
			rev.setUserOutputModel(uom);
			rom.add(rev);
		}
		return rom;
	}

	// SORT REVIEW ON BASIS OF LIKES

	@Transactional
	@Override
	public List<ReviewOutputModel> sortReviewsByLikes() {
		List<Reviews> rl = new ArrayList<>();
		List<ReviewOutputModel> rom = new ArrayList<>();
		rl = reviewRepository.sortReviewsByLikes();
		for (Reviews r : rl) {
			UserOutputModel uom = new UserOutputModel();
			uom.setEmail(r.getUser().getEmail());
			uom.setFirstName(r.getUser().getFirstName());
			uom.setId(r.getUser().getId());
			uom.setLastName(r.getUser().getLastName());
			uom.setUserName(r.getUser().getUserName());

			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(r.getAttraction().getDistanceFromStation());
			aom.setId(r.getAttraction().getId());
			aom.setName(r.getAttraction().getName());
			aom.setRating(r.getAttraction().getRating());
			aom.setReports(r.getAttraction().getReports());
			aom.setType(r.getAttraction().getType());
			aom.setLikes(r.getAttraction().getLikes());
			aom.setUserOutputModel(uom);

			ReviewOutputModel rev = new ReviewOutputModel();
			rev.setComments(r.getComments());
			rev.setCreatedAt(r.getCreatedAt());
			rev.setId(r.getId());
			rev.setLikes(r.getLikes());
			rev.setReports(r.getReports());
			rev.setAttractionOutputModel(aom);
			rev.setUserOutputModel(uom);
			rom.add(rev);
		}
		return rom;
	}

	// GET ALL REVIEWS UNDER A PARTICULAR ATTRACTION

	@Transactional
	@Override
	public List<ReviewOutputModel> findAllReviewsByAttraction(int id) throws AttractionNotFoundException {
		List<Reviews> reviewByAttraction = reviewRepository.findAllReviewsByAttraction(id);
		List<ReviewOutputModel> rom = new ArrayList<>();
		if (reviewByAttraction.size() == 0) {
			throw new AttractionNotFoundException("No Review in this Attraction ");
		}
		for (Reviews r : reviewByAttraction) {
			UserOutputModel uom = new UserOutputModel();
			uom.setEmail(r.getUser().getEmail());
			uom.setFirstName(r.getUser().getFirstName());
			uom.setId(r.getUser().getId());
			uom.setLastName(r.getUser().getLastName());
			uom.setUserName(r.getUser().getUserName());

			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(r.getAttraction().getDistanceFromStation());
			aom.setId(r.getAttraction().getId());
			aom.setName(r.getAttraction().getName());
			aom.setRating(r.getAttraction().getRating());
			aom.setReports(r.getAttraction().getReports());
			aom.setLikes(r.getAttraction().getLikes());
			aom.setType(r.getAttraction().getType());
			aom.setUserOutputModel(uom);

			ReviewOutputModel rev = new ReviewOutputModel();
			rev.setComments(r.getComments());
			rev.setCreatedAt(r.getCreatedAt());
			rev.setId(r.getId());
			rev.setLikes(r.getLikes());
			rev.setReports(r.getReports());
			rev.setAttractionOutputModel(aom);
			rev.setUserOutputModel(uom);
			rom.add(rev);
		}
		return rom;
	}

	// FIND ALL REVIEWS DONE BY A USER

	@Transactional
	@Override
	public List<ReviewOutputModel> findAllReviewsByUser(int id) throws UserNotFoundException {
		List<Reviews> reviewByUser = reviewRepository.findAllReviewsByUser(id);
		List<ReviewOutputModel> rom = new ArrayList<>();
		if (reviewByUser.size() == 0) {
			throw new UserNotFoundException("No Review by this User");
		}
		for (Reviews r : reviewByUser) {
			UserOutputModel uom = new UserOutputModel();
			uom.setEmail(r.getUser().getEmail());
			uom.setFirstName(r.getUser().getFirstName());
			uom.setId(r.getUser().getId());
			uom.setLastName(r.getUser().getLastName());
			uom.setUserName(r.getUser().getUserName());

			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(r.getAttraction().getDistanceFromStation());
			aom.setId(r.getAttraction().getId());
			aom.setName(r.getAttraction().getName());
			aom.setRating(r.getAttraction().getRating());
			aom.setReports(r.getAttraction().getReports());
			aom.setType(r.getAttraction().getType());
			aom.setLikes(r.getAttraction().getLikes());
			aom.setUserOutputModel(uom);
			;

			ReviewOutputModel rev = new ReviewOutputModel();
			rev.setComments(r.getComments());
			rev.setCreatedAt(r.getCreatedAt());
			rev.setId(r.getId());
			rev.setLikes(r.getLikes());
			rev.setReports(r.getReports());
			rev.setAttractionOutputModel(aom);
			rev.setUserOutputModel(uom);
			;
			rom.add(rev);
		}
		return rom;
	}
}
