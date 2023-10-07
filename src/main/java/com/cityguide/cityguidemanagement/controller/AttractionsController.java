package com.cityguide.cityguidemanagement.controller;

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
//import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cityguide.cityguidemanagement.entity.Attractions;
import com.cityguide.cityguidemanagement.entity.TypeOfAttraction;
import com.cityguide.cityguidemanagement.entity.Users;
import com.cityguide.cityguidemanagement.exception.AttractionAlreadyExistsException;
import com.cityguide.cityguidemanagement.exception.AttractionNotFoundException;
import com.cityguide.cityguidemanagement.exception.UserNotFoundException;
import com.cityguide.cityguidemanagement.model.AttractionInputModel;
import com.cityguide.cityguidemanagement.model.AttractionOutputModel;
import com.cityguide.cityguidemanagement.service.AttractionsService;
import com.cityguide.cityguidemanagement.service.TypeOfAttractionService;
import com.cityguide.cityguidemanagement.service.UserService;

//import jakarta.transaction.Transactional;

@RestController
public class AttractionsController {

	Logger logger = LoggerFactory.getLogger(AttractionsController.class);

	@Autowired
	AttractionsService attractionService;

	@Autowired
	UserService us;

	@Autowired
	TypeOfAttractionService toas;

	@GetMapping("/searchbykeywordAttraction/{key}")
	public ResponseEntity<List<AttractionOutputModel>> searchByKeyword(@PathVariable("key") String key)
			throws AttractionNotFoundException {
		List<AttractionOutputModel> att = attractionService.searchByKeyword(key);
		logger.info("Attractions starting with " + key + " are : " + att);
		return new ResponseEntity<List<AttractionOutputModel>>(att, HttpStatus.OK);
	}

	@PostMapping("/addattraction")
	public ResponseEntity<Attractions> addAttraction(@RequestBody AttractionInputModel att)
			throws AttractionAlreadyExistsException, UserNotFoundException {
		Attractions a = new Attractions();
		a.setDistanceFromStation(att.getDistanceFromStation());
		a.setId(att.getId());
		a.setLikes(0);
		a.setName(att.getName());
		a.setRating(att.getRating());
		a.setReports(0);
		int tid = att.getTid();
		TypeOfAttraction t = toas.getTypeById(tid);
		a.setType(t);
		int id = att.getUid();
		Users u = us.getUserById(id);
		a.setUser(u);
		a = attractionService.addAttraction(a);
		logger.info("Attraction with name : " + a.getName() + " Added");
		// logger.error("Already Exists")
		return new ResponseEntity<Attractions>(a, HttpStatus.OK);
	}

	@DeleteMapping("/deleteattractions/{id}")
	public void deleteAttraction(int id) {
		logger.info("Attraction Successfully Deleted");
		attractionService.deleteAttraction(id);
	}

	@GetMapping("/getallattractions")
	public ResponseEntity<List<AttractionOutputModel>> getAllAttractions() throws AttractionNotFoundException {
		List<AttractionOutputModel> attractionsList = attractionService.getAllAttractions();
		logger.info("All Attractions info : " + attractionsList);
		return new ResponseEntity<List<AttractionOutputModel>>(attractionsList, HttpStatus.OK);
	}

	@GetMapping("/getallattractionsbyratings/{ratings}")
	public ResponseEntity<List<AttractionOutputModel>> getAllAttractionsByRatings(@PathVariable float ratings)
			throws AttractionNotFoundException {
		List<AttractionOutputModel> attractionsListByRatings = attractionService.getAllAttractionsByRatings(ratings);
		logger.info("All Attractions by given Ratings : " + attractionsListByRatings);
		return new ResponseEntity<List<AttractionOutputModel>>(attractionsListByRatings, HttpStatus.OK);
	}

	@GetMapping("/getallattractionsbylikes/{likes}")
	public ResponseEntity<List<AttractionOutputModel>> getAllAttractionsByLikes(@PathVariable int likes)
			throws AttractionNotFoundException {
		List<AttractionOutputModel> attractionsListByLikes = attractionService.getAllAttractionsByLikes(likes);
		logger.info("All Attractions by given Likes : " + attractionsListByLikes);
		return new ResponseEntity<List<AttractionOutputModel>>(attractionsListByLikes, HttpStatus.OK);
	}

	@GetMapping("/getattractionbyname/{name}")
	public ResponseEntity<AttractionOutputModel> getAttractionByName(@PathVariable String name)
			throws AttractionNotFoundException {
		AttractionOutputModel a = attractionService.getAttractionByName(name);
		logger.info("Attraction with given name" + a);
		return new ResponseEntity<AttractionOutputModel>(a, HttpStatus.OK);
	}

	@GetMapping("getattractionbyreports/{reports}")
	public ResponseEntity<List<AttractionOutputModel>> getAttractionByReports(@PathVariable int reports)
			throws AttractionNotFoundException {
		List<AttractionOutputModel> attractionsListByReports = attractionService.getAttractionByReports(reports);
		logger.info("Attraction with given reports : " + attractionsListByReports);
		return new ResponseEntity<List<AttractionOutputModel>>(attractionsListByReports, HttpStatus.OK);
	}

	@GetMapping("/findclosestattractionstostations")
	public ResponseEntity<List<AttractionOutputModel>> findClosestAttractionsToStations()
			throws AttractionNotFoundException {
		List<AttractionOutputModel> attractionListByDistance = attractionService.findClosestAttractionsToStations();
		logger.info("Attraction with given reports : " + attractionListByDistance);
		return new ResponseEntity<List<AttractionOutputModel>>(attractionListByDistance, HttpStatus.OK);
	}

	@GetMapping("/findallattractionsbyuser/{id}")
	public ResponseEntity<List<AttractionOutputModel>> findAllAttractionsByUser(@PathVariable int id)
			throws AttractionNotFoundException {
		List<AttractionOutputModel> attractionListByUser = attractionService.findAllAttractionsByUser(id);
		logger.info("Attraction added by Given User are  : " + attractionListByUser);
		return new ResponseEntity<List<AttractionOutputModel>>(attractionListByUser, HttpStatus.OK);
	}

	@GetMapping("/getattractionsoftype/{id}")
	public ResponseEntity<List<AttractionOutputModel>> getAttractionsOfType(@PathVariable int id)
			throws AttractionNotFoundException {
		List<AttractionOutputModel> attractionListByType = attractionService.getAttractionsOfType(id);
		logger.info("Attractions of given type are : " + attractionListByType);
		return new ResponseEntity<List<AttractionOutputModel>>(attractionListByType, HttpStatus.OK);

	}

	@PutMapping("/likeattraction/{id}")
	public void likeAttraction(int id) {
		logger.info("Attraction has been LIKED successfully!!!!");
		attractionService.likeAttraction(id);
	}

	@PutMapping("/reportattraction/{id}")
	public void reportAttraction(@PathVariable int id) {
		logger.info("Attraction has been REPORTED successfully!!!!!");
		attractionService.reportAttraction(id);
	}

}
