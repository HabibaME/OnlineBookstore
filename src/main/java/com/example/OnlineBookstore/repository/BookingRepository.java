package com.example.OnlineBookstore.repository;

import com.example.OnlineBookstore.entity.Booking;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookingRepository extends JpaRepository<Booking, Long> {
}
