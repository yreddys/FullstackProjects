package com.course.controller;

import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.course.entity.Course;
import com.course.service.CourseService;

@RestController
@RequestMapping("/Course")
@CrossOrigin("*")
public class CourseController {

	@Autowired
	private CourseService courseService;

	@PostMapping("/addCourse")
	public ResponseEntity<Course> addCourse(@RequestParam("photo") MultipartFile photo,
			@RequestParam("courseName") String courseName, @RequestParam("coursePrice") int coursePrice,
			@RequestParam("courseDescription") String courseDescription) {
		Course addCourseResponse = courseService.addCourse(photo, courseName, coursePrice, courseDescription);
		return new ResponseEntity<>(addCourseResponse, HttpStatus.CREATED);
	}

	@GetMapping("/allCourse")
	public ResponseEntity<List<Course>> allCourse() {
		List<Course> allCourseResponse = courseService.allCourse();
		List<Course> updatedCourses = new ArrayList<>();

		for (Course course : allCourseResponse) {
			try {
				Blob photoBlob = course.getPhoto();
				if (photoBlob != null) {
					byte[] photoBytes = photoBlob.getBytes(1, (int) photoBlob.length());
					if (photoBytes.length > 0) {
						// String base64Photo = Base64.encodeBase64String(photoBytes);
						String base64Photo = Base64.getEncoder().encodeToString(photoBytes);

						course.setPhotoBase64(base64Photo);
					}
				}
			} catch (SQLException e) {
				// Handle exception for photo encoding
				e.printStackTrace();
			}
			updatedCourses.add(course);
		}

		return new ResponseEntity<>(updatedCourses, HttpStatus.OK);
	}
}
