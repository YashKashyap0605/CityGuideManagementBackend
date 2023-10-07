package com.cityguide.cityguidemanagement.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.sql.Timestamp;
import java.time.LocalDate;

import jakarta.persistence.*;

@Entity
@Table(name = "users") // optional if table and class names are different , then it is mandatory
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Users {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	@Column(name = "username", nullable = false)
	private String userName;
	@Column(name = "first_name", nullable = false)
	private String firstName;
	@Column(name = "last_name", nullable = false)
	private String lastName;
	@Column(name = "email", nullable = false)
	private String email;
	@Column(name = "password", nullable = false)
	private String password;
	@Column(name = "is_admin", nullable = false, columnDefinition = "boolean default false")
	private boolean isAdmin;
	@Column(name = "dob")
	private LocalDate dob;
	@Column(name = "created_at")
	private Timestamp createdAt;

}
