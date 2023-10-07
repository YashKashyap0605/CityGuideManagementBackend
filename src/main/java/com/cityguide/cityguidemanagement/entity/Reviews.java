package com.cityguide.cityguidemanagement.entity;

import java.sql.Timestamp;

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
@Table(name = "reviews") // optional if table and class names are different , then it is mandatory
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class Reviews {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", updatable = false, nullable = false)
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	@ManyToOne
	@JoinColumn(name = "attraction_id", referencedColumnName = "id")
	private Attractions attraction;
	@Column(name = "likes")
	private int likes;
	@Column(name = "reports")
	private int reports;
	@Column(name = "comments", nullable = false)
	private String comments;
	@Column(name = "created_at", nullable = false)
	private Timestamp createdAt;

}
