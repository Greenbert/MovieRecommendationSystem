package com.example.moviespdemo.bean;

import java.io.Serializable;

public class Movie implements Serializable {
	private int id;
	private String name;
	private String published_year;
	private String type;

	public int getId() {
		return id;
	}

	public void setId(int id) {
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

}