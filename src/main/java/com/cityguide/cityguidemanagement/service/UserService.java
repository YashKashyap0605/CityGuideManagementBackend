package com.cityguide.cityguidemanagement.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.cityguide.cityguidemanagement.entity.Users;
import com.cityguide.cityguidemanagement.exception.UserAlreadyExistsException;
import com.cityguide.cityguidemanagement.exception.UserNotFoundException;
import com.cityguide.cityguidemanagement.model.UserOutputModel;

@Service
public interface UserService {
	public Users getUserById(int id) throws UserNotFoundException;

	public List<String> getAllNamesOfUsers();

	public List<String> getAllEmailOfUsers();

	public Users addUser(Users user) throws UserAlreadyExistsException;

	public void deleteUser(int id) throws UserNotFoundException;

	public UserOutputModel getUserByEmail(String emailId) throws UserNotFoundException;

	public UserOutputModel getUserByUserName(String userName) throws UserNotFoundException;

	public List<UserOutputModel> searchByKeyword(String key) throws UserNotFoundException;
}
