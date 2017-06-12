package br.com.cinq.spring.data.sample.application;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.context.annotation.Configuration;

import br.com.cinq.spring.data.sample.resource.SampleCountries;
import br.com.cinq.spring.data.sample.resource.SampleDelete;
import br.com.cinq.spring.data.sample.resource.SampleInsertCountry;
import br.com.cinq.spring.data.sample.resource.SampleResource;

/**
 * Config class with the sample resource module
 * @author AndradeGabriel
 *
 */
@Configuration
@ApplicationPath("rest")
public class Config extends ResourceConfig {

    public Config() {
        register(SampleResource.class);
        register(SampleDelete.class);
        register(SampleInsertCountry.class);
        register(SampleCountries.class);
        //		packages("br.com.cinq.greet.resource");
        //		property(ServletProperties.FILTER_FORWARD_ON_404, true);
    }


}