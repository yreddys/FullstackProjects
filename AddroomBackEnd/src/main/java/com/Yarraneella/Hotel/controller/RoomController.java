package com.Yarraneella.Hotel.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.sql.Blob;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.Yarraneella.Hotel.entity.BookedRoom;
import com.Yarraneella.Hotel.entity.Room;
import com.Yarraneella.Hotel.exception.PhotoRetrievalException;
import com.Yarraneella.Hotel.exception.ResourceNotFoundException;
import com.Yarraneella.Hotel.model.BookingResponse;
import com.Yarraneella.Hotel.model.RoomResponse;
import com.Yarraneella.Hotel.service.BookedRoomService;
import com.Yarraneella.Hotel.service.RoomService;

import lombok.extern.log4j.Log4j2;

/**
 * @author yarraneella
 */
@RequestMapping("/rooms")
@RestController
@Log4j2
@CrossOrigin(origins = "*")
public class RoomController {
	@Autowired
	private BookedRoomService bookingService;
	private RoomService roomService;

	@Autowired
	RoomController(RoomService roomService) {
		this.roomService = roomService;
	}

	@PostMapping("/add/new-room")
	public ResponseEntity<RoomResponse> addNewRoom(@RequestParam("photo") MultipartFile photo,
			@RequestParam("roomType") String roomType, @RequestParam("roomPrice") BigDecimal roomPrice)
			throws SQLException, IOException {
		log.info("Received request to add a new room with roomType: {} and roomPrice: {}", roomType, roomPrice);
		Room savedRoom = roomService.addNewRoom(photo, roomType, roomPrice);
		log.info("New room added successfully. Room ID: {}", savedRoom.getId());
		RoomResponse response = new RoomResponse(savedRoom.getId(), savedRoom.getRoomType(), savedRoom.getRoomPrice());
		log.info("Returning response for the new room");
		return ResponseEntity.ok(response);
	}

	@GetMapping("/room/types")
	public List<String> getRoomTypes() {
		return roomService.getAllRoomTypes();
	}

	@GetMapping("/all-rooms")
	public ResponseEntity<List<RoomResponse>> getAllRooms() throws SQLException {
		List<Room> rooms = roomService.getAllRooms();
		List<RoomResponse> roomResponses = new ArrayList<>();
		for (Room room : rooms) {
			byte[] photoBytes = roomService.getRoomPhotoByRoomId(room.getId());
			if (photoBytes != null && photoBytes.length > 0) {
				String base64Photo = Base64.encodeBase64String(photoBytes);
				RoomResponse roomResponse = getRoomResponse(room);
				roomResponse.setPhoto(base64Photo);
				roomResponses.add(roomResponse);
			}
		}
		return ResponseEntity.ok(roomResponses);
	}

	@DeleteMapping("/delete/room/{roomId}")

	public ResponseEntity<Void> deleteRoom(@PathVariable Long roomId) {
		roomService.deleteRoom(roomId);
		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@PutMapping("/update/{roomId}")

	public ResponseEntity<RoomResponse> updateRoom(@PathVariable Long roomId,
			@RequestParam(required = false) String roomType, @RequestParam(required = false) BigDecimal roomPrice,
			@RequestParam(required = false) MultipartFile photo) throws SQLException, IOException {
		byte[] photoBytes = photo != null && !photo.isEmpty() ? photo.getBytes()
				: roomService.getRoomPhotoByRoomId(roomId);
		Blob photoBlob = photoBytes != null && photoBytes.length > 0 ? new SerialBlob(photoBytes) : null;
		Room theRoom = roomService.updateRoom(roomId, roomType, roomPrice, photoBytes);
		theRoom.setPhoto(photoBlob);
		RoomResponse roomResponse = getRoomResponse(theRoom);
		return ResponseEntity.ok(roomResponse);
	}

	@GetMapping("/room/{roomId}")
	public ResponseEntity<Optional<RoomResponse>> getRoomById(@PathVariable Long roomId) {
		Optional<Room> theRoom = roomService.getRoomById(roomId);
		return theRoom.map(room -> {
			RoomResponse roomResponse = getRoomResponse(room);
			return ResponseEntity.ok(Optional.of(roomResponse));
		}).orElseThrow(() -> new ResourceNotFoundException("Room not found"));
	}

	@GetMapping("/available-rooms")
	public ResponseEntity<List<RoomResponse>> getAvailableRooms(
			@RequestParam("checkInDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkInDate,
			@RequestParam("checkOutDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate checkOutDate,
			@RequestParam("roomType") String roomType) throws SQLException {
		List<Room> availableRooms = roomService.getAvailableRooms(checkInDate, checkOutDate, roomType);
		List<RoomResponse> roomResponses = new ArrayList<>();
		for (Room room : availableRooms) {
			byte[] photoBytes = roomService.getRoomPhotoByRoomId(room.getId());
			if (photoBytes != null && photoBytes.length > 0) {
				String photoBase64 = Base64.encodeBase64String(photoBytes);
				RoomResponse roomResponse = getRoomResponse(room);
				roomResponse.setPhoto(photoBase64);
				roomResponses.add(roomResponse);
			}
		}
		if (roomResponses.isEmpty()) {
			return ResponseEntity.noContent().build();
		} else {
			return ResponseEntity.ok(roomResponses);
		}
	}

	private RoomResponse getRoomResponse(Room room) {
		List<BookedRoom> bookings = getAllBookingsByRoomId(room.getId());
		List<BookingResponse> bookingInfo = bookings.stream().map(booking -> new BookingResponse(booking.getBookingId(),
				booking.getCheckInDate(), booking.getCheckOutDate(), booking.getBookingConfirmationCode())).toList();
		byte[] photoBytes = null;
		Blob photoBlob = room.getPhoto();
		if (photoBlob != null) {
			try {
				photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
			} catch (SQLException e) {
				throw new PhotoRetrievalException("Error retrieving photo");
			}
		}
		return new RoomResponse(room.getId(), room.getRoomType(), room.getRoomPrice(), room.isBooked(), photoBytes,
				bookingInfo);
	}

	private List<BookedRoom> getAllBookingsByRoomId(Long roomId) {
		return bookingService.getAllBookingsByRoomId(roomId);

	}
}
