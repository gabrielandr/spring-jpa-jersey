package br.com.cinq.spring.data.sample.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import br.com.cinq.spring.data.sample.service.CityService;

/**
 * Service class to delete cities from DB (country param passed)
 * 
 * @author AndradeGabriel
 *
 */
@Path("/")
public class SampleDelete {
    static Logger logger = LoggerFactory.getLogger(SampleDelete.class);

    @Autowired
    CityService cityService;

    @Path("deleteCities")
    @GET
    public Response deleteCities(@QueryParam("country") String name) {
    	boolean deleted = false;
        try {
        	logger.info("Retrieving cities from countries from '{}'", name);
            deleted = cityService.deleteCitiesByCountry(name);
        } catch (Exception e) {
            logger.error("An exception occurred deleting cities", e);
            return Response.status(Status.INTERNAL_SERVER_ERROR).entity("exception").build();
        }
        if (deleted){
        	return Response.status(200).entity("Cities from country " + name + " successfully deleted.").build();
        } else {
        	return Response.status(200).entity("Country '" + name + "' have no city on DB yet.").build();
        }
    }

	public CityService getCityService() {
		return cityService;
	}

	public void setCityService(CityService cityService) {
		this.cityService = cityService;
	}
}
