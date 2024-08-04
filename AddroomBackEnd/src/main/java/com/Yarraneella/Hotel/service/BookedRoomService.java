package com.Yarraneella.Hotel.service;

import java.util.List;

import com.Yarraneella.Hotel.entity.BookedRoom;

public interface BookedRoomService {

	List<BookedRoom> getAllBookingsByRoomId(Long roomId);

	List<BookedRoom> getAllBookings();

	String saveBooking(Long roomId, BookedRoom bookingRequest);

	void cancelBooking(Long bookingId);

	List<BookedRoom> getBookingsByUserEmail(String email);

	BookedRoom findByBookingConfirmationCode(String confirmationCode);

	

}
