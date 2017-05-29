package com.mane.rahul.services;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;

import org.springframework.stereotype.Service;

import com.mane.rahul.model.Customer;

@Service
@Path("/helloworld")
public interface HelloWorld {

	@GET
	@Produces("text/plain")
	@Path("/user/{user}")
	public String sayHello(@PathParam("user") String user);
	
	@GET
	@Produces("application/json")
	@Path("/customer")
	public List<Customer> getCustomers(@HeaderParam("WEB_USER") String user);
	
	@GET
	@Produces("application/json")
	@Path("/customer/info")
	public Customer getCustomerById(@QueryParam("id") int id);
	
	@GET
	@Produces("application/xml")
	@Path("/customer/xml")
	public List<Customer> getCustomersInXml();
	
	@POST
	@Consumes("application/json")
	@Path("/customer/new")
	public boolean addCustomer(Customer customer);
	
}
