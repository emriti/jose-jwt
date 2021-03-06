package apiservice.confs;

import org.glassfish.jersey.server.ResourceConfig;

import apiservice.filters.CORSFilter;
import apiservices.auths.AuthenticationFilter;

public class Configuration extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public Configuration() {
		packages("apiservice.services");
		register(CORSFilter.class);
		register(AuthenticationFilter.class);
	}
}
