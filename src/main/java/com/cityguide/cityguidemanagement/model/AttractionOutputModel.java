package com.cityguide.cityguidemanagement.model;

import com.cityguide.cityguidemanagement.entity.TypeOfAttraction;

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
public class AttractionOutputModel {

	private int id;
	private String name;
	private float distanceFromStation;
	private TypeOfAttraction type;
	private float rating;
	private int reports;
	private int likes;
	private UserOutputModel userOutputModel;

}
