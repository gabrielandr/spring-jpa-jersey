package br.com.cinq.spring.data.sample.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

/**
 * Implements queries using JpaRepository
 * @author AndradeGabriel
 *
 */
public interface CityDao extends JpaRepository<City, Integer>{

    List<City> findByCountry(Country country);

	/**
	 * Query to find countries where the name contains characters on string passed by the user
	 * @param name - String with the name of the country
	 */
    @Modifying
	@Query("DELETE FROM City city where city.country.id = ?1")
    public void deleteCitiesFromCountry(Integer country_id);

}
