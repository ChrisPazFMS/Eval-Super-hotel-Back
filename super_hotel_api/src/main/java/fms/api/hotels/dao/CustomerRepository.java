package fms.api.hotels.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import fms.api.hotels.entities.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

}