package com.cityguide.cityguidemanagement.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class UserOutputModel {

	private int id;
	private String userName;
	private String firstName;
	private String lastName;
	private String email;
}
