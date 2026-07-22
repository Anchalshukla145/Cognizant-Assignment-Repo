package com.cognizant.springlearn.dao;

import com.cognizant.springlearn.exception.CountryNotFoundException;
import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class CountryDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryDao.class);
    private static final List<Country> COUNTRY_LIST = new ArrayList<>();

    static {
        COUNTRY_LIST.add(new Country("IN", "India"));
        COUNTRY_LIST.add(new Country("US", "United States"));
        COUNTRY_LIST.add(new Country("DE", "Germany"));
        COUNTRY_LIST.add(new Country("JP", "Japan"));
    }

    public List<Country> getAllCountries() {
        LOGGER.info("Start CountryDao getAllCountries");
        return COUNTRY_LIST;
    }

    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("Start CountryDao getCountry with code: {}", code);
        return COUNTRY_LIST.stream()
                .filter(c -> c.getCode().equalsIgnoreCase(code))
                .findFirst()
                .orElseThrow(() -> new CountryNotFoundException("Country with code " + code + " not found"));
    }

    public Country addCountry(Country country) {
        LOGGER.info("Start CountryDao addCountry: {}", country);
        COUNTRY_LIST.add(country);
        return country;
    }
}
