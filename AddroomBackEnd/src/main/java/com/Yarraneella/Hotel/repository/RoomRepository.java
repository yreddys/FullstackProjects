package com.Yarraneella.Hotel.repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.Yarraneella.Hotel.entity.Room;

@Repository
public interface RoomRepository extends JpaRepository<Room, Integer> {
	@Query("SELECT DISTINCT r.roomType FROM Room r")
	List<String> findDistinctRoomTypes();

	Optional<Room> findById(Long roomId);

	void deleteById(Long roomId);

	@Query(" SELECT r FROM Room r " + " WHERE r.roomType LIKE %:roomType% " + " AND r.id NOT IN ("
			+ "  SELECT br.room.id FROM BookedRoom br "
			+ "  WHERE ((br.checkInDate <= :checkOutDate) AND (br.checkOutDate >= :checkInDate))" + ")")
	List<Room> findAvailableRoomsByDateAndType(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

}
