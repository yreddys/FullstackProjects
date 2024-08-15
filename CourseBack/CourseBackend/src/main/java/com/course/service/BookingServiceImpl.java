package com.course.service;

import java.security.SecureRandom;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.course.entity.Booking;
import com.course.repository.BookingRepository;

@Service
public class BookingServiceImpl implements BookingService {
	@Autowired
	private BookingRepository bookingRepository;
	String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
	int LENGTH = 8; // Length of the confirmation ID

	@Override
	public Booking bookingCourse(Booking bookingCourse) {

		// Generate a unique confirmation ID
		String confirmationId = generateConfirmationId();
		bookingCourse.setConfirmationId(confirmationId);

		// Save the booking
		return bookingRepository.save(bookingCourse);
	}

	public String generateConfirmationId() {
		SecureRandom random = new SecureRandom();
		StringBuilder sb = new StringBuilder(LENGTH);
		for (int i = 0; i < LENGTH; i++) {
			int index = random.nextInt(CHARACTERS.length());
			sb.append(CHARACTERS.charAt(index));
		}
		return sb.toString();
	}
}
