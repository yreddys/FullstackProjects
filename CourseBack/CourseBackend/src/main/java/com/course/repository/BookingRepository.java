package com.course.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.course.entity.Booking;

@Repository
public interface BookingRepository extends JpaRepository<Booking, Integer> {

}
