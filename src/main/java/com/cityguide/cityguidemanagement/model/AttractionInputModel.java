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
public class AttractionInputModel {
	private int id;
	private String name;
	private float distanceFromStation;
	private int tid;
	private float rating;
	private int uid;
}
