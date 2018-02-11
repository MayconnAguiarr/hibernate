package com.produto.hibernate.model;

public class Reminder {

	private long id;
	private String title;
	private String description;

	public Reminder() {	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	@Override
	public String toString() {
		return "Reminder [id=" + id + ", title=" + title + ", description=" + description + "]";
	}
}
