package fms.api.hotels.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fms.api.hotels.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

}