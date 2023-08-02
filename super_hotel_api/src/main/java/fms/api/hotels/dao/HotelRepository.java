package fms.api.hotels.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import fms.api.hotels.entities.Hotel;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Long> {

    // Requête pour obtenir le nombre total de chambres dans la ville
    @Query("SELECT SUM(hb.number) FROM Hotel h JOIN h.bedrooms hb WHERE h.city.id = :cityId")
    int getTotalNumberOfBedroomsInCity(@Param("cityId") Long cityId);

    // Requête pour obtenir le nombre total de chambres disponibles dans la ville
    @Query("SELECT COUNT(hb) FROM Hotel h JOIN h.bedrooms hb WHERE h.city.id = :cityId AND hb.available = true")
    int getTotalNumberOfAvailableBedroomsInCity(@Param("cityId") Long cityId);
}