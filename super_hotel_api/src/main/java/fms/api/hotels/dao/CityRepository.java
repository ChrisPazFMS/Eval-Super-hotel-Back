package fms.api.hotels.dao;

import fms.api.hotels.entities.City;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CityRepository extends JpaRepository<City, Long> {
    City findByName(String cityName);
}
