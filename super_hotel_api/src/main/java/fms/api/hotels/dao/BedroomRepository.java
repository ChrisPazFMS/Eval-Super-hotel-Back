package fms.api.hotels.dao;

import fms.api.hotels.entities.Bedroom;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BedroomRepository extends JpaRepository<Bedroom, Long> {
}
