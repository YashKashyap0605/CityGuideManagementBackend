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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.cityguide.cityguidemanagement.entity.Users;
import com.cityguide.cityguidemanagement.exception.UserAlreadyExistsException;
import com.cityguide.cityguidemanagement.exception.UserNotFoundException;
import com.cityguide.cityguidemanagement.model.UserOutputModel;
import com.cityguide.cityguidemanagement.service.UserService;

@RestController
public class UserController {

	Logger logger = LoggerFactory.getLogger(AttractionsController.class);

	@Autowired
	UserService userService;

	@GetMapping("/findbyid/{id}")
	public Users getUserById(@PathVariable int id) throws UserNotFoundException {
		Users u = userService.getUserById(id);
		return u;
	}

	@PostMapping("/adduser")
	public ResponseEntity<Users> addUser(@RequestBody Users user) throws UserAlreadyExistsException {
		Users u = userService.addUser(user);
		logger.info("User with username : " + u.getUserName() + " Successfully Added");
		return new ResponseEntity<Users>(u, HttpStatus.OK);
	}

	@DeleteMapping("/deleteuser/{id}")
	public void deleteUser(@PathVariable int id) throws UserNotFoundException {
		logger.info("User Deleted Successfully");
		userService.deleteUser(id);
	}

	@GetMapping("/getuserbyemail/{emailid}")
	public ResponseEntity<UserOutputModel> getUserByEmail(@PathVariable("emailid") String emailId)
			throws com.cityguide.cityguidemanagement.exception.UserNotFoundException {
		UserOutputModel user = userService.getUserByEmail(emailId);
		logger.info("User information having the given email ID : " + user);
		return new ResponseEntity<UserOutputModel>(user, HttpStatus.OK);
	}

	@GetMapping("/getuserbyusername/{username}")
	public ResponseEntity<UserOutputModel> getUserByUserName(@PathVariable("username") String userName)
			throws UserNotFoundException {
		UserOutputModel user = userService.getUserByUserName(userName);
		logger.info("User information having the given Username ID : " + user);

		return new ResponseEntity<UserOutputModel>(user, HttpStatus.OK);
	}

	@GetMapping("/searchbykeyword/{key}")
	public ResponseEntity<List<UserOutputModel>> searchByKeyword(@PathVariable String key)
			throws UserNotFoundException {
		List<UserOutputModel> userListBySearch = userService.searchByKeyword(key);
		logger.info("Users with name Starting with given key : " + userListBySearch);

		return new ResponseEntity<List<UserOutputModel>>(userListBySearch, HttpStatus.OK);

	}

}
