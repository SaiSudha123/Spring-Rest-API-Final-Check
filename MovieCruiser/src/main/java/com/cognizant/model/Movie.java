package com.cognizant.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "movie")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Movie {
	@Id
	@Column(name = "mo_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "mo_title")
	private String title;
	@Column(name = "mo_box_office")
	private String boxOffice;
	@Column(name = "mo_active")
	private String active;
	@Column(name = "mo_date_of_launch")
	private Date dateOfLaunch;
	@Column(name = "mo_genre")
	private String genre;
	@Column(name = "mo_has_teaser")
	private String hasTeaser;
	
}
