package com.course.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.course.entity.Booking;
import com.course.service.BookingService;

@RestController
@RequestMapping("/booking")
@CrossOrigin("*")
public class BookingController {
	@Autowired
	private BookingService bookingService;

	@PostMapping("/book")
	ResponseEntity<Booking> bookingCourseService(@RequestBody Booking bookingCourse) {
		Booking bookedCourse = bookingService.bookingCourse(bookingCourse);
		return new ResponseEntity<>(bookedCourse, HttpStatus.CREATED);
	}
}
