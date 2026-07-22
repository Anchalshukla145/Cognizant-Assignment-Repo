package com.cognizant.springlearn.service;

import com.cognizant.springlearn.dao.CountryDao;
import com.cognizant.springlearn.exception.CountryNotFoundException;
import com.cognizant.springlearn.model.Country;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryService {

    private static final Logger LOGGER = LoggerFactory.getLogger(CountryService.class);

    @Autowired
    private CountryDao countryDao;

    public List<Country> getAllCountries() {
        LOGGER.info("Start CountryService getAllCountries");
        return countryDao.getAllCountries();
    }

    public Country getCountry(String code) throws CountryNotFoundException {
        LOGGER.info("Start CountryService getCountry code: {}", code);
        return countryDao.getCountry(code);
    }

    public Country addCountry(Country country) {
        LOGGER.info("Start CountryService addCountry: {}", country);
        return countryDao.addCountry(country);
    }
}
