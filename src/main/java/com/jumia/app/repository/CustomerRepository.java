package com.jumia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumia.app.model.Customer;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Integer> {

	List<Customer> findAll();
}
