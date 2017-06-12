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
public interface CountryDao extends JpaRepository<Country, Integer> {

	@Query("SELECT country FROM Country country where name LIKE CONCAT('%',?1,'%')")
    List<Country> findLikeName(String name);
	
	@Query("SELECT country FROM Country country where name = ?1")
	List<Country> findByExactName(String name);

}