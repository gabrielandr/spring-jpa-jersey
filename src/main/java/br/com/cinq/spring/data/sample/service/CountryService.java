package br.com.cinq.spring.data.sample.service;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.cinq.spring.data.sample.dao.CountryDao;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

/**
 * Service classes to control transactions with Spring
 * @author AndradeGabriel
 *
 */
@Component
public class CountryService {

    @Autowired
    private CountryDao countryDao;

    @Transactional(Transactional.TxType.SUPPORTS)
    public List<Country> listCountries(String name) {

        if (name == null) {
            return countryDao.findAll();
        } else
            return countryDao.findLikeName(name);
    }

	@Transactional(Transactional.TxType.REQUIRED)
    public Country insertCountry(String country) {
		List<Country> countries = countryDao.findByExactName(country);
		Country c = new Country();
		if(countries.size() > 0){
			return null;
		} else {
			c.setName(country);
			c = countryDao.save(c);
		}
		return c;
	}

	@Transactional(Transactional.TxType.SUPPORTS)
    public List<Country> listCountries() {
		return countryDao.findAll();
	}
}
