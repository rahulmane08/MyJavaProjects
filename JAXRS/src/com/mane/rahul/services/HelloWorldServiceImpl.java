package com.mane.rahul.services;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.mane.rahul.model.Customer;


public class HelloWorldServiceImpl implements HelloWorld {
	private static Map<Integer, Customer> allCustomers = new HashMap<Integer, Customer>();
	static{
		Customer c1  = new Customer();
		c1.setCity("Mumbai");
		c1.setCountry("India");
		c1.setFirstName("Rahul");
		c1.setId(1);
		c1.setLastName("Mane");
		c1.setState("Maharashtra");
		c1.setZip("416003");
		
		Customer c2  = new Customer();
		c2.setCity("Mumbai");
		c2.setCountry("India");
		c2.setFirstName("Nikhil");
		c2.setId(2);
		c2.setLastName("Shekar");
		c2.setState("Maharashtra");
		c2.setZip("400076");

		allCustomers.put(c1.getId(), c1);
		allCustomers.put(c2.getId(), c2);
	}
	
	@Override
	public String sayHello(String user) {
		
		return "Hi "+user;
	}

	@Override
	public List<Customer> getCustomers(String user) {
		System.out.println("user list requested by "+user);
		List<Customer> customers = new ArrayList<Customer>(allCustomers.values());		
		return customers;
	}



	@Override
	public List<Customer> getCustomersInXml() {
		
		return new ArrayList<Customer>(allCustomers.values());
	}

	@Override
	public Customer getCustomerById(int id) {
		List<Customer> customers = new ArrayList<Customer>(allCustomers.values());
		System.out.println("getting the info for the id="+id);
		Customer customer = null;
		for(Customer c:customers)
			if(c.getId()==id)
				customer = c;
		return customer;
	}

	@Override
	public boolean addCustomer(Customer customer) {
		Customer c1 = allCustomers.put(customer.getId(), customer);
		
		
		return true;
	}
	
	

	
}
