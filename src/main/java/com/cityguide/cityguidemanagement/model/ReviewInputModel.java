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
public class ReviewInputModel {
	private int id;
	private int uid;
	private int aid;
	private String comments;
}
