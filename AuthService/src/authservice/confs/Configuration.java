package authservice.confs;

import org.glassfish.jersey.server.ResourceConfig;

import authservice.filters.CORSFilter;


public class Configuration extends ResourceConfig {

	/**
	 * Register JAX-RS application components.
	 */
	public Configuration() {
		packages("authservice.services");
		register(CORSFilter.class);
	}
}
