package com.cityguide.cityguidemanagement.entity;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "attractions") // optional if table and class names are different , then it is mandatory
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Attractions {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;
	@Column(name = "name", nullable = false)
	private String name;
	@Column(name = "distance_from_station", nullable = false)
	private float distanceFromStation;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "type_id", referencedColumnName = "id")
	private TypeOfAttraction type;
	@Column(name = "rating")
	private float rating;
	@Column(name = "likes")
	private int likes;
	@Column(name = "reports")
	private int reports;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "by_user_id", referencedColumnName = "id")
	private Users user;

}
