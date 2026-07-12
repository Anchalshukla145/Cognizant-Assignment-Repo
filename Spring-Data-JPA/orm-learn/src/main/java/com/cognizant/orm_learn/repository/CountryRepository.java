package com.cognizant.orm_learn.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.cognizant.orm_learn.model.Country;

@Repository
public interface CountryRepository extends JpaRepository<Country, String> {

    // Query Methods for Hands-on
    List<Country> findByNameContaining(String name);

    List<Country> findByNameContainingOrderByNameAsc(String name);

    List<Country> findByNameStartingWith(String letter);
}
