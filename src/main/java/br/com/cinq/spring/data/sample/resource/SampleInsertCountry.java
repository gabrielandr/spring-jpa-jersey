package br.com.cinq.spring.data.sample.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.sample.entity.Country;
import br.com.cinq.spring.data.sample.service.CountryService;

/**
 * Service class to delete cities from DB (country param passed)
 * 
 * @author AndradeGabriel
 *
 */
@Path("insertCountry")
public class SampleInsertCountry {
    static Logger logger = LoggerFactory.getLogger(SampleInsertCountry.class);

    @Autowired
    CountryService countryService;

    @Path("/{param}")
    @GET
    public Response insertCountry(@PathParam("param") String country) {
        Country c = new Country();
    	try {
            c = countryService.insertCountry(country);
        } catch (Exception e) {
            logger.error("An exception occurred inserting country: "+ e, e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("exception").build();
        }
    	if (c == null){
    		return Response.status(200).entity("Country '" + country + "' already exists into the db.").build();
    	} else if (c.getId() > 0){
    		return Response.status(200).entity("Country '" + country + "' successfully inserted to the db.").build();
    	}
        return Response.status(200).entity("Error inserting the new country.").build();
    }

	public CountryService getCountryService() {
		return countryService;
	}

	public void setCountryService(CountryService countryService) {
		this.countryService = countryService;
	}
}
