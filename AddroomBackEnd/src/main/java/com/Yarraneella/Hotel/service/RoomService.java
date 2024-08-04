package com.Yarraneella.Hotel.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialException;

import org.springframework.web.multipart.MultipartFile;

import com.Yarraneella.Hotel.entity.Room;

public interface RoomService {

	Room addNewRoom(MultipartFile photo, String roomType, BigDecimal roomPrice) throws SerialException, SQLException, IOException;

	List<String> getAllRoomTypes();

	List<Room> getAllRooms();

	byte[] getRoomPhotoByRoomId(Long id) throws SQLException;

	void deleteRoom(Long roomId);

	Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes);

	Optional<Room> getRoomById(Long roomId);

	List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType);

}
