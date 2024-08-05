package com.course.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import com.course.entity.Course;

public interface CourseService {

	Course addCourse(MultipartFile photo, String courseName, int coursePrice, String courseDescription);

	List<Course> allCourse();

	 //byte[] getRoomPhotoByRoomId(int id);
	
	
	

	

}
