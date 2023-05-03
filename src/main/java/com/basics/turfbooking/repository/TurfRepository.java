package com.basics.turfbooking.repository;

import com.basics.turfbooking.entity.Turf;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TurfRepository extends JpaRepository<Turf,Integer> {


    @Query("SELECT t FROM Turf t where t.location =:l")
    public List<Turf> findTurfByLocation(@Param("l") String location);

}
