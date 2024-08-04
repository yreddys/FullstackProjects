package com.Yarraneella.Hotel.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.Yarraneella.Hotel.entity.BookedRoom;
import com.Yarraneella.Hotel.entity.Room;

@Repository
public interface BookedRoomRepository extends JpaRepository<BookedRoom, Integer> {

	List<BookedRoom> findByRoomId(Long roomId);

	List<BookedRoom> findByGuestEmail(String email);

	Room findByBookingConfirmationCode(String confirmationCode);

//	Optional<Room> findByBookingConfirmationCode(String confirmationCode);

}
