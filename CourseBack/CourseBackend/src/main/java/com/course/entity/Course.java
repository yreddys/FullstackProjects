package com.course.entity;

import java.sql.Blob;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Lob;
import jakarta.persistence.Transient;
import lombok.Data;

@Entity
@Data
public class Course {
	@Id
	@GeneratedValue
	private int id;
	private String courseName;
	private int coursePrice;
	private String courseDescription;
	@Lob
	@JsonIgnore
	private Blob photo;
	@Transient
	private String photoBase64;

	public String getPhotoBase64() {
		return photoBase64;
	}

	public void setPhotoBase64(String photoBase64) {
		this.photoBase64 = photoBase64;
	}
}
