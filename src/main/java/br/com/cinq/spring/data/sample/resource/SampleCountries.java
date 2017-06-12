package br.com.cinq.spring.data.sample.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.service.CountryService;

/**
 * Sample class implementing a Jersey webservice
 * 
 * @author AndradeGabriel
 *
 */
@Path("/")
public class SampleCountries {
    static Logger logger = LoggerFactory.getLogger(SampleCountries.class);

    @Autowired
    private CountryService countryService;

    @Path("countries")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response findCities() {
        List<Country> result = null;
        try {
        	logger.info("Retrieving countries");
            result = countryService.listCountries();
        } catch (Exception e) {
            logger.error("An exception occurred while retrieving cities", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("exception").build();
        }

        return Response.ok().entity(result).build();
    }

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}
}
