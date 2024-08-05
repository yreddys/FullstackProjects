package com.course.service;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

import javax.sql.rowset.serial.SerialBlob;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.course.entity.Course;
import com.course.repository.CourseRepository;

@Service
public class CourseServiceImpl implements CourseService {

	private static final Logger log = LoggerFactory.getLogger(CourseServiceImpl.class);

	@Autowired
	private CourseRepository courseRepository;

	@Override
	public Course addCourse(MultipartFile photo, String courseName, int coursePrice, String courseDescription) {
		log.info("Received request to add a new course with courseName: {}, coursePrice: {}, courseDescription: {}",
				courseName, coursePrice, courseDescription);

		Course course = new Course();
		try {
			if (photo != null && !photo.isEmpty()) {
				byte[] photoBytes = photo.getBytes();
				Blob photoBlob = new SerialBlob(photoBytes);
				course.setPhoto(photoBlob);
				log.info("Course photo set successfully");
			}
		} catch (IOException | SQLException e) {
			log.error("Error occurred while setting course photo", e);
			// Optionally rethrow or handle the exception further
		}

		course.setCourseName(courseName);
		course.setCourseDescription(courseDescription);
		course.setCoursePrice(coursePrice);

		log.info("Saving course to repository");
		return courseRepository.save(course);
	}

	@Override
	public List<Course> allCourse() {

		return courseRepository.findAll();
	}

	
}
