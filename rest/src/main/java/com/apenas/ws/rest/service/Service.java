package com.apenas.ws.rest.service;

import java.sql.SQLException;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.apenas.ws.rest.vo.Request;
import com.h2.init.H2Database;

@Path("/SolarSystem")
public class Service {

	@GET
	@Path("/weatherForDay")
	@Produces(MediaType.APPLICATION_JSON)
	public Request requestWeather( @QueryParam("clima") String clima,
			@QueryParam("dia") int dia) {

		Request request = new Request();

		try {
			request = H2Database.getweatherForDay(dia);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return request;
	}

}
