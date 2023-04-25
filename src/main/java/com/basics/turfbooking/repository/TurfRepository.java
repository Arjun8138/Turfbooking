package com.basics.turfbooking.repository;

import com.basics.turfbooking.entity.Turf;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TurfRepository extends JpaRepository<Turf,Integer> {
}
