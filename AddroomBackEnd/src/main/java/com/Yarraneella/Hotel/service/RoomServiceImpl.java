package com.Yarraneella.Hotel.service;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.Yarraneella.Hotel.entity.Room;
import com.Yarraneella.Hotel.exception.InternalServerException;
import com.Yarraneella.Hotel.repository.RoomRepository;

import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
public class RoomServiceImpl implements RoomService {
	@Autowired
	private RoomRepository roomRepository;

	@Override
	public Room addNewRoom(MultipartFile file, String roomType, BigDecimal roomPrice)
			throws SerialException, SQLException, IOException {
		log.info("Received request to add a new room with roomType: {} and roomPrice: {}", roomType, roomPrice);
		Room room = new Room();
		room.setRoomType(roomType);
		room.setRoomPrice(roomPrice);
		if (!file.isEmpty()) {
			byte[] photoBytes = file.getBytes();
			Blob photoBlob = new SerialBlob(photoBytes);
			room.setPhoto(photoBlob);
			log.info("Room photo set successfully");
		}
		return roomRepository.save(room);
	}

	@Override
	public List<String> getAllRoomTypes() {
		return roomRepository.findDistinctRoomTypes();
	}

	@Override
	public List<Room> getAllRooms() {

		return roomRepository.findAll();
	}

	@Override
	public byte[] getRoomPhotoByRoomId(Long roomId) throws SQLException {
		Optional<Room> theRoom = roomRepository.findById(roomId);
		if (theRoom.isEmpty()) {
			throw new com.Yarraneella.Hotel.exception.ResourceNotFoundException("Sorry, Room not found!");
		}
		Blob photoBlob = theRoom.get().getPhoto();
		if (photoBlob != null) {
			return photoBlob.getBytes(1, (int) photoBlob.length());
		}
		return null;
	}

	@Override
	public void deleteRoom(Long roomId) {
		Optional<Room> theRoom = roomRepository.findById(roomId);
		if (theRoom.isPresent()) {
			roomRepository.deleteById(roomId);
		}

	}

	@Override
	public Room updateRoom(Long roomId, String roomType, BigDecimal roomPrice, byte[] photoBytes) {
		Room room = roomRepository.findById(roomId).get();
		if (roomType != null)
			room.setRoomType(roomType);
		if (roomPrice != null)
			room.setRoomPrice(roomPrice);
		if (photoBytes != null && photoBytes.length > 0) {
			try {
				room.setPhoto(new SerialBlob(photoBytes));
			} catch (SQLException ex) {
				throw new InternalServerException("Fail updating room");
			}
		}
		return roomRepository.save(room);
	}

	@Override
	public Optional<Room> getRoomById(Long roomId) {
		return Optional.of(roomRepository.findById(roomId).get());
	}

	@Override
	public List<Room> getAvailableRooms(LocalDate checkInDate, LocalDate checkOutDate, String roomType) {
		return roomRepository.findAvailableRoomsByDateAndType(checkInDate, checkOutDate, roomType);
	}

}
