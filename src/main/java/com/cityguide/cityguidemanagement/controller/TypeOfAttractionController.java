package com.cityguide.cityguidemanagement.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cityguide.cityguidemanagement.entity.TypeOfAttraction;
import com.cityguide.cityguidemanagement.service.TypeOfAttractionService;

@RestController
public class TypeOfAttractionController {

	Logger logger = LoggerFactory.getLogger(AttractionsController.class);

	@Autowired
	TypeOfAttractionService typeOfAttractionService;

	@PostMapping("/addtypeofattraction")
	public ResponseEntity<TypeOfAttraction> addTypeOfAttraction(@RequestBody TypeOfAttraction type) {
		TypeOfAttraction t = typeOfAttractionService.addTypeOfAttraction(type);
		logger.info("Type Succesfully Added");
		return new ResponseEntity<TypeOfAttraction>(t, HttpStatus.OK);
	}

	@DeleteMapping("/deletetypeofattraction/{id}")
	public void deleteTypeOfAttraction(@PathVariable int id) {
		logger.info("Type Succesfully Deleted");
		typeOfAttractionService.deleteTypeOfAttraction(id);
	}

}
