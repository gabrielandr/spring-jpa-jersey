package br.com.cinq.spring.data.sample.service;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.sample.dao.CityDao;
import br.com.cinq.spring.data.sample.dao.CountryDao;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

/**
 * Service classes to control transactions with Spring
 * @author AndradeGabriel
 *
 */
@Component
public class CityService {
    static Logger logger = LoggerFactory.getLogger(CityService.class);

    @Autowired
    private CountryDao countryDao;

    @Autowired
    private CityDao cityDao;

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<City> listCities(Country country) {

        if (country == null) {
            return cityDao.findAll();
        } else
            return cityDao.findByCountry(country);
    }

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<City> findCitiesByCountry(String name) {
        List<City> result = new LinkedList<City>();
        logger.info("Retrieving cities from countries containing the text'{}'", name);

        List<Country> countries = null;
        if (name == null) {
            countries = countryDao.findAll();
        } else
            countries = countryDao.findLikeName(name);

        for (Country country : countries) {
            List<City> cities = listCities(country);

            result.addAll(cities);
        }

        return result;
    }
    
    @Transactional(Transactional.TxType.REQUIRED)
    public boolean deleteCitiesByCountry(String name) {
 
    	logger.info("Retrieving cities from '{}'", name);
    	List<Country> result = new ArrayList<Country>();
    	result = countryDao.findByExactName(name);
    	if (result.size() > 0){
    		Country country = result.get(0);
    		cityDao.deleteCitiesFromCountry(country.getId());
    		return true;
    	} else {
    		return false;
    	}
    }
}
