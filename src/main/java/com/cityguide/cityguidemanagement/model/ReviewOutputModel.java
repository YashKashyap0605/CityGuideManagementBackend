package com.cityguide.cityguidemanagement.model;

import java.sql.Timestamp;

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
public class ReviewOutputModel {

	private int id;
	private UserOutputModel userOutputModel;
	private AttractionOutputModel attractionOutputModel;
	private int likes;
	private int reports;
	private String comments;
	private Timestamp createdAt;

}
