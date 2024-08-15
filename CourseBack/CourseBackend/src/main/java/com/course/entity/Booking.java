package com.course.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Booking {
	@Id
	@GeneratedValue
	private int BookingId;
	private String name;
	private String email;
	private long mobile;
	@ManyToOne
	@JoinColumn(name = "Id")
	private Course course;
	private String confirmationId;
}
