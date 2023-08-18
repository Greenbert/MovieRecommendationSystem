/**
 * movie的javabean
 */
package com.example.moviespdemo.bean;

public class MovieInfo {
	private long id;
	private String name;
	private String published_year;
	private String type;
	private double preference;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPublished_year() {
		return published_year;
	}

	public void setPublished_year(String published_year) {
		this.published_year = published_year;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public double getPreference() {
		return preference;
	}

	public void setPreference(double preference) {
		this.preference = preference;
	}
}
