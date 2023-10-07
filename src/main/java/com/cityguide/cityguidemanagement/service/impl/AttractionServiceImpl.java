package com.cityguide.cityguidemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityguide.cityguidemanagement.controller.AttractionsController;
import com.cityguide.cityguidemanagement.dao.AttractionRepository;
import com.cityguide.cityguidemanagement.entity.Attractions;
import com.cityguide.cityguidemanagement.exception.AttractionAlreadyExistsException;
import com.cityguide.cityguidemanagement.exception.AttractionNotFoundException;
import com.cityguide.cityguidemanagement.model.AttractionOutputModel;
import com.cityguide.cityguidemanagement.model.UserOutputModel;
import com.cityguide.cityguidemanagement.service.AttractionsService;

import jakarta.transaction.Transactional;

@Service
public class AttractionServiceImpl implements AttractionsService {
	@Autowired
	AttractionRepository attractionRepository;

	Logger logger = LoggerFactory.getLogger(AttractionsController.class);

	// CONTAINS NAMES OF ALL ATTRACTIONS
	// FOR UNIQUENESS
	List<String> namesOfAttractions = new ArrayList<>();

	//// FIND A PARTICULAR ATTRACTION BY ID

	@Transactional
	@Override
	public Attractions getAttractionById(int id) {
		Attractions a = attractionRepository.findById(id).orElse(null);

		return a;
	}

	// GET ALL NAMES OF ATTRACTIONS
	// JUST TO CONTAIN UNIQUENESS IN DATABASE

	@Transactional
	@Override
	public List<String> getAllNamesOfAttractions() {
		namesOfAttractions = attractionRepository.getAllNamesOfAttractions();
		return namesOfAttractions;
	}

	// ADD A NEW ATTRACTION

	@Transactional
	@Override
	public Attractions addAttraction(Attractions att) throws AttractionAlreadyExistsException {
		List<String> names = attractionRepository.getAllNamesOfAttractions();
		if (names.contains(att.getName())) {
			throw new AttractionAlreadyExistsException("Already Present in Database");
		} else {
			logger.info("Attraction with name : " + att.getName() + " Successfully Added.");
			return attractionRepository.save(att);
		}
	}

	// DELETE A PARTICULAR ATTRACTION

	@Transactional
	@Override
	public void deleteAttraction(int id) {
		logger.info("Attraction Deleted");
		attractionRepository.deleteById(id);
	}

	// GET ALL ATTACTIONS PRESENT IN THE DATABASE

	@Transactional
	@Override
	public List<AttractionOutputModel> getAllAttractions() throws AttractionNotFoundException {
		List<Attractions> attractionsList = attractionRepository.findAll();
		List<AttractionOutputModel> a = new ArrayList<>();
		if (attractionsList.size() == 0) {
			throw new AttractionNotFoundException("No Attractions in the database");
		}
		for (Attractions at : attractionsList) {
			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(at.getDistanceFromStation());
			aom.setId(at.getId());
			aom.setName(at.getName());
			aom.setRating(at.getRating());
			aom.setLikes(at.getLikes());
			aom.setReports(at.getReports());
			aom.setType(at.getType());
			UserOutputModel u = new UserOutputModel();
			u.setEmail(at.getUser().getEmail());
			u.setFirstName(at.getUser().getFirstName());
			u.setId(at.getUser().getId());
			u.setLastName(at.getUser().getLastName());
			u.setUserName(at.getUser().getUserName());
			aom.setUserOutputModel(u);
			a.add(aom);
		}
		logger.info("All attractions : " + a);
		return a;
	}

	// GET ATTRACTIONS BY RATING

	@Transactional
	@Override
	public List<AttractionOutputModel> getAllAttractionsByRatings(float ratings) throws AttractionNotFoundException {
		List<Attractions> attractionsListByRatings = attractionRepository.getAllAttractionsByRatings(ratings);
		List<AttractionOutputModel> a = new ArrayList<>();
		if (attractionsListByRatings.size() == 0) {
			throw new AttractionNotFoundException("No Attraction has this much Ratings");
		}
		for (Attractions at : attractionsListByRatings) {
			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(at.getDistanceFromStation());
			aom.setId(at.getId());
			aom.setName(at.getName());
			aom.setRating(at.getRating());
			aom.setLikes(at.getLikes());
			aom.setReports(at.getReports());
			aom.setType(at.getType());
			UserOutputModel u = new UserOutputModel();
			u.setEmail(at.getUser().getEmail());
			u.setFirstName(at.getUser().getFirstName());
			u.setId(at.getUser().getId());
			u.setLastName(at.getUser().getLastName());
			u.setUserName(at.getUser().getUserName());
			aom.setUserOutputModel(u);
			a.add(aom);
		}
		logger.info("All attractions as per given Ratings : " + a);
		return a;
	}

