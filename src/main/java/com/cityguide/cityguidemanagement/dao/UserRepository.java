package com.cityguide.cityguidemanagement.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cityguide.cityguidemanagement.entity.Users;

public interface UserRepository extends JpaRepository<Users, Integer> {

	@Query(value = "select u from Users u where u.email=?1")
	public Users getUserByEmail(String email);

	@Query(value = "select u from Users u where u.userName=?1")
	public Users getUserByUserName(String userName);

	@Query(value = "select u.email from Users u")
	public List<String> getAllEmailOfUsers();

	@Query(value = "select u.userName from Users u")
	public List<String> getAllNamesOfUsers();

	@Query("SELECT u FROM Users u WHERE u.userName LIKE ?1%")
	public List<Users> searchByKeyword(String key);

}
