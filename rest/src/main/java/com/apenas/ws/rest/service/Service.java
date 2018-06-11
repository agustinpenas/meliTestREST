package com.apenas.ws.rest.service;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import com.apenas.ws.rest.vo.Request;

import solarsystem.SolarSystem;

@Path("/SolarSystem")
public class Service {

	@GET
	@Path("/weatherForDay")
	//@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Request requestWeather(/*RequestVO request*/ @QueryParam("clima") String clima, @QueryParam("dia") int dia) {
		
		SolarSystem system = new SolarSystem();
		Request request =new Request();
		request.setDia(dia);
		system.setDate(request.getDia());
		request.setClima(system.weatherForToday().getName());
		
		
		return request;
	}
	
	@GET
	@Path("/weatherForDayX")
	//@Consumes({MediaType.APPLICATION_JSON})
	@Produces(MediaType.APPLICATION_JSON)
	public Request requestWeather() {
		
		SolarSystem system = new SolarSystem();
		Request request =new Request();
		request.setDia(10);
		system.setDate(request.getDia());
		request.setClima(system.weatherForToday().getName());
		
		
		return request;
	}
}
	
	
	