	// GET ATTRACTIONS HAVING LIKES GREATER THAN A NUMBER

	@Transactional
	@Override
	public List<AttractionOutputModel> getAllAttractionsByLikes(int likes) throws AttractionNotFoundException {
		List<Attractions> attractionsListByLikes = attractionRepository.getAllAttractionsByLikes(likes);
		List<AttractionOutputModel> a = new ArrayList<>();
		if (attractionsListByLikes.size() == 0) {
			throw new AttractionNotFoundException("No Attraction has this much likes");
		}
		for (Attractions at : attractionsListByLikes) {
			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(at.getDistanceFromStation());
			aom.setId(at.getId());
			aom.setName(at.getName());
			aom.setRating(at.getRating());
			aom.setReports(at.getReports());
			aom.setLikes(at.getLikes());
			aom.setType(at.getType());
			UserOutputModel u = new UserOutputModel();
			u.setEmail(at.getUser().getEmail());
			u.setFirstName(at.getUser().getFirstName());
			u.setId(at.getUser().getId());
			u.setLastName(at.getUser().getLastName());
			u.setUserName(at.getUser().getUserName());
			aom.setUserOutputModel(u);
			a.add(aom);
		}
		logger.info("All attractions as per given Likes : " + a);
		return a;

	}

	// SEARCH ATTRACTION BY ITS NAME

	@Transactional
	@Override
	public AttractionOutputModel getAttractionByName(String name) throws AttractionNotFoundException {
		Attractions at = attractionRepository.getAttractionByName(name);
		if (at == null) {
			throw new AttractionNotFoundException("No Attraction with this name exists");
		}
		AttractionOutputModel aom = new AttractionOutputModel();
		aom.setDistanceFromStation(at.getDistanceFromStation());
		aom.setId(at.getId());
		aom.setName(at.getName());
		aom.setRating(at.getRating());
		aom.setReports(at.getReports());
		aom.setLikes(at.getLikes());
		aom.setType(at.getType());
		UserOutputModel u = new UserOutputModel();
		u.setEmail(at.getUser().getEmail());
		u.setFirstName(at.getUser().getFirstName());
		u.setId(at.getUser().getId());
		u.setLastName(at.getUser().getLastName());
		u.setUserName(at.getUser().getUserName());
		aom.setUserOutputModel(u);
		logger.info("All attractions by Name : " + aom);
		return aom;

	}

	// GET ATTRACTION HAVING REPORTS GREATER THAN A NUMBER

	@Transactional
	@Override
	public List<AttractionOutputModel> getAttractionByReports(int reports) throws AttractionNotFoundException {
		List<Attractions> attractionsListByReports = attractionRepository.getAttractionByReports(reports);
		List<AttractionOutputModel> a = new ArrayList<>();
		if (attractionsListByReports.size() == 0) {
			throw new AttractionNotFoundException("No Attraction has this much Reports");
		}
		for (Attractions at : attractionsListByReports) {
			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(at.getDistanceFromStation());
			aom.setId(at.getId());
			aom.setName(at.getName());
			aom.setLikes(at.getLikes());
			aom.setRating(at.getRating());
			aom.setReports(at.getReports());
			aom.setType(at.getType());
			UserOutputModel u = new UserOutputModel();
			u.setEmail(at.getUser().getEmail());
			u.setFirstName(at.getUser().getFirstName());
			u.setId(at.getUser().getId());
			u.setLastName(at.getUser().getLastName());
			u.setUserName(at.getUser().getUserName());
			aom.setUserOutputModel(u);
			a.add(aom);
		}

		return a;
	}

	// SEARCH ATTRACTION ON BASIS OF STARTING LETTERS

