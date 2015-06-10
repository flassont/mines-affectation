package org.mines.nantes.rest;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module;

import org.mines.nantes.util.HibernateUtil;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.ext.ContextResolver;
import javax.ws.rs.ext.Provider;

/**
 * Jax-RS activator (Java EE6 style)
 */
@ApplicationPath("/rest")
public class JaxRsActivator extends Application {
	/**
	 * Context resolver for Hibernate Jackson module loading
	 * @see com.fasterxml.jackson.datatype.hibernate4.Hibernate4Module
	 */
	@Provider
	@Produces(MediaType.APPLICATION_JSON)
	public static class HibernateLoader implements ContextResolver<ObjectMapper> {

		final ObjectMapper defaultObjectMapper;

		public HibernateLoader() {
			defaultObjectMapper = new ObjectMapper();

			Hibernate4Module hibernate4Module = new Hibernate4Module(HibernateUtil.getSessionFactory());

			defaultObjectMapper.registerModule(hibernate4Module);

			defaultObjectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		}

		@Override
		public ObjectMapper getContext(Class<?> aClass) {
			return defaultObjectMapper;
		}
	}
}
