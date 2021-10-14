package com.jumia.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jumia.app.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, Integer> {

	List<Country> findAll();
}