	@Transactional
	@Override
	public List<AttractionOutputModel> searchByKeyword(String key) throws AttractionNotFoundException {
		List<Attractions> attractionListBySearch = attractionRepository.searchByKeyword(key);
		List<AttractionOutputModel> a = new ArrayList<>();
		if (attractionListBySearch.size() == 0) {
			throw new AttractionNotFoundException("No Attraction like this exists");
		}
		for (Attractions at : attractionListBySearch) {
			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(at.getDistanceFromStation());
			aom.setId(at.getId());
			aom.setName(at.getName());
			aom.setRating(at.getRating());
			aom.setLikes(at.getLikes());
			aom.setReports(at.getReports());
			aom.setType(at.getType());
			UserOutputModel u = new UserOutputModel();
			u.setEmail(at.getUser().getEmail());
			u.setFirstName(at.getUser().getFirstName());
			u.setId(at.getUser().getId());
			u.setLastName(at.getUser().getLastName());
			u.setUserName(at.getUser().getUserName());
			aom.setUserOutputModel(u);
			a.add(aom);
		}
		return a;

	}

	// FIND CLOSEST ATTRACTIONS TO STATION

	@Transactional
	@Override
	public List<AttractionOutputModel> findClosestAttractionsToStations() throws AttractionNotFoundException {
		List<Attractions> attractionListByDistance = attractionRepository.findClosestAttractionsToStations();
		List<AttractionOutputModel> a = new ArrayList<>();
		if (attractionListByDistance.size() == 0) {
			throw new AttractionNotFoundException("No Attraction in Database");
		}
		for (Attractions at : attractionListByDistance) {
			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(at.getDistanceFromStation());
			aom.setId(at.getId());
			aom.setName(at.getName());
			aom.setRating(at.getRating());
			aom.setLikes(at.getLikes());
			aom.setReports(at.getReports());
			aom.setType(at.getType());
			UserOutputModel u = new UserOutputModel();
			u.setEmail(at.getUser().getEmail());
			u.setFirstName(at.getUser().getFirstName());
			u.setId(at.getUser().getId());
			u.setLastName(at.getUser().getLastName());
			u.setUserName(at.getUser().getUserName());
			aom.setUserOutputModel(u);
			a.add(aom);
		}
		return a;
	}

	// GET ALL ATTRACTIONS ADDED BY A PARTICULAR USER

	@Transactional
	@Override
	public List<AttractionOutputModel> findAllAttractionsByUser(int id) throws AttractionNotFoundException {
		List<Attractions> attractionListByUser = attractionRepository.findAllAttractionsByUser(id);
		List<AttractionOutputModel> a = new ArrayList<>();
		if (attractionListByUser.size() == 0) {
			throw new AttractionNotFoundException("No Attraction in by this user");
		}
		for (Attractions at : attractionListByUser) {
			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(at.getDistanceFromStation());
			aom.setId(at.getId());
			aom.setName(at.getName());
			aom.setRating(at.getRating());
			aom.setReports(at.getReports());
			aom.setLikes(at.getLikes());
			aom.setType(at.getType());
			UserOutputModel u = new UserOutputModel();
			u.setEmail(at.getUser().getEmail());
			u.setFirstName(at.getUser().getFirstName());
			u.setId(at.getUser().getId());
			u.setLastName(at.getUser().getLastName());
			u.setUserName(at.getUser().getUserName());
			aom.setUserOutputModel(u);
			a.add(aom);
		}
		return a;
	}

	// GET ALL ATTRACTIONS OF A PARTICULAR TYPE

	@Transactional
	@Override
	public List<AttractionOutputModel> getAttractionsOfType(int id) throws AttractionNotFoundException {
		List<Attractions> attractionListByType = attractionRepository.getAttractionsOfType(id);
		List<AttractionOutputModel> a = new ArrayList<>();

		if (attractionListByType.size() == 0) {
			throw new AttractionNotFoundException("No Attraction of this Type");
		}
		for (Attractions at : attractionListByType) {
			AttractionOutputModel aom = new AttractionOutputModel();
			aom.setDistanceFromStation(at.getDistanceFromStation());
			aom.setId(at.getId());
			aom.setName(at.getName());
			aom.setRating(at.getRating());
			aom.setReports(at.getReports());
			aom.setLikes(at.getLikes());
			aom.setType(at.getType());
			UserOutputModel u = new UserOutputModel();
			u.setEmail(at.getUser().getEmail());
			u.setFirstName(at.getUser().getFirstName());
			u.setId(at.getUser().getId());
			u.setLastName(at.getUser().getLastName());
			u.setUserName(at.getUser().getUserName());
			aom.setUserOutputModel(u);
			a.add(aom);
		}
		return a;
	}

	// LIKE ANY ATTRACTION

	@Transactional
	@Override
	public void likeAttraction(int id) {
		attractionRepository.likeAttraction(id);
	}

	// REPORT A ATTRACTION

	@Transactional
	@Override
	public void reportAttraction(int id) {
		attractionRepository.reportAttraction(id);
	}
}
