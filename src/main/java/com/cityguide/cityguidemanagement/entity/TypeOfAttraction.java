package com.cityguide.cityguidemanagement.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "typeofattraction") // optional if table and class names are different , then it is mandatory
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class TypeOfAttraction {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;

}
