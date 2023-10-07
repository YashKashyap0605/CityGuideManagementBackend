package com.cityguide.cityguidemanagement.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cityguide.cityguidemanagement.dao.UserRepository;
import com.cityguide.cityguidemanagement.entity.Users;
import com.cityguide.cityguidemanagement.exception.UserAlreadyExistsException;
import com.cityguide.cityguidemanagement.exception.UserNotFoundException;
import com.cityguide.cityguidemanagement.model.UserOutputModel;
import com.cityguide.cityguidemanagement.service.UserService;

import jakarta.transaction.Transactional;

@Service
public class UserServiceImpl implements UserService {
	@Autowired
	UserRepository userRepository;

	// GET USER BY ID
	@Override
	public Users getUserById(int id) throws UserNotFoundException {

		Users u = userRepository.findById(id).orElse(null);
		if (u == null)
			throw new UserNotFoundException("User with given id does not exists");
		return u;

	}

	// LIST CONTAINING ALL USERNAMES OF USERS IN THE DATABASE
	List<String> usernamesOfUsers = new ArrayList<>();

	// LIST CONTAINING ALL EMAILS OF A USER
	List<String> emailOfUsers = new ArrayList<>();

	// LIST OF ALL USERNAMES OF USERS

	@Transactional
	@Override
	public List<String> getAllNamesOfUsers() {
		usernamesOfUsers = userRepository.getAllNamesOfUsers();
		return usernamesOfUsers;
	}

	// LIST OF ALL EMAILS OF USERS

	@Transactional
	@Override
	public List<String> getAllEmailOfUsers() {
		emailOfUsers = userRepository.getAllEmailOfUsers();
		return emailOfUsers;
	}

	// ADD A USER TO THE DATABASE

	@Transactional
	@Override
	public Users addUser(Users user) throws UserAlreadyExistsException {
		List<String> usernames = userRepository.getAllNamesOfUsers();
		List<String> emails = userRepository.getAllEmailOfUsers();
		if (usernames.contains(user.getUserName())) {
			throw new UserAlreadyExistsException("Username is already taken");
		} else {
			if (emails.contains(user.getEmail())) {
				throw new UserAlreadyExistsException("Email is already registered");
			} else {
				return userRepository.save(user);
			}
		}
	}

	// DELETE A USER FROM DATABASE

	@Transactional
	@Override
	public void deleteUser(int id) throws UserNotFoundException {
		userRepository.deleteById(id);
	}

	// GET USER BY EMAIL ID

	@Transactional
	@Override
	public UserOutputModel getUserByEmail(String emailId)
			throws com.cityguide.cityguidemanagement.exception.UserNotFoundException {
		Users user = userRepository.getUserByEmail(emailId);
		if (user == null)
			throw new com.cityguide.cityguidemanagement.exception.UserNotFoundException("User not found.....");
		UserOutputModel u = new UserOutputModel();
		u.setEmail(user.getEmail());
		u.setId(user.getId());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setUserName(user.getUserName());

		return u;
	}

	// GET USER BY USERNAME

	@Transactional
	@Override
	public UserOutputModel getUserByUserName(String userName) throws UserNotFoundException {
		Users user = userRepository.getUserByUserName(userName);
		if (user == null)
			throw new com.cityguide.cityguidemanagement.exception.UserNotFoundException("User not found.....");
		UserOutputModel u = new UserOutputModel();
		u.setEmail(user.getEmail());
		u.setId(user.getId());
		u.setFirstName(user.getFirstName());
		u.setLastName(user.getLastName());
		u.setUserName(user.getUserName());

		return u;
	}

	// SEARCH A USER BY STARTING ALPHABETS

	@Transactional
	@Override
	public List<UserOutputModel> searchByKeyword(String key) throws UserNotFoundException {
		List<Users> userListBySearch = userRepository.searchByKeyword(key);
		if (userListBySearch.size() == 0) {
			throw new UserNotFoundException("No such User Found");
		}
		List<UserOutputModel> u = new ArrayList<UserOutputModel>();
		for (Users us : userListBySearch) {
			UserOutputModel uom = new UserOutputModel();
			uom.setEmail(us.getEmail());
			uom.setFirstName(us.getFirstName());
			uom.setId(us.getId());
			uom.setLastName(us.getLastName());
			uom.setUserName(us.getUserName());
			u.add(uom);
		}
		return u;
	}
}
