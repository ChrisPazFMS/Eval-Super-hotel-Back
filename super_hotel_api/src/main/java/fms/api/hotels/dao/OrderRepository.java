package fms.api.hotels.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import fms.api.hotels.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
