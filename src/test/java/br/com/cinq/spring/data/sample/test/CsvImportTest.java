package br.com.cinq.spring.data.sample.test;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.WebIntegrationTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import br.com.cinq.spring.data.sample.application.Application;
import br.com.cinq.spring.data.sample.dao.CityDao;
//import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.City;
import br.com.cinq.spring.data.sample.entity.Country;

/**
 * Test if the csv import works, if the number of cities from Brazil is 6, means it works
 * @author AndradeGabriel
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebIntegrationTest(randomPort = true)
@IntegrationTest("server.port=9000")
@ActiveProfiles("unit")
public class CsvImportTest {
    Logger logger = LoggerFactory.getLogger(CsvImportTest.class);

    @Autowired
    private CityDao dao;

    @Test
    public void testGetCitiesFromBrazil() throws InterruptedException {

      Assert.assertNotNull(dao);
      
      Assert.assertTrue(dao.count()>0);

      Country country = new Country();
      country.setId(1); // Should be Brazil

      List<City> list = dao.findByCountry(country);

      Assert.assertEquals(6, list.size());
  }
}